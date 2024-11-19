package com.example.composeview.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import java.util.Calendar

@Composable
fun TimePikerView(navController: NavHostController?, viewName: String, description: String) {
    var showDialExample by rememberSaveable { mutableStateOf(false) }
    var dialExampleSelectedTime by rememberSaveable { mutableStateOf("") }
    var showInputExample by rememberSaveable { mutableStateOf(false) }
    var inputExampleSelectedTime by rememberSaveable { mutableStateOf("") }
    var showDialWithDialogExample by rememberSaveable { mutableStateOf(false) }
    var dialWithDialogExampleSelectedTime by rememberSaveable { mutableStateOf("") }
    var showAdvancedTimePickerExample by rememberSaveable { mutableStateOf(false) }
    var advancedTimePickerExampleSelectedTime by rememberSaveable { mutableStateOf("") }
    var showDial by rememberSaveable { mutableStateOf(false) }

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
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    // ダイアル式のTimePicker
                    DialExample(
                        selectedTime = dialExampleSelectedTime,
                        showDialExample = showDialExample,
                        onShowButtonClick = {
                            showDialExample = !showDialExample
                            showInputExample = false
                            showDialWithDialogExample = false
                            showAdvancedTimePickerExample = false
                        },
                        onConfirm = { newValue ->
                            // フォーマットしないと頭文字の0抜ける
                            dialExampleSelectedTime = "%02d:%02d".format(newValue.first, newValue.second)
                            showDialExample = false
                        },
                        onDismiss = { showDialExample = false}
                    )
                }


                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    // 入力式のTimePicker
                    InputExample(
                        selectedTime = inputExampleSelectedTime,
                        showInputExample = showInputExample,
                        onShowButtonClick = {
                            showDialExample = false
                            showInputExample = !showInputExample
                            showDialWithDialogExample = false
                            showAdvancedTimePickerExample = false
                        },
                        onConfirm = { newValue ->
                            inputExampleSelectedTime = "%02d:%02d".format(newValue.first, newValue.second)
                            showInputExample = false
                        },
                        onDismiss = { showInputExample = false}
                    )
                }

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    // ダイアログにダイアル式TimePikerをのせる
                    DialWithDialogExample(
                        selectedTime = dialWithDialogExampleSelectedTime,
                        showDialWithDialogExample = showDialWithDialogExample,
                        onShowButtonClick = {
                            showDialExample = false
                            showInputExample = false
                            showDialWithDialogExample = !showDialWithDialogExample
                            showAdvancedTimePickerExample = false
                        },
                        onConfirm = { newValue ->
                            dialWithDialogExampleSelectedTime = "%02d:%02d".format(newValue.first, newValue.second)
                            showDialWithDialogExample = false
                        },
                        onDismiss = { showDialWithDialogExample = false}
                    )
                }

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    // アイコンをタップすることでダイアルか入力か選べるTimePiker
                    AdvancedTimePickerExample(
                        selectedTime = advancedTimePickerExampleSelectedTime,
                        showDial = showDial,
                        showAdvancedTimePickerExample = showAdvancedTimePickerExample,
                        onShowButtonClick = {
                            showDialExample = false
                            showInputExample = false
                            showDialWithDialogExample = false
                            showAdvancedTimePickerExample = !showAdvancedTimePickerExample
                        },
                        onShowDialToggle = { showDial = !showDial },
                        onConfirm = { newValue ->
                            advancedTimePickerExampleSelectedTime = "%02d:%02d".format(newValue.first, newValue.second)
                            showAdvancedTimePickerExample = false
                        },
                        onDismiss = { showAdvancedTimePickerExample = false }
                    )
                }
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
    onDismiss: () -> Unit
) {
    val currentTime = Calendar.getInstance() // 現在の時刻で初期化

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true, // デフォルトはfalse
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                .wrapContentHeight()
        )


        if (showDialExample) {
            TimePicker(
                state = timePickerState,
            )
            ElevatedButton(onClick = onDismiss) {
                Text("Cancel")
            }
            ElevatedButton(
                onClick = {
                    onConfirm(Pair(timePickerState.hour, timePickerState.minute))
                }
            ) {
                Text("OK")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputExample(
    selectedTime: String,
    showInputExample: Boolean,
    onShowButtonClick: () -> Unit,
    onConfirm: (Pair<Int, Int>) -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onShowButtonClick) {
            Text(stringResource(id = R.string.time_select_2))
        }
        OutlinedTextField(
            value = selectedTime,
            onValueChange = {},
            label = { Text(stringResource(id = R.string.input_time_piker)) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )


        if (showInputExample) {
            TimeInput(
                state = timePickerState,
            )
            ElevatedButton(onClick = onDismiss) {
                Text("Cancel")
            }
            ElevatedButton(
                onClick = {
                    onConfirm(Pair(timePickerState.hour, timePickerState.minute))
                }
            ) {
                Text("OK")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialWithDialogExample(
    selectedTime: String,
    showDialWithDialogExample: Boolean,
    onShowButtonClick: () -> Unit,
    onConfirm: (Pair<Int, Int>) -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onShowButtonClick) {
            Text(stringResource(id = R.string.time_select_3))
        }
        OutlinedTextField(
            value = selectedTime,
            onValueChange = {},
            label = { Text(stringResource(id = R.string.dialog_dial_time_piker)) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )


        if (showDialWithDialogExample) {
            TimePickerDialog(
                onDismiss = onDismiss,
                onConfirm = { onConfirm(Pair(timePickerState.hour, timePickerState.minute)) }
            ) {
                // 横にした時若干UI潰れているけど対策が分からない。。。
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    TimePicker(
                        state = timePickerState,
                    )
                }
            }
        }
    }
}

@Composable
private fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("OK")
            }
        },
        text = { content() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdvancedTimePickerExample(
    selectedTime: String,
    showDial: Boolean,
    showAdvancedTimePickerExample: Boolean,
    onShowButtonClick: () -> Unit,
    onShowDialToggle: () -> Unit,
    onConfirm: (Pair<Int, Int>) -> Unit,
    onDismiss: () -> Unit
) {

    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    // アイコンの切り替え
    val toggleIcon = if (showDial) {
        Icons.Filled.EditCalendar
    } else {
        Icons.Filled.AccessTime
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onShowButtonClick) {
            Text(stringResource(id = R.string.time_select_4))
        }
        OutlinedTextField(
            value = selectedTime,
            onValueChange = {},
            label = { Text(stringResource(id = R.string.advanced_time_piker)) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )


        if (showAdvancedTimePickerExample) {
            AdvancedTimePickerDialog(
                onDismiss = { onDismiss() },
                onConfirm = { onConfirm(Pair(timePickerState.hour, timePickerState.minute)) },
                toggle = {
                    IconButton(onClick = onShowDialToggle) {
                        Icon(
                            imageVector = toggleIcon,
                            contentDescription = "Time picker type toggle",
                        )
                    }
                },
            ) {
                if (showDial) {
                    TimePicker(
                        state = timePickerState,
                    )
                } else {
                    TimeInput(
                        state = timePickerState,
                    )
                }
            }
        }
    }
}

@Composable
private fun AdvancedTimePickerDialog(
    title: String = "Select Time",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier =
            Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    TextButton(onClick = onConfirm) { Text("OK") }
                }
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