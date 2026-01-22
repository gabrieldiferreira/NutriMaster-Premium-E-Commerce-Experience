package com.nutrimaster.shared.domain

import com.nutrimaster.shared.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class Country(

    val dialCode: Int,
    val code: String,
    val flag: DrawableResource

) {
    Australia(
        dialCode = 61,
        code = "AUS",
        flag = Resources.Flag.Australia
    ),
    USA(
        dialCode = 1,
        code = "EUA",
        flag = Resources.Flag.Usa
    ),
    Brazil(
    dialCode = 55,
    code = "BR",
    flag = Resources.Flag.Brazil
    ),
    Japan(
        dialCode = 81,
        code = "JPN",
        flag = Resources.Flag.Japan
    )
}