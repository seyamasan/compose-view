package com.example.composeview.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeview.loading.LoadingType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IndicatorViewModel : ViewModel() {
    var linearDeterminateIndicatorCurrentProgress by mutableFloatStateOf(0f)
    var linearDeterminateIndicatorLoading by mutableStateOf(false)
    var circularDeterminateIndicatorCurrentProgress by mutableFloatStateOf(0f)
    var circularDeterminateIndicatorLoading by mutableStateOf(false)

    fun startLoading(type: LoadingType) {
        when (type) {
            LoadingType.LINEAR -> {
                linearDeterminateIndicatorLoading = true
                viewModelScope.launch {
                    loadProgress { progress ->
                        linearDeterminateIndicatorCurrentProgress = progress
                    }
                    linearDeterminateIndicatorLoading = false // loadProgress終了後にリセット
                }
            }
            LoadingType.CIRCULAR -> {
                circularDeterminateIndicatorLoading = true
                viewModelScope.launch {
                    loadProgress { progress ->
                        circularDeterminateIndicatorCurrentProgress = progress
                    }
                    circularDeterminateIndicatorLoading = false
                }
            }
        }
    }

    /** 進捗に使う値をforで回す */
    private suspend fun loadProgress(updateProgress: (Float) -> Unit) {
        for (i in 1..100) {
            updateProgress(i / 100f)
            delay(100)
        }
    }
}