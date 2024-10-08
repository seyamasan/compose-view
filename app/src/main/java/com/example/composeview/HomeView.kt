package com.example.composeview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeview.navigator.AppNavigatorImpl
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

@Composable
fun HomeView(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate(Screens.First("First Viewだよ")) }) {
            Text(text = "ファーストビュー")
        }
        Button(onClick = { navController.navigate(Screens.Second("Second Viewだよ")) }) {
            Text(text = "セカンドビュー")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    val navController = rememberNavController()
    ComposeViewTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.primary) { innerPadding ->
            AppNavigatorImpl(navController,innerPadding).NavigateTo()
        }
    }
}