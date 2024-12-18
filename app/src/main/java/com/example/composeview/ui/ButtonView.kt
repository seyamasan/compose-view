package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.utils.SnackbarUtils

@Composable
fun ButtonView(navController: NavHostController?, viewName: String, description: String) {
    val buttonNameList = listOf(
        stringResource(id = R.string.button_name),
        stringResource(id = R.string.filled_tonal_button_name),
        stringResource(id = R.string.outlined_button_name),
        stringResource(id = R.string.elevated_button_name),
        stringResource(id = R.string.text_button_name)
    )

    // ５種類のボタン
    val buttonTypes: List<@Composable (String, () -> Unit) -> Unit> = listOf(
        { name, onClick -> Button(onClick = onClick) { Text(name) } }, // 塗りつぶしのボタン
        { name, onClick -> FilledTonalButton(onClick = onClick) { Text(name) } }, // 塗りつぶしのメインの色より薄い？ボタン
        { name, onClick -> OutlinedButton(onClick = onClick) { Text(name) } }, // 枠線付きボタン
        { name, onClick -> ElevatedButton(onClick = onClick) { Text(name) } }, // 立体ボタン
        { name, onClick -> TextButton(onClick = onClick) { Text(name) } } // テキストボタン
    )

    val scope = rememberCoroutineScope() // コルーチンスコープ
    val snackbarHostState = remember { SnackbarHostState() } // スナックバー

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, viewName, true)
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

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                buttonNameList.forEachIndexed { index, name ->
                    val message = name + stringResource(id = R.string.tapped_button_msg)
                    val buttonContent = buttonTypes[index]

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = name, modifier = Modifier.weight(1f))
                            buttonContent(name) {
                                SnackbarUtils.showSnackbar(scope, snackbarHostState, message)
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
        ButtonView(
            null,
            viewName = stringResource(id = R.string.button_view_name),
            description = stringResource(id = R.string.button_view_description)
        )
    }
}