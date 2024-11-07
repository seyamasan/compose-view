package com.example.composeview.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckboxViewModel : ViewModel() {
    private val _childCheckedStates = MutableLiveData(listOf(false, false, false))
    val childCheckedStates: LiveData<List<Boolean>> = _childCheckedStates

    // 子チェックボックスの状態を更新する関数
    fun updateChildState(index: Int, isChecked: Boolean) {
        _childCheckedStates.value = _childCheckedStates.value?.toMutableList()?.apply {
            this[index] = isChecked
        }
    }
}