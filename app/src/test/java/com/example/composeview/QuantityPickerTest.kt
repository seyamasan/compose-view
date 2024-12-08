package com.example.composeview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composeview.ui.QuantityPicker
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/*
コンポーザブルの振る舞いのテスト。
Robolectricを使ってローカルマシンで実行する前提なので、testソースセットに配置している。
実機やエミュレータでテストを実行するには、androidTestソースセットに配置する。
*/

@RunWith(RobolectricTestRunner::class)
class QuantityPickerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun quantity_should_be_increased_when_button_is_tapped() {
        // 準備
        composeTestRule.setContent {
            QuantityPickerTestContent(initialQuantity = 1)
        }

        // 実行
        composeTestRule
            .onNodeWithContentDescription("Increase quantity") // contentDescriptionが"Increase quantity"に等しいコンポーザブルを取得　Finder
            .performClick() // そしてクリック Action

        // 確認
        composeTestRule
            .onNodeWithText("2") // 指定した文字列を表示しているコンポーザブルを検索する
            .assertExists() // 条件にマッチしたコンポーザブルが存在するか検証
    }

    @Test
    fun quantity_should_be_decreased_when_button_is_tapped() {
        composeTestRule.setContent {
            QuantityPickerTestContent(initialQuantity = 1)
        }

        composeTestRule
            .onNodeWithContentDescription("Decrease quantity")
            .performClick()

        composeTestRule
            .onNodeWithText("0")
            .assertExists()
    }

    @Composable
    fun QuantityPickerTestContent(initialQuantity: Int) {
        val state = remember {
            QuantityPickerState(
                minQuantity = 0,
                maxQuantity = 3,
                initialQuantity = initialQuantity
            )
        }
        QuantityPicker(state = state)
    }
}
