package com.example.composeview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingActionButtonView(navController: NavHostController?, description: String) {
    var showButtons by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.floating_action_button_view_name)) },
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
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd // FABを右下に配置
            ) {
                if (showButtons) {
                    // FABの上に表示するボタン
                    Column(
                        horizontalAlignment = Alignment.End, // ボタンのEnd（右側）を揃える
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 76.dp)
                    ) {
                        // 普通のFAB
                        FloatingActionButton(
                            onClick = {  },
                        ) {
                            Icon(Icons.Filled.Edit, "Floating action button.")
                        }
                        // 小さいのFAB
                        SmallFloatingActionButton(
                            onClick = {  }
                        ) {
                            Icon(Icons.Filled.Edit, "Small floating action button.")
                        }
                        // 大きいFAB
                        LargeFloatingActionButton(
                            onClick = {  }
                        ) {
                            Icon(Icons.Filled.Edit, "Large floating action button")
                        }
                    }
                }
                // 拡張FAB
                ExtendedFloatingActionButton(
                    onClick = { showButtons = !showButtons },
                    icon = { Icon(Icons.Filled.Edit, contentDescription = "Edit") },
                    text = { Text(stringResource(id = R.string.extended_floating_action_button_name)) }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FloatingActionButtonViewPreview() {
    ComposeViewTheme {
        FloatingActionButtonView(null, description = stringResource(id = R.string.floating_action_button_view_description))
    }
}