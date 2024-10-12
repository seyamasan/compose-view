package com.example.composeview.navigator

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

interface AppNavigator {
    @Composable
    fun NavigateTo()
}

class Screens {
    @Serializable
    object Home

    @Serializable
    data class ButtonView (
        val title: String
    )

    @Serializable
    data class Second(
        val title: String
    )
}