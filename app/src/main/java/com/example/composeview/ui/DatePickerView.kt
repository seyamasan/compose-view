package com.example.composeview.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerView(navController: NavHostController?, viewName: String, description: String) {
    var showDatePickerExample by rememberSaveable { mutableStateOf(false) }
    val datePickerExampleState = rememberDatePickerState()
    var selectedDate by rememberSaveable { mutableStateOf<Long?>(null) }
    var showModalDatePiker by rememberSaveable { mutableStateOf(false) }
    val datePickerModalState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

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

            // 選択のみのカレンダー
            DatePickerExample(
                showDatePicker = showDatePickerExample,
                datePickerState = datePickerExampleState,
                onClick = {
                    showDatePickerExample = !showDatePickerExample
                },
                onDismiss = { showDatePickerExample = false }
            )

            // 選択と入力ができるカレンダー
            DatePickerFieldToModal(
                selectedDate = selectedDate,
                showModal = showModalDatePiker,
                onClick = { showModalDatePiker = it },
                onSelected = { selectedDate = it },
                datePickerModalState = datePickerModalState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerExample(
    showDatePicker: Boolean,
    datePickerState: DatePickerState,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val selectedDate = datePickerState.selectedDateMillis?.let {
        // 年月日を選択した後に、この中の処理が走る
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text(stringResource(id = R.string.select_only_calendar)) },
            readOnly = true, // リードオンリーで文字入力不可
            trailingIcon = {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "DateRange icon"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Dialog(onDismissRequest = onDismiss) {
                Column(
                    modifier = Modifier
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState()) // 縦スクロール付けないと横にした時見切れる
                ) {
                    // カレンダー
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerFieldToModal(
    selectedDate: Long?,
    showModal: Boolean,
    onClick: (Boolean) -> Unit,
    onSelected: (Long?) -> Unit,
    datePickerModalState: DatePickerState
) {
    OutlinedTextField(
        value = selectedDate?.let { convertMillisToDate(it) } ?: "",
        onValueChange = { },
        label = { Text(stringResource(id = R.string.select_and_input_calendar)) },
        placeholder = { Text("YYYY/MM/DD") },
        trailingIcon = {
            Icon(Icons.Default.DateRange, contentDescription = "DateRange icon")
        },
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(selectedDate) {
                awaitEachGesture {
                    // Modifier.clickable はテキスト フィールドでは機能しないため、
                    // テキスト フィールドがメイン パスでイベントを消費する前に、
                    // 初期パスで Modifier.pointerInput を使用してイベントを監視します。
                    // つまり、Modifier.clickableではダメらしい。
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        onClick(true)
                    }
                }
            }
    )

    if (showModal) {
        DatePickerModalInput(
            onDateSelected = { onSelected(it) },
            onDismiss = { onClick(false) },
            datePickerModalState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerModalInput(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    datePickerModalState: DatePickerState
) {
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerModalState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            DatePicker(state = datePickerModalState)
        }
    }
}

private fun convertMillisToDate(millis: Long): String {
    // yyyy/MM/ddのフォーマットに変換
    val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview(showBackground = true)
@Composable
fun DatePickerViewPreview() {
    ComposeViewTheme {
        DatePickerView(
            null,
            viewName = stringResource(id = R.string.date_picker_view_name),
            description = stringResource(id = R.string.date_picker_view_description)
        )
    }
}