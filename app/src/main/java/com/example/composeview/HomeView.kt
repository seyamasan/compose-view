package com.example.composeview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavHostController?) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { navController?.navigate(Screens.First("First Viewだよ")) }) {
                Text(text = "ファーストビュー")
            }
            Button(onClick = { navController?.navigate(Screens.Second("Second Viewだよ")) }) {
                Text(text = "セカンドビュー")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    ComposeViewTheme {
        HomeView(null)
    }
}