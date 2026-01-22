package com.gabrielferreira_dev.nutrimaster.home.domain

import androidx.compose.runtime.Composable

enum class CustomDrawerState {
    Opened,
    Closed
}

fun CustomDrawerState.isOpened(): Boolean {
    return this == CustomDrawerState.Opened
}

fun CustomDrawerState.opposite(): CustomDrawerState{
    return if (this == CustomDrawerState.Opened) CustomDrawerState.Closed
    else CustomDrawerState.Opened
}

