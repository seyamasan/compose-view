package com.example.composeview.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object SnackbarUtils {
    fun showSnackbar(scope: CoroutineScope, snackbarHostState: SnackbarHostState, message: String) {
        scope.launch {
            // showSnackbar()はsuspend関数なのでコルーチンスコープ内で実行
            snackbarHostState
                .showSnackbar(
                    message = message,
                    // デフォルト値はSnackbarDuration.Short
                    duration = SnackbarDuration.Short // すぐ消える
                )
        }
    }
}