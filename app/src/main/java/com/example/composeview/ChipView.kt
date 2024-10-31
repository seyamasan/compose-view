package com.example.composeview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.utils.SnackbarUtils

@Composable
fun ChipView(navController: NavHostController?, description: String) {
    var tappedAssistChip by rememberSaveable { mutableStateOf(false) }
    var inputText by rememberSaveable { mutableStateOf("") }  // 入力したテキストの状態を保持
    var addButtonList by rememberSaveable { mutableStateOf(listOf<String>()) }
    var filterChipSelected by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val addedMsg = stringResource(id = R.string.chip_view_added_msg)

    val suggestionChipList = listOf(
        stringResource(id = R.string.suggestion_chip_cat),
        stringResource(id = R.string.suggestion_chip_dog),
        stringResource(id = R.string.suggestion_chip_people)
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, stringResource(id = R.string.chip_view_name), true)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)
            AssistChipSection(
                tappedAssistChip = tappedAssistChip,
                suggestionChipList = suggestionChipList,
                inputText = inputText,
                addButtonList = addButtonList,
                filterChipSelected = filterChipSelected,
                onTap = { tappedAssistChip = !tappedAssistChip },
                onInputTextChange = { inputText = it },
                onFilterChipSelect = { filterChipSelected = !filterChipSelected },
                onAddedButtonDismiss = { addButtonList = addButtonList - it },
                onAddButtonClick = {
                    addButtonList = addButtonList + inputText
                    inputText = "" // addしたら空にする
                },
                onSnackbarShow = {
                    SnackbarUtils.showSnackbar(scope, snackbarHostState, addedMsg + inputText)
                }
            )
        }
    }
}

@Composable
private fun AssistChipSection(
    tappedAssistChip: Boolean,
    suggestionChipList: List<String>,
    inputText: String,
    addButtonList: List<String>,
    filterChipSelected: Boolean,
    onTap: () -> Unit,
    onInputTextChange: (String) -> Unit,
    onFilterChipSelect: () -> Unit,
    onAddedButtonDismiss:(String) -> Unit,
    onAddButtonClick: () -> Unit,
    onSnackbarShow: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // アシスタントチップ
        AssistChip(
            onClick = onTap,
            label = { Text(stringResource(id = R.string.assist_chip_name)) },
            leadingIcon = { // アイコンを左に表示
                Icon(Icons.Filled.Create, contentDescription = "Create icon.", Modifier.size(AssistChipDefaults.IconSize))
            }
        )

        if (tappedAssistChip) {
            LazyColumn {
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            ChipInputCandidatesView(suggestionChipList) { clickedText ->
                                onInputTextChange(clickedText)
                            }

                            // フィルターチップ
                            FilterChip(
                                modifier = Modifier.padding(horizontal = 5.dp),
                                onClick = onFilterChipSelect,
                                label = { Text(stringResource(id = R.string.filter_chip_name)) },
                                selected = filterChipSelected,
                                leadingIcon = if (filterChipSelected) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Done icon",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                },
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(addButtonList) { name ->
                                    ChipAddedButtonView(
                                        addButtonName = name,
                                        filterChipSelected = filterChipSelected,
                                        suggestionChipList = suggestionChipList,
                                        onDismiss = {
                                            onAddedButtonDismiss(name)
                                        }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(5.dp))

                            ChipTextFieldView(
                                inputText = inputText,
                                onNewText = onInputTextChange,
                                onClick = {
                                    onSnackbarShow()
                                    onAddButtonClick()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChipInputCandidatesView(suggestionChipList: List<String>, onClick: (String) -> Unit) {
    Text(stringResource(id = R.string.chip_view_input_candidates))

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(suggestionChipList) { name ->
            // 候補チップ
            SuggestionChip(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = { onClick(name) },
                label = { Text(name) }
            )
        }
    }
}

@Composable
private fun ChipAddedButtonView(
    addButtonName: String,
    filterChipSelected: Boolean,
    suggestionChipList: List<String>,
    onDismiss: () -> Unit
) {
    // フィルターかける
    if (filterChipSelected && addButtonName in suggestionChipList) {
        return
    }

    // 入力チップ
    InputChip(
        modifier = Modifier.padding(horizontal = 5.dp),
        onClick = onDismiss,
        label = { Text(addButtonName) },
        selected = true,
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Parson icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Close icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}

@Composable
private fun ChipTextFieldView(
    inputText: String,
    onNewText: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(4.dp)
    ) {
        TextField(
            value = inputText,
            onValueChange = { newText -> onNewText(newText) }, // 入力されたら毎回処理走る,
            label = { Text(stringResource(id = R.string.chip_view_text_filed_label)) }, // ラベルを設定
            placeholder = { Text(stringResource(id = R.string.chip_view_text_filed_placeholder)) }, // プレースホルダー
            singleLine = true, // 単一行の入力フィールド
            modifier = Modifier.weight(1f),
        )

        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirm　icon",
                tint = Color.Blue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChipViewPreview() {
    ComposeViewTheme {
        ChipView(null, description = stringResource(id = R.string.chip_view_description))
    }
}