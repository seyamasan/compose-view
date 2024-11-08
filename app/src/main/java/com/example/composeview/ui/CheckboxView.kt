package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.viewModel.CheckboxViewModel

@Composable
fun CheckboxView(
    navController: NavHostController?,
    viewName: String,
    description: String,
    checkboxViewModel: CheckboxViewModel = viewModel()
) {
    var checkedCheckboxMinimalExample by rememberSaveable { mutableStateOf(false) }
    // LiveDataをStateとしてObserve
    val childCheckedStates by checkboxViewModel.childCheckedStates.observeAsState(listOf(false, false, false))

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
                    CheckboxMinimalExample(
                        checked = checkedCheckboxMinimalExample,
                        onChecked = { checkedCheckboxMinimalExample = it }
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    CheckboxParentExample(
                        childCheckedStates = childCheckedStates,
                        onChildCheckedChange = { index, isChecked ->
                            checkboxViewModel.updateChildState(index, isChecked)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CheckboxMinimalExample(checked: Boolean, onChecked : (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            stringResource(id = R.string.minimal_checkbox_name)
        )
        // チェックボックス
        Checkbox(
            checked = checked,
            onCheckedChange = { onChecked(it) }
        )
    }

    Text(
        if (checked) {
            stringResource(id = R.string.checkbox_is_checked)
        } else {
            stringResource(id = R.string.checkbox_is_uncheck)
        }
    )
}

@Composable
private fun CheckboxParentExample(
    childCheckedStates: List<Boolean>,
    onChildCheckedChange: (Int, Boolean) -> Unit
) {
    // 子チェックボックスの状態に基づいて処理
    val parentState = when {
        childCheckedStates.all { it } -> ToggleableState.On // 全てtrueならOn
        childCheckedStates.none { it } -> ToggleableState.Off // 全てfalseならOff
        else -> ToggleableState.Indeterminate // それ以外は不確定
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(stringResource(id = R.string.select_all_checkbox_name))
            // 親3ステートチェックボックス(ON or OFF or 不確定)
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val newState = parentState != ToggleableState.On
                    childCheckedStates.forEachIndexed { index, _ ->
                        onChildCheckedChange(index, newState)
                    }
                }
            )
        }

        // 子チェックボックス
        childCheckedStates.forEachIndexed { index, checked ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("${stringResource(id = R.string.option_checkbox_name)} ${index + 1}")
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        onChildCheckedChange(index, isChecked)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckboxViewPreview() {
    ComposeViewTheme {
        CheckboxView(
            null,
            viewName = stringResource(id = R.string.checkbox_view_name),
            description = stringResource(id = R.string.checkbox_view_description)
        )
    }
}