package org.gabrielferreira_nutrimaster

import androidx.compose.ui.window.ComposeUIViewController
import com.gabrielferreira_dev.nutrimaster.di.initializeKoin

fun MainViewController() = ComposeUIViewController (
    configure = { initializeKoin() }
) { App() }