package com.gabrielferreira_dev.nutrimaster.data

import com.gabrielferreira_dev.nutrimaster.data.domain.CustomerRepository
import com.nutrimaster.shared.domain.Customer
import com.nutrimaster.shared.util.RequestState
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

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

    override suspend fun signOut(): RequestState<Unit> {
        return try {
            Firebase.auth.signOut()
            RequestState.Success(data = Unit)
        }catch (e: Exception){
            RequestState.Error("Erro ao fazer Log Out${e.message}")
        }
    }
}