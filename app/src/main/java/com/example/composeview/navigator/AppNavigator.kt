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
        val description: String
    )

    @Serializable
    data class FloatingActionButtonView(
        val description: String
    )

    @Serializable
    data class CardView(
        val description: String
    )
}