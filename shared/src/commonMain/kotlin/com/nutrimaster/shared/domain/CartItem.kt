package com.nutrimaster.shared.domain

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@OptIn(ExperimentalUuidApi::class)
data class CartItem(
    var id: String = Uuid.random().toString(),
    val productID: String,
    val flavor: String? = null,
    val quantity: Int
)
