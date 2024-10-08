package com.example.composeview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

// 色々Viewの書き方おかしいから後から書き直す
@Composable
fun FirstView(navController: NavHostController, title: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = title)
        Button(onClick = { navController.navigate(Screens.Home) }) {
            Text(text = "戻る")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstViewPreview() {
    val navController = rememberNavController()
    ComposeViewTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.primary) { innerPadding ->
            FirstView(navController = navController, title = "First Viewだよ", modifier = Modifier.padding(innerPadding))
        }
    }
}