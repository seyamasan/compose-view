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

    @Serializable
    data class ChipView(
        val description: String
    )

    @Serializable
    data class DialogView(
        val description: String
    )

    @Serializable
    data class IndicatorView(
        val description: String
    )

    @Serializable
    data class SliderView(
        val description: String
    )

    @Serializable
    data class SwitchView(
        val description: String
    )

    @Serializable
    data class CheckboxView(
        val description: String
    )
}