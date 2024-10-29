package com.example.composeview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.utils.SnackbarUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipView(navController: NavHostController?, description: String) {
    var selectedFilterChip by remember { mutableStateOf(false) }

    val chipNameList = listOf(
        stringResource(id = R.string.assist_chip_name),
        stringResource(id = R.string.filter_chip_name),
        stringResource(id = R.string.input_chip_name),
        stringResource(id = R.string.suggestion_chip_name)
    )
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

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

            LazyRow {
                itemsIndexed(chipNameList) { index, name ->
                    val message = name + stringResource(id = R.string.tapped_chip_msg)
                    when (index) {
                        0 -> {
                            AssistChip(
                                onClick = {
                                    SnackbarUtils.showSnackbar(
                                        scope = scope,
                                        snackbarHostState = snackbarHostState,
                                        message = message
                                    )
                                },
                                label = { Text(name) },
                                leadingIcon = { // チップ内の左にアイコンを配置できる
                                    Icon(
                                        Icons.Filled.Settings,
                                        contentDescription = "Setting icon.",
                                        Modifier.size(AssistChipDefaults.IconSize)
                                    )
                                }
                            )
                        }
                        1 -> {
                            // フィルターなのでタップしてOnならInputChipをフィルターかけれるようにしたい
                            FilterChip(
                                onClick = {
                                    selectedFilterChip = !selectedFilterChip
                                    SnackbarUtils.showSnackbar(
                                        scope = scope,
                                        snackbarHostState = snackbarHostState,
                                        message = message
                                    )
                                },
                                label = { Text(name) },
                                selected = selectedFilterChip,
                                leadingIcon = if (selectedFilterChip) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Done icon",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                        }
                        2 -> {
                            // Input Chipはユーザー入力したら追加されるボタンにしたい
                            // タップしたら消したい
                        }
                        3 -> {

                        }
                    }
                }
            }
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