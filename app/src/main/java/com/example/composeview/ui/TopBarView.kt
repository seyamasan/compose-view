package com.example.composeview.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.composeview.screens.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

/*
* CommonTopAppBar（共通のトップバー）
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(navController: NavHostController?, title: String, enableBack: Boolean) {
    TopAppBar(
        title = { Text(title) },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            if (enableBack) {
                IconButton(onClick = { navController?.navigate(Screens.Home) }) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarViewPreview() {
    ComposeViewTheme {
        TopBarView(null, title = "Test View", enableBack = true)
    }
}