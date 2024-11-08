package com.example.composeview.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.composeview.ui.BottomSheetView
import com.example.composeview.ui.BudgeBoxView
import com.example.composeview.ui.ButtonView
import com.example.composeview.ui.CardView
import com.example.composeview.ui.CheckboxView
import com.example.composeview.ui.ChipView
import com.example.composeview.ui.DialogView
import com.example.composeview.ui.FloatingActionButtonView
import com.example.composeview.ui.HomeView
import com.example.composeview.ui.IndicatorView
import com.example.composeview.ui.NavigationDrawerView
import com.example.composeview.ui.SliderView
import com.example.composeview.ui.SwitchView

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
                    viewName = buttonView.viewName,
                    description = buttonView.description
                )
            }

            // FloatingActionButtonのView
            composable<Screens.FloatingActionButtonView> { backStackEntry ->
                val floatingActionButtonView: Screens.FloatingActionButtonView = backStackEntry.toRoute()
                FloatingActionButtonView(
                    navController = navController,
                    viewName = floatingActionButtonView.viewName,
                    description = floatingActionButtonView.description
                )
            }

            // CardのView
            composable<Screens.CardView> { backStackEntry ->
                val cardView: Screens.CardView = backStackEntry.toRoute()
                CardView(
                    navController = navController,
                    viewName = cardView.viewName,
                    description = cardView.description
                )
            }

            // ChipのView
            composable<Screens.ChipView> { backStackEntry ->
                val chipView: Screens.ChipView = backStackEntry.toRoute()
                ChipView(
                    navController = navController,
                    viewName = chipView.viewName,
                    description = chipView.description
                )
            }

            // DialogのView
            composable<Screens.DialogView> { backStackEntry ->
                val dialogView: Screens.DialogView = backStackEntry.toRoute()
                DialogView(
                    navController = navController,
                    viewName = dialogView.viewName,
                    description = dialogView.description
                )
            }

            // IndicatorのView
            composable<Screens.IndicatorView> { backStackEntry ->
                val indicatorView: Screens.IndicatorView = backStackEntry.toRoute()
                IndicatorView(
                    navController = navController,
                    viewName = indicatorView.viewName,
                    description = indicatorView.description
                )
            }

            // SliderのView
            composable<Screens.SliderView> { backStackEntry ->
                val sliderView: Screens.SliderView = backStackEntry.toRoute()
                SliderView(
                    navController = navController,
                    viewName = sliderView.viewName,
                    description = sliderView.description
                )
            }

            // SwitchのView
            composable<Screens.SwitchView> { backStackEntry ->
                val switchView: Screens.SwitchView = backStackEntry.toRoute()
                SwitchView(
                    navController = navController,
                    viewName = switchView.viewName,
                    description = switchView.description
                )
            }

            // CheckboxのView
            composable<Screens.CheckboxView> { backStackEntry ->
                val checkboxView: Screens.CheckboxView = backStackEntry.toRoute()
                CheckboxView(
                    navController = navController,
                    viewName = checkboxView.viewName,
                    description = checkboxView.description
                )
            }

            // BudgeBoxのView
            composable<Screens.BudgeBoxView> { backStackEntry ->
                val budgeBoxView: Screens.BudgeBoxView = backStackEntry.toRoute()
                BudgeBoxView(
                    navController = navController,
                    viewName = budgeBoxView.viewName,
                    description = budgeBoxView.description
                )
            }

            // BottomSheetのView
            composable<Screens.BottomSheetView> { backStackEntry ->
                val bottomSheetView: Screens.BottomSheetView = backStackEntry.toRoute()
                BottomSheetView(
                    navController = navController,
                    viewName = bottomSheetView.viewName,
                    description = bottomSheetView.description
                )
            }

            // NavigationDrawerのView
            composable<Screens.NavigationDrawerView> { backStackEntry ->
                val navigationDrawerView: Screens.NavigationDrawerView = backStackEntry.toRoute()
                NavigationDrawerView(
                    navController = navController,
                    viewName = navigationDrawerView.viewName,
                    description = navigationDrawerView.description
                )
            }
        }
    }
}