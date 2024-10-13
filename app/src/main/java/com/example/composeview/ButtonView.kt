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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

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
                                        onClick = { }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // 塗りつぶしのメインの色より薄い？ボタン
                                1 -> {
                                    FilledTonalButton(
                                        onClick = { }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // 枠線付きボタン
                                2 -> {
                                    OutlinedButton(
                                        onClick = { }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // 立体ボタン
                                3 -> {
                                    ElevatedButton(
                                        onClick = { }
                                    ) {
                                        Text(
                                            text = name
                                        )
                                    }
                                }
                                // テキストボタン
                                4 -> {
                                    TextButton(
                                        onClick = { }
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