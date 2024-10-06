package com.example.composeview.navigator

import androidx.compose.runtime.Composable

enum class Screens {
    FIRST,
    SECOND
}

interface AppNavigator {
    @Composable
    fun NavigateTo(screen: Screens)
}