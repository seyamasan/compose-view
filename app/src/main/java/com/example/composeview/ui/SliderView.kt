package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.viewModel.SliderViewModel

@Composable
fun SliderView(
    navController: NavHostController?,
    viewName: String,
    description: String,
    sliderViewModel: SliderViewModel = viewModel()
) {
    var sliderMinimalExamplePosition by rememberSaveable { mutableFloatStateOf(0f) }
    var sliderAdvancedExamplePosition by rememberSaveable { mutableFloatStateOf(0f) }
    // rememberSaveableで保持できないからviewModelで管理
    val rangeSliderExamplePosition by sliderViewModel.rangeSliderExamplePosition.observeAsState(0f..100f)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, viewName, true)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    SliderMinimalExample(
                        sliderPosition = sliderMinimalExamplePosition,
                        onChange = { sliderMinimalExamplePosition = it }
                    )

                    SliderAdvancedExample(
                        sliderPosition = sliderAdvancedExamplePosition,
                        onChange = { sliderAdvancedExamplePosition = it }
                    )

                    RangeSliderExample(
                        sliderPosition = rangeSliderExamplePosition,
                        onChange = { sliderViewModel.updateSliderPosition(it) },
                        onValueChangeFinished = { sliderViewModel.finalizeSliderChange() }
                    )
                }
            }
        }
    }
}

@Composable
private fun SliderMinimalExample(sliderPosition: Float, onChange: (Float) -> Unit) {
    // 最低限のスライダー
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { onChange(it) }
        )
        Text(text = sliderPosition.toString())
    }
}

@Composable
private fun SliderAdvancedExample(sliderPosition: Float, onChange: (Float) -> Unit) {
    // 少しアレンジを加えたスライダー
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { onChange(it) },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3, // つまみをスナップするスライダーのノッチの数を指定
            valueRange = 0f..50f // 値の範囲を指定
        )
        Text(text = sliderPosition.toString())
    }
}

@Composable
private fun RangeSliderExample(
    sliderPosition: ClosedFloatingPointRange<Float>,
    onChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onValueChangeFinished: () -> Unit
) {
    // 範囲指定できるスライダー
    Column {
        RangeSlider(
            value = sliderPosition,
            steps = 5,
            onValueChange = { onChange(it) },
            valueRange = 0f..100f,
            onValueChangeFinished = onValueChangeFinished // スライダーを手放した時に走る
        )
        Text(text = sliderPosition.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun SliderViewPreview() {
    ComposeViewTheme {
        SliderView(
            null, 
            viewName = stringResource(id = R.string.slider_view_name),
            description = stringResource(id = R.string.slider_view_description)
        )
    }
}