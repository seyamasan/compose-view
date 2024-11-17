package com.example.composeview.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.composeview.screens.Screens
import com.example.composeview.ui.BottomSheetView
import com.example.composeview.ui.BudgeBoxView
import com.example.composeview.ui.ButtonView
import com.example.composeview.ui.CardView
import com.example.composeview.ui.CheckboxView
import com.example.composeview.ui.ChipView
import com.example.composeview.ui.DatePickerView
import com.example.composeview.ui.DialogView
import com.example.composeview.ui.DividerView
import com.example.composeview.ui.FloatingActionButtonView
import com.example.composeview.ui.HomeView
import com.example.composeview.ui.IndicatorView
import com.example.composeview.ui.NavigationDrawerView
import com.example.composeview.ui.SliderView
import com.example.composeview.ui.SwitchView
import com.example.composeview.ui.TimePikerView

class AppNavigatorImpl (private val navController: NavHostController) : AppNavigator {

    @Composable
    override fun NavigateTo() {
        NavHost(
            navController = navController, Screens.Home
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
                    viewName = stringResource(id = buttonView.viewNameResId),
                    description = stringResource(id = buttonView.descriptionResId)
                )
            }

            // FloatingActionButtonのView
            composable<Screens.FloatingActionButtonView> { backStackEntry ->
                val floatingActionButtonView: Screens.FloatingActionButtonView = backStackEntry.toRoute()
                FloatingActionButtonView(
                    navController = navController,
                    viewName = stringResource(id = floatingActionButtonView.viewNameResId),
                    description = stringResource(id = floatingActionButtonView.descriptionResId)
                )
            }

            // CardのView
            composable<Screens.CardView> { backStackEntry ->
                val cardView: Screens.CardView = backStackEntry.toRoute()
                CardView(
                    navController = navController,
                    viewName = stringResource(id = cardView.viewNameResId),
                    description = stringResource(id = cardView.descriptionResId)
                )
            }

            // ChipのView
            composable<Screens.ChipView> { backStackEntry ->
                val chipView: Screens.ChipView = backStackEntry.toRoute()
                ChipView(
                    navController = navController,
                    viewName = stringResource(id = chipView.viewNameResId),
                    description = stringResource(id = chipView.descriptionResId)
                )
            }

            // DialogのView
            composable<Screens.DialogView> { backStackEntry ->
                val dialogView: Screens.DialogView = backStackEntry.toRoute()
                DialogView(
                    navController = navController,
                    viewName = stringResource(id = dialogView.viewNameResId),
                    description = stringResource(id = dialogView.descriptionResId)
                )
            }

            // IndicatorのView
            composable<Screens.IndicatorView> { backStackEntry ->
                val indicatorView: Screens.IndicatorView = backStackEntry.toRoute()
                IndicatorView(
                    navController = navController,
                    viewName = stringResource(id = indicatorView.viewNameResId),
                    description = stringResource(id = indicatorView.descriptionResId)
                )
            }

            // SliderのView
            composable<Screens.SliderView> { backStackEntry ->
                val sliderView: Screens.SliderView = backStackEntry.toRoute()
                SliderView(
                    navController = navController,
                    viewName = stringResource(id = sliderView.viewNameResId),
                    description = stringResource(id = sliderView.descriptionResId)
                )
            }

            // SwitchのView
            composable<Screens.SwitchView> { backStackEntry ->
                val switchView: Screens.SwitchView = backStackEntry.toRoute()
                SwitchView(
                    navController = navController,
                    viewName = stringResource(id = switchView.viewNameResId),
                    description = stringResource(id = switchView.descriptionResId)
                )
            }

            // CheckboxのView
            composable<Screens.CheckboxView> { backStackEntry ->
                val checkboxView: Screens.CheckboxView = backStackEntry.toRoute()
                CheckboxView(
                    navController = navController,
                    viewName = stringResource(id = checkboxView.viewNameResId),
                    description = stringResource(id = checkboxView.descriptionResId)
                )
            }

            // BudgeBoxのView
            composable<Screens.BudgeBoxView> { backStackEntry ->
                val budgeBoxView: Screens.BudgeBoxView = backStackEntry.toRoute()
                BudgeBoxView(
                    navController = navController,
                    viewName = stringResource(id = budgeBoxView.viewNameResId),
                    description = stringResource(id = budgeBoxView.descriptionResId)
                )
            }

            // BottomSheetのView
            composable<Screens.BottomSheetView> { backStackEntry ->
                val bottomSheetView: Screens.BottomSheetView = backStackEntry.toRoute()
                BottomSheetView(
                    navController = navController,
                    viewName = stringResource(id = bottomSheetView.viewNameResId),
                    description = stringResource(id = bottomSheetView.descriptionResId)
                )
            }

            // NavigationDrawerのView
            composable<Screens.NavigationDrawerView> { backStackEntry ->
                val navigationDrawerView: Screens.NavigationDrawerView = backStackEntry.toRoute()
                NavigationDrawerView(
                    navController = navController,
                    viewName = stringResource(id = navigationDrawerView.viewNameResId),
                    description = stringResource(id = navigationDrawerView.descriptionResId)
                )
            }

            // DividerのView
            composable<Screens.DividerView> { backStackEntry ->
                val dividerView: Screens.DividerView = backStackEntry.toRoute()
                DividerView(
                    navController = navController,
                    viewName = stringResource(id = dividerView.viewNameResId),
                    description = stringResource(id = dividerView.descriptionResId)
                )
            }

            // DatePickerのView
            composable<Screens.DatePickerView> { backStackEntry ->
                val datePickerView: Screens.DatePickerView = backStackEntry.toRoute()
                DatePickerView(
                    navController = navController,
                    viewName = stringResource(id = datePickerView.viewNameResId),
                    description = stringResource(id = datePickerView.descriptionResId)
                )
            }

            // TimePikerのView
            composable<Screens.TimePikerView> { backStackEntry ->
                val timePikerView: Screens.TimePikerView = backStackEntry.toRoute()
                TimePikerView(
                    navController = navController,
                    viewName = stringResource(id = timePikerView.viewNameResId),
                    description = stringResource(id = timePikerView.descriptionResId)
                )
            }
        }
    }
}