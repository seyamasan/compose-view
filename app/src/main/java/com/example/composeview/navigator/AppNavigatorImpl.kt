package com.example.composeview.navigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeview.Greeting
import com.example.composeview.Greeting2
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val navController: NavHostController,
    private val innerPadding: PaddingValues
) : AppNavigator {

    @Composable
    override fun NavigateTo(screen: Screens) {
        NavHost(
            navController = navController,
            startDestination = Screens.FIRST.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // 画面1
            composable(route = Screens.FIRST.name) {
                Greeting(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            // 画面2
            composable(route = Screens.SECOND.name) {
                Greeting2(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}