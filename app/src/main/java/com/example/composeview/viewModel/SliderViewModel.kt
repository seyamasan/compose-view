package com.example.composeview.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SliderViewModel : ViewModel() {
    private val _rangeSliderExamplePosition = MutableLiveData(0f..100f)
    val rangeSliderExamplePosition: LiveData<ClosedFloatingPointRange<Float>> = _rangeSliderExamplePosition

    // スライダーの位置を更新する関数
    fun updateSliderPosition(newPosition: ClosedFloatingPointRange<Float>) {
        _rangeSliderExamplePosition.value = newPosition
    }

    // onValueChangeFinishedでの処理
    fun finalizeSliderChange() {
        print("stop!!!")
    }
}