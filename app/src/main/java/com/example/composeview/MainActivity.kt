package com.example.composeview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.composeview.navigator.AppNavigatorImpl
import com.example.composeview.ui.theme.ComposeViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeViewTheme {
                val navController = rememberNavController()
                AppNavigatorImpl(navController).NavigateTo()
            }
        }
    }
}