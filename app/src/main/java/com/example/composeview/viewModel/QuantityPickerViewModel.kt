package com.example.composeview.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composeview.QuantityPickerState

class QuantityPickerViewModel: ViewModel() {
    private val _quantityPickerState = mutableStateOf(QuantityPickerState(minQuantity = 0, maxQuantity = 5, initialQuantity = 0))
    val quantityPickerState: QuantityPickerState
        get() = _quantityPickerState.value
}