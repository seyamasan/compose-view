package com.example.composeview.navigator

import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.composeview.ui.ButtonView
import com.example.composeview.ui.CardView
import com.example.composeview.ui.ChipView
import com.example.composeview.ui.DialogView
import com.example.composeview.ui.FloatingActionButtonView
import com.example.composeview.ui.HomeView
import com.example.composeview.ui.IndicatorView
import com.example.composeview.ui.SliderView

class AppNavigatorImpl (private val navController: NavHostController) : AppNavigator {

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

            // DialogのView
            composable<Screens.DialogView> { backStackEntry ->
                val dialogView: Screens.DialogView = backStackEntry.toRoute()
                DialogView(
                    navController = navController,
                    description = dialogView.description
                )
            }

            // IndicatorのView
            composable<Screens.IndicatorView> { backStackEntry ->
                val indicatorView: Screens.IndicatorView = backStackEntry.toRoute()
                IndicatorView(
                    navController = navController,
                    description = indicatorView.description
                )
            }

            // SliderのView
            composable<Screens.SliderView> { backStackEntry ->
                val sliderView: Screens.SliderView = backStackEntry.toRoute()
                SliderView(
                    navController = navController,
                    description = sliderView.description
                )
            }
        }
    }
}