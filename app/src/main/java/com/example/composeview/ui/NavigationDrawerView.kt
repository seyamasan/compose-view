package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerView(navController: NavHostController?, viewName: String, description: String) {
    var settingClickState by rememberSaveable { mutableStateOf(listOf(false, false, false)) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // ドロワーの開け閉めを制御
    val scope = rememberCoroutineScope()

    val settings = listOf(
        Pair(stringResource(id = R.string.navigation_drawer_account), Icons.Filled.AccountCircle),
        Pair(stringResource(id = R.string.navigation_drawer_notification), Icons.Filled.Notifications),
        Pair(stringResource(id = R.string.navigation_drawer_favorite), Icons.Filled.Favorite)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = R.string.navigation_drawer_settings_title),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                HorizontalDivider()

                settings.forEachIndexed { index, setting ->
                    NavigationDrawerItem(
                        icon = { Icon(setting.second, contentDescription = setting.first) },
                        label = { Text(setting.first) },
                        selected = settingClickState[index],
                        onClick = {
                            settingClickState = List(settingClickState.size) { i -> i == index }
                        }
                    )
                }
            }
        },
//        gesturesEnabled = false // ユーザーのジェスチャーを制御できる
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopBarView(navController, viewName, true)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text(text = stringResource(id = R.string.navigation_drawer_open_settings_sheet))},
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings icon.") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerViewPreview() {
    ComposeViewTheme {
        NavigationDrawerView(
            null,
            viewName = stringResource(id = R.string.navigation_drawer_view_name),
            description = stringResource(id = R.string.navigation_drawer_view_description)
        )
    }
}