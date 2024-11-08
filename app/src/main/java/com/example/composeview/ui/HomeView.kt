package com.example.composeview.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

@Composable
fun HomeView(navController: NavHostController?, modifier: Modifier = Modifier) {
    val screenList = listOf(
        Screens.ButtonView(viewName = stringResource(id = R.string.button_view_name), description = stringResource(id = R.string.button_view_description)),
        Screens.FloatingActionButtonView(viewName = stringResource(id = R.string.floating_action_button_view_name), description = stringResource(id = R.string.floating_action_button_view_description)),
        Screens.CardView(viewName = stringResource(id = R.string.card_view_name), description = stringResource(id = R.string.card_view_description)),
        Screens.ChipView(viewName = stringResource(id = R.string.chip_view_name), description = stringResource(id = R.string.chip_view_description)),
        Screens.DialogView(viewName = stringResource(id = R.string.dialog_view_name), description = stringResource(id = R.string.dialog_view_description)),
        Screens.IndicatorView(viewName = stringResource(id = R.string.indicator_view_name), description = stringResource(id = R.string.indicator_view_description)),
        Screens.SliderView(viewName = stringResource(id = R.string.slider_view_name), description = stringResource(id = R.string.slider_view_description)),
        Screens.SwitchView(viewName = stringResource(id = R.string.switch_view_name), description = stringResource(id = R.string.switch_view_description)),
        Screens.CheckboxView(viewName = stringResource(id = R.string.checkbox_view_name), description = stringResource(id = R.string.checkbox_view_description)),
        Screens.BudgeBoxView(viewName = stringResource(id = R.string.budge_box_view_name), description = stringResource(id = R.string.budge_box_view_description)),
        Screens.BottomSheetView(viewName = stringResource(id = R.string.bottom_sheet_view_name), description = stringResource(id = R.string.bottom_sheet_view_description)),
        Screens.NavigationDrawerView(viewName = stringResource(id = R.string.navigation_drawer_view_name), description = stringResource(id = R.string.navigation_drawer_view_description))
    )

    // Scaffoldを使ってtopBarを表示 & innerPaddingをViewのpaddingに適応でbarと重ならないようにしている
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, stringResource(id = R.string.app_name), false)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            items(screenList) { screen ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = modifier.padding(vertical = 4.dp),
                    onClick = {
                        navController?.navigate(screen)
                    }
                ) {
                    CardContent(screen)
                }
            }
        }
    }
}

@Composable
private fun CardContent(screen: Any) {
    val viewName = when (screen) {
        is Screens.ButtonView -> stringResource(id = R.string.button_view_name)
        is Screens.FloatingActionButtonView -> stringResource(id = R.string.floating_action_button_view_name)
        is Screens.CardView -> stringResource(id = R.string.card_view_name)
        is Screens.ChipView -> stringResource(id = R.string.chip_view_name)
        is Screens.DialogView -> stringResource(id = R.string.dialog_view_name)
        is Screens.IndicatorView -> stringResource(id = R.string.indicator_view_name)
        is Screens.SliderView -> stringResource(id = R.string.slider_view_name)
        is Screens.SwitchView -> stringResource(id = R.string.switch_view_name)
        is Screens.CheckboxView -> stringResource(id = R.string.checkbox_view_name)
        is Screens.BudgeBoxView -> stringResource(id = R.string.budge_box_view_name)
        is Screens.BottomSheetView -> stringResource(id = R.string.bottom_sheet_view_name)
        is Screens.NavigationDrawerView -> stringResource(id = R.string.navigation_drawer_view_name)
        else -> {"null"}
    }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = viewName,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Show view button"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    ComposeViewTheme {
        HomeView(null)
    }
}