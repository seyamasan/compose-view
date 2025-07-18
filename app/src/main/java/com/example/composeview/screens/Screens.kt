package com.example.composeview.screens

import com.example.composeview.R
import kotlinx.serialization.Serializable

class Screens {
    companion object {
        val screenList = listOf(
            BottomSheetView(),
            ButtonGroupView(),
            ButtonView(),
            BudgeBoxView(),
            CardView(),
            CheckboxView(),
            ChipView(),
            DatePickerView(),
            DialogView(),
            DividerView(),
            FloatingActionButtonView(),
            HorizontalFloatingToolbarView(),
            IndicatorView(),
            NavigationDrawerView(),
            QuantityPickerView(),
            SliderView(),
            SplitButtonView(),
            SwitchView(),
            TimePikerView()
        )
    }

    @Serializable
    object Home

    @Serializable
    data class ButtonView (
        override val viewNameResId: Int = R.string.button_view_name,
        override val descriptionResId: Int = R.string.button_view_description
    ):ScreenData

    @Serializable
    data class FloatingActionButtonView(
        override val viewNameResId: Int = R.string.floating_action_button_view_name,
        override val descriptionResId: Int = R.string.floating_action_button_view_description
    ):ScreenData

    @Serializable
    data class CardView(
        override val viewNameResId: Int = R.string.card_view_name,
        override val descriptionResId: Int = R.string.card_view_description
    ):ScreenData

    @Serializable
    data class ChipView(
        override val viewNameResId: Int = R.string.chip_view_name,
        override val descriptionResId: Int = R.string.chip_view_description
    ):ScreenData

    @Serializable
    data class DialogView(
        override val viewNameResId: Int = R.string.dialog_view_name,
        override val descriptionResId: Int = R.string.dialog_view_description
    ):ScreenData

    @Serializable
    data class IndicatorView(
        override val viewNameResId: Int = R.string.indicator_view_name,
        override val descriptionResId: Int = R.string.indicator_view_description
    ):ScreenData

    @Serializable
    data class SliderView(
        override val viewNameResId: Int = R.string.slider_view_name,
        override val descriptionResId: Int = R.string.slider_view_description
    ):ScreenData

    @Serializable
    data class SwitchView(
        override val viewNameResId: Int = R.string.switch_view_name,
        override val descriptionResId: Int = R.string.switch_view_description
    ):ScreenData

    @Serializable
    data class CheckboxView(
        override val viewNameResId: Int = R.string.checkbox_view_name,
        override val descriptionResId: Int = R.string.checkbox_view_description
    ):ScreenData

    @Serializable
    data class BudgeBoxView(
        override val viewNameResId: Int = R.string.budge_box_view_name,
        override val descriptionResId: Int = R.string.budge_box_view_description
    ):ScreenData

    @Serializable
    data class BottomSheetView(
        override val viewNameResId: Int = R.string.bottom_sheet_view_name,
        override val descriptionResId: Int = R.string.bottom_sheet_view_description
    ):ScreenData

    @Serializable
    data class NavigationDrawerView(
        override val viewNameResId: Int = R.string.navigation_drawer_view_name,
        override val descriptionResId: Int = R.string.navigation_drawer_view_description
    ):ScreenData

    @Serializable
    data class DividerView(
        override val viewNameResId: Int = R.string.divider_view_name,
        override val descriptionResId: Int = R.string.divider_view_description
    ):ScreenData

    @Serializable
    data class DatePickerView(
        override val viewNameResId: Int = R.string.date_picker_view_name,
        override val descriptionResId: Int = R.string.date_picker_view_description
    ):ScreenData

    @Serializable
    data class TimePikerView(
        override val viewNameResId: Int = R.string.time_picker_view_name,
        override val descriptionResId: Int = R.string.time_picker_view_description
    ):ScreenData

    @Serializable
    data class QuantityPickerView(
        override val viewNameResId: Int = R.string.quantity_picker_view_name,
        override val descriptionResId: Int = R.string.quantity_picker_view_description
    ):ScreenData

    @Serializable
    data class SplitButtonView(
        override val viewNameResId: Int = R.string.split_button_view_name,
        override val descriptionResId: Int = R.string.split_button_view_description
    ):ScreenData

    @Serializable
    data class HorizontalFloatingToolbarView(
        override val viewNameResId: Int = R.string.horizontal_floating_toolbar_view_name,
        override val descriptionResId: Int = R.string.horizontal_floating_toolbar_view_description
    ):ScreenData

    @Serializable
    data class ButtonGroupView(
        override val viewNameResId: Int = R.string.button_group_view_name,
        override val descriptionResId: Int = R.string.button_group_view_description
    ):ScreenData
}