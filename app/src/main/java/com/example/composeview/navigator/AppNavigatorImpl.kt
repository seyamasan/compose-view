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
import com.example.composeview.ui.HorizontalFloatingToolbarView
import com.example.composeview.ui.IndicatorView
import com.example.composeview.ui.NavigationDrawerView
import com.example.composeview.ui.QuantityPickerView
import com.example.composeview.ui.SliderView
import com.example.composeview.ui.SplitButtonView
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
                val screen: Screens.ButtonView = backStackEntry.toRoute()
                ButtonView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // FloatingActionButtonのView
            composable<Screens.FloatingActionButtonView> { backStackEntry ->
                val screen: Screens.FloatingActionButtonView = backStackEntry.toRoute()
                FloatingActionButtonView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // CardのView
            composable<Screens.CardView> { backStackEntry ->
                val screen: Screens.CardView = backStackEntry.toRoute()
                CardView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // ChipのView
            composable<Screens.ChipView> { backStackEntry ->
                val screen: Screens.ChipView = backStackEntry.toRoute()
                ChipView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // DialogのView
            composable<Screens.DialogView> { backStackEntry ->
                val screen: Screens.DialogView = backStackEntry.toRoute()
                DialogView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // IndicatorのView
            composable<Screens.IndicatorView> { backStackEntry ->
                val screen: Screens.IndicatorView = backStackEntry.toRoute()
                IndicatorView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // SliderのView
            composable<Screens.SliderView> { backStackEntry ->
                val screen: Screens.SliderView = backStackEntry.toRoute()
                SliderView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // SwitchのView
            composable<Screens.SwitchView> { backStackEntry ->
                val screen: Screens.SwitchView = backStackEntry.toRoute()
                SwitchView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // CheckboxのView
            composable<Screens.CheckboxView> { backStackEntry ->
                val screen: Screens.CheckboxView = backStackEntry.toRoute()
                CheckboxView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // BudgeBoxのView
            composable<Screens.BudgeBoxView> { backStackEntry ->
                val screen: Screens.BudgeBoxView = backStackEntry.toRoute()
                BudgeBoxView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // BottomSheetのView
            composable<Screens.BottomSheetView> { backStackEntry ->
                val screen: Screens.BottomSheetView = backStackEntry.toRoute()
                BottomSheetView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // NavigationDrawerのView
            composable<Screens.NavigationDrawerView> { backStackEntry ->
                val screen: Screens.NavigationDrawerView = backStackEntry.toRoute()
                NavigationDrawerView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // DividerのView
            composable<Screens.DividerView> { backStackEntry ->
                val screen: Screens.DividerView = backStackEntry.toRoute()
                DividerView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // DatePickerのView
            composable<Screens.DatePickerView> { backStackEntry ->
                val screen: Screens.DatePickerView = backStackEntry.toRoute()
                DatePickerView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // TimePikerのView
            composable<Screens.TimePikerView> { backStackEntry ->
                val screen: Screens.TimePikerView = backStackEntry.toRoute()
                TimePikerView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // QuantityPickerView
            composable<Screens.QuantityPickerView> { backStackEntry ->
                val screen: Screens.QuantityPickerView = backStackEntry.toRoute()
                QuantityPickerView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // SplitButtonView
            composable<Screens.SplitButtonView> { backStackEntry ->
                val screen: Screens.SplitButtonView = backStackEntry.toRoute()
                SplitButtonView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }

            // HorizontalFloatingToolbarView
            composable<Screens.HorizontalFloatingToolbarView> { backStackEntry ->
                val screen: Screens.HorizontalFloatingToolbarView = backStackEntry.toRoute()
                HorizontalFloatingToolbarView(
                    navController = navController,
                    viewName = stringResource(id = screen.viewNameResId),
                    description = stringResource(id = screen.descriptionResId)
                )
            }
        }
    }
}