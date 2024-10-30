package com.example.composeview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipView(navController: NavHostController?, description: String) {
    var tappedAssistChip by rememberSaveable { mutableStateOf(false) }
    var inputText by rememberSaveable { mutableStateOf("") }  // テキストの状態を保持
    var addButtonList by rememberSaveable { mutableStateOf(listOf<String>()) }

    val suggestionChipList = listOf(
        stringResource(id = R.string.suggestion_chip_cat),
        stringResource(id = R.string.suggestion_chip_dog),
        stringResource(id = R.string.suggestion_chip_human)
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.chip_view_name)) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController?.navigate(Screens.Home) }) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)

            AssistChip(
                onClick = {
                    tappedAssistChip = !tappedAssistChip
                },
                label = { Text(stringResource(id = R.string.assist_chip_name)) },
                leadingIcon = { // チップ内の左にアイコンを配置できる
                    Icon(
                        Icons.Filled.Create,
                        contentDescription = "Create icon.",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )

            if (tappedAssistChip) {
                Text(stringResource(id = R.string.chip_view_input_candidates))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    items(suggestionChipList) { name ->
                        SuggestionChip(
                            onClick = { inputText = name },
                            label = { Text(name) }
                        )
                    }
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    items(addButtonList) { name ->
                        AddedButtonView(addButtonName = name)
                    }
                }

                Row {
                    TextField(
                        value = inputText,
                        onValueChange = { newText -> inputText = newText }, // 入力されたら毎回処理走る
                        label = { Text(stringResource(id = R.string.chip_view_text_filed_label)) }, // ラベルを設定
                        placeholder = { Text(stringResource(id = R.string.chip_view_text_filed_placeholder)) }, // プレースホルダー
                        singleLine = true // 単一行の入力フィールド
                    )

                    ElevatedButton(
                        onClick = {
                            addButtonList = addButtonList + inputText
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.chip_view_add_button)
                        )
                    }
                }
            }

        }
    }
}

@Composable
private fun AddedButtonView(addButtonName: String) {
    var inputChipEnabled by rememberSaveable { mutableStateOf(false) }

    InputChip(
        onClick = {
            inputChipEnabled = !inputChipEnabled
        },
        label = { Text(addButtonName) },
        selected = inputChipEnabled,
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

@Preview(showBackground = true)
@Composable
fun ChipViewPreview() {
    ComposeViewTheme {
        ChipView(null, description = stringResource(id = R.string.chip_view_description))
    }
}