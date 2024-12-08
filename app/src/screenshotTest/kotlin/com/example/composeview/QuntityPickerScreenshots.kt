package com.example.composeview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeview.ui.QuantityPicker

/*
スクリーンショットテスト
【./gradlew updateDebugScreenshotTest】でスクリーンショットテスト用の写真を撮る
【./gradlew validateDebugScreenshotTest】で比較用の写真を撮る
前後見比べる感じ。
 */

@Composable
@Preview(showBackground = true)
fun QuantityPickerPreviewMinQuantity() {
    val state = QuantityPickerState(
        minQuantity = 0,
        maxQuantity = 99,
        initialQuantity = 0 // 数量が下限
    )
    QuantityPicker(state)
}

@Composable
@Preview(showBackground = true)
fun QuantityPickerPreviewMaxQuantity() {
    val state = QuantityPickerState(
        minQuantity = 0,
        maxQuantity = 99,
        initialQuantity = 99 // 数量が上限
    )
    QuantityPicker(state)
}

@Composable
@Preview(showBackground = true)
fun QuantityPickerPreview() {
    val state = QuantityPickerState(
        minQuantity = 0,
        maxQuantity = 99,
        initialQuantity = 1 // 下限でも上限でもない
    )
    QuantityPicker(state)
}
