package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import java.util.Calendar

@Composable
fun TimePikerView(navController: NavHostController?, viewName: String, description: String) {
    var showDialExample by rememberSaveable { mutableStateOf(false) }
    var selectedTime by rememberSaveable { mutableStateOf("") }

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

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ダイアル式のTimePicker
                DialExample(
                    selectedTime = selectedTime,
                    showDialExample = showDialExample,
                    onShowButtonClick = { showDialExample = !showDialExample },
                    onConfirm = { newValue ->
                        // フォーマットしないと頭文字の0抜ける
                        selectedTime = "%02d:%02d".format(newValue.first, newValue.second)
                        showDialExample = false
                    },
                    onDismiss = { showDialExample = false}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialExample(
    selectedTime: String,
    showDialExample: Boolean,
    onShowButtonClick: () -> Unit,
    onConfirm: (Pair<Int, Int>) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onShowButtonClick) {
            Text(stringResource(id = R.string.time_select_1))
        }
        OutlinedTextField(
            value = selectedTime,
            onValueChange = {},
            label = { Text(stringResource(id = R.string.dial_time_piker)) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )


        if (showDialExample) {
            TimePicker(
                state = timePickerState,
            )
            Button(onClick = onDismiss) {
                Text("Dismiss picker")
            }
            Button(
                onClick = {
                    onConfirm(Pair(timePickerState.hour, timePickerState.minute))
                }
            ) {
                Text("Confirm selection")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimePikerViewPreview() {
    ComposeViewTheme {
        TimePikerView(
            null,
            viewName = stringResource(id = R.string.time_picker_view_name),
            description = stringResource(id = R.string.time_picker_view_description)
        )
    }
}