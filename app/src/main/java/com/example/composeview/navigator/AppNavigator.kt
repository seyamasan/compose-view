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
        val viewName: String,
        val description: String
    )

    @Serializable
    data class FloatingActionButtonView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class CardView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class ChipView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class DialogView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class IndicatorView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class SliderView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class SwitchView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class CheckboxView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class BudgeBoxView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class BottomSheetView(
        val viewName: String,
        val description: String
    )

    @Serializable
    data class NavigationDrawerView(
        val viewName: String,
        val description: String
    )
}