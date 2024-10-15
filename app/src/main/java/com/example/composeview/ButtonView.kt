package com.example.composeview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
fun ButtonView(navController: NavHostController?, description: String) {
    val buttonNameList = listOf(
        stringResource(id = R.string.button_name),
        stringResource(id = R.string.filled_tonal_button_name),
        stringResource(id = R.string.outlined_button_name),
        stringResource(id = R.string.elevated_button_name),
        stringResource(id = R.string.text_button_name)
    )
    val scope = rememberCoroutineScope() // コルーチンスコープ
    val snackbarHostState = remember { SnackbarHostState() } // スナックバー

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.button_view_name)) },
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
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(buttonNameList) { index, name ->
                    val message = name + stringResource(id = R.string.tapped_button_msg)

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        ),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        Row (
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                        )  {
                            Text(
                                text = name,
                                modifier = Modifier.weight(1f)
                            )
                            when(index) {
                                0 -> {
                                    // 塗りつぶしのボタン
                                    Button(
                                        onClick = {
                                            SnackbarUtils.showSnackbar(
                                                scope = scope,
                                                snackbarHostState = snackbarHostState,
                                                message = message
                                            )
                                        },
//                                        enabled = false, // 活性/非活性を制御できる
//                                        colors = ButtonDefaults.buttonColors(
//                                            containerColor = Color.Red,
//                                            contentColor = Color.Black
//                                        ), // ボタンの色を変えることができる
//                                        contentPadding = PaddingValues(32.dp) // ボタン内のpadding値を設定できる
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // 塗りつぶしのメインの色より薄い？ボタン
                                1 -> {
                                    FilledTonalButton(
                                        onClick = {
                                            SnackbarUtils.showSnackbar(
                                                scope = scope,
                                                snackbarHostState = snackbarHostState,
                                                message = message
                                            )
                                        }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // 枠線付きボタン
                                2 -> {
                                    OutlinedButton(
                                        onClick = {
                                            SnackbarUtils.showSnackbar(
                                                scope = scope,
                                                snackbarHostState = snackbarHostState,
                                                message = message
                                            )
                                        }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // 立体ボタン
                                3 -> {
                                    ElevatedButton(
                                        onClick = {
                                            SnackbarUtils.showSnackbar(
                                                scope = scope,
                                                snackbarHostState = snackbarHostState,
                                                message = message
                                            )
                                        }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // テキストボタン
                                4 -> {
                                    TextButton(
                                        onClick = {
                                            SnackbarUtils.showSnackbar(
                                                scope = scope,
                                                snackbarHostState = snackbarHostState,
                                                message = message
                                            )
                                        }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonViewPreview() {
    ComposeViewTheme {
        ButtonView(null, description = stringResource(id = R.string.button_view_description))
    }
}