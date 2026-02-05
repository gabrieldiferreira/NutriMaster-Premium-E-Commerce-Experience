package com.gabrielferreira_dev.nutrimaster.data.domain

import com.nutrimaster.shared.domain.Customer
import com.nutrimaster.shared.util.RequestState
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getCurrentUserID(): String?
    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )

    //Inicio do processo de ler os dados do cliente / mas pega dados locais ja existente
    fun readCustomerFlow(): Flow<RequestState<Customer>>
    //buscar no firebase e atualizar os dados / nao pega do cache local
    suspend fun refreshCustomerInfo()
    suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
    suspend fun signOut(): RequestState<Unit>
}

