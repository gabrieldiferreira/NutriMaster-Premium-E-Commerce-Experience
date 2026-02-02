package com.nutrimaster.shared.domain

import kotlinx.serialization.Serializable

@Serializable
data class Customer (
    val id: String,
    val photoURL: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val city: String? = null,
    val postalCode: String?,
    val address: String? = null,
    val phoneNumber: PhoneNumber? = null,
    val cart: List<CartItem> = emptyList()
)

@Serializable
data class PhoneNumber(
    val dialcode: Int,
    val number: String,
)
