package com.example.composeview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavHostController?, modifier: Modifier = Modifier) {
    val screenList = listOf(
        Screens.ButtonView(description = stringResource(id = R.string.button_view_description)),
        Screens.FloatingActionButtonView(description = stringResource(id = R.string.floating_action_button_view_description)),
        Screens.CardView(description = stringResource(id = R.string.card_view_description))
    )

    // Scaffoldを使ってtopBarを表示 & innerPaddingをViewのpaddingに適応でbarと重ならないようにしている
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
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
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val name = when (screen) {
            is Screens.ButtonView -> stringResource(id = R.string.button_view_name)
            is Screens.FloatingActionButtonView -> stringResource(id = R.string.floating_action_button_view_name)
            is Screens.CardView -> stringResource(id = R.string.card_view_name)
            else -> {"null"}
        }

        Text(
            text = name,
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