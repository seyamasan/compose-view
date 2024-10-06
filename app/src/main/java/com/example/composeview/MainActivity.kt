package com.example.composeview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeViewTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.primary) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavigatorImpl(navController,innerPadding).NavigateTo(screen = Screens.FIRST)
                }
            }
        }
    }
}

@Composable
fun Greeting(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(onClick = { navController.navigate("SECOND") }) {
        Text(text = "Go to 2nd Screen")
    }
}

@Composable
fun Greeting2(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(onClick = { navController.navigate("FIRST")}) {
        Text(text = "Go to 1st Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    ComposeViewTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.primary) { innerPadding ->
            AppNavigatorImpl(navController,innerPadding).NavigateTo(screen = Screens.FIRST)
        }
    }
}