package com.gabrielferreira_dev.nutrimaster.data

import com.gabrielferreira_dev.nutrimaster.data.domain.CustomerRepository
import com.nutrimaster.shared.domain.Customer
import com.nutrimaster.shared.util.RequestState
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class CustomerRepositoryImpl : CustomerRepository {
    override fun getCurrentUserID(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if (user != null){
                val customerCollection = Firebase.firestore.collection(collectionPath = "customer")
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split(" ")?.firstOrNull() ?: "Unknown",
                    lastName = user.displayName?.split(" ")?.lastOrNull() ?: "Unknown",
                    email = user.email ?: "Unknown",
                    postalCode = "Unknown",
                    photoURL = user.photoURL ?: "Unknown"
                )

                val customerExists = customerCollection.document(user.uid).get().exists

                if(customerExists){
                    onSuccess()
                }else {
                    customerCollection.document(user.uid).set(customer)
                    onSuccess()
                }
            }else{
                onError("Usuario é nulo")
            }
        }catch (e: Exception){
            onError("Erro ao criar o Usuario: ${e.message}")
        }
    }
    //implementei essa fun para ler os dados do cliente, pegos as info da tabela no firebase
    //faz a validacao do userID, coleta os dados do database.collection
    override fun readCustomerFlow(): Flow<RequestState<Customer>> = channelFlow {
        try {
            val userId = getCurrentUserID()
            if (userId != null) {
                val database = Firebase.firestore
                database.collection(collectionPath = "customer")
                    .document(userId)
                    .snapshots
                    .collectLatest { document ->
                        if (document.exists) {
                            val customer = Customer(
                                id = document.id,
                                firstName = document.get(field = "firstName"),
                                lastName = document.get(field = "lastName"),
                                email = document.get(field = "email"),
                                city = document.get(field = "city"),
                                address = document.get(field = "address"),
                                phoneNumber = document.get(field = "phoneNumber"),
                                postalCode = document.get(field = "postalCode"),
                                photoURL = document.get(field = "photoURL"),
                                cart = document.get(field = "cart")
                            )
                            send(RequestState.Success(data = customer))
                        } else{
                            send(RequestState.Error("Esse nome de banco de dados nao existe!"))
                        }
                    }
            }else {
                send(RequestState.Error("Usuario nao encontrado!"))
            }
        } catch (e: Exception){
            send(RequestState.Error("Erro ao ler os dados do Cliente ${e.message}"))
        }
    }

    override suspend fun refreshCustomerInfo() {
        try {
            val user = Firebase.auth.currentUser
            val userId = user?.uid
            if (userId != null) {
                val customerCollection = Firebase.firestore.collection("customer")
                val document = customerCollection.document(userId).get()
                if (!document.exists){
                    val updatedData = mutableMapOf<String, Any?>(
                        "firstName" to (user.displayName?.split(" ")?.firstOrNull() ?: "Unknown"),
                        "lastName" to (user.displayName?.split(" ")?.lastOrNull() ?: "Unknown"),
                        "email" to (user.email ?: "Unknown"),
                        "photoURL" to (user.photoURL ?: "Unknown")
                    )
                    customerCollection.document(userId).update(updatedData)
                }
            }
        } catch (e: Exception){

        }
    }

    //atualizar formulario
    override suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val userId = getCurrentUserID()
            if (userId != null) {
                val firestore = Firebase.firestore
                val customerCollection = firestore.collection(collectionPath = "customer")

                val existingCustomer = customerCollection
                    .document(customer.id)
                    .get()
                if(existingCustomer.exists) {
                    customerCollection
                        .document(customer.id)
                        .update(
                            "firstName" to customer.firstName,
                            "lastName" to customer.lastName,
                            "email" to customer.email,
                            "city" to customer.city,
                            "postalCode" to customer.postalCode,
                            "address" to customer.address,
                            "phoneNumber" to customer.phoneNumber,
                        )
                    onSuccess()
                }else {
                    onError("Usuario nao encontrado!")
                }
            } else {
                onError("Usuario nao disponivel")
            }
        } catch (e: Exception) {
            onError("Error ao atualizar seus dados! ${e.message}")
        }
    }

    override suspend fun signOut(): RequestState<Unit> {
        return try {
            Firebase.auth.signOut()
            RequestState.Success(data = Unit)
        }catch (e: Exception){
            RequestState.Error("Erro ao fazer Log Out${e.message}")
        }
    }
}