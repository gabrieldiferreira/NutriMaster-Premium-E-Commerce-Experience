package com.gabrielferreira_dev.nutrimaster.data.domain

import com.nutrimaster.shared.util.RequestState
import dev.gitlive.firebase.auth.FirebaseUser

interface CustomerRepository {
    fun getCurrentUserID(): String?
    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )
    suspend fun signOut(): RequestState<Unit>
}

