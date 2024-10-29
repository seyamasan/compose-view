package com.example.composeview.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.composeview.ButtonView
import com.example.composeview.CardView
import com.example.composeview.ChipView
import com.example.composeview.FloatingActionButtonView
import com.example.composeview.HomeView
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

            // ButtonのView
            composable<Screens.ButtonView> { backStackEntry ->
                val buttonView: Screens.ButtonView = backStackEntry.toRoute()
                ButtonView(
                    navController = navController,
                    description = buttonView.description
                )
            }

            // FloatingActionButtonのView
            composable<Screens.FloatingActionButtonView> { backStackEntry ->
                val floatingActionButtonView: Screens.FloatingActionButtonView = backStackEntry.toRoute()
                FloatingActionButtonView(
                    navController = navController,
                    description = floatingActionButtonView.description
                )
            }

            // CardのView
            composable<Screens.CardView> { backStackEntry ->
                val cardView: Screens.CardView = backStackEntry.toRoute()
                CardView(
                    navController = navController,
                    description = cardView.description
                )
            }

            // ChipのView
            composable<Screens.ChipView> { backStackEntry ->
                val chipView: Screens.ChipView = backStackEntry.toRoute()
                ChipView(
                    navController = navController,
                    description = chipView.description
                )
            }
        }
    }
}