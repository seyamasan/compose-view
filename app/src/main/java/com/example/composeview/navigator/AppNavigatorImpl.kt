package com.example.composeview.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.composeview.FirstView
import com.example.composeview.HomeView
import com.example.composeview.SecondView
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val navController: NavHostController
) : AppNavigator {

    @Composable
    override fun NavigateTo() {
        NavHost(
            navController = navController,Screens.Home
        ) {
            // ホーム
            composable<Screens.Home> {
                HomeView(
                    navController = navController
                )
            }

            // 画面1
            composable<Screens.First> { backStackEntry ->
                val first: Screens.First = backStackEntry.toRoute()
                FirstView(
                    navController = navController,
                    title = first.title
                )
            }

            // 画面2
            composable<Screens.Second> { backStackEntry ->
                val second: Screens.Second = backStackEntry.toRoute()
                SecondView(
                    navController = navController,
                    title = second.title
                )
            }
        }
    }
}