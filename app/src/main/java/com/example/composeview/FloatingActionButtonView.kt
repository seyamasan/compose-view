package com.example.composeview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.utils.SnackbarUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingActionButtonView(navController: NavHostController?, description: String) {
    val buttonNameList = listOf(
        stringResource(id = R.string.fab_name),
        stringResource(id = R.string.small_fab_name),
        stringResource(id = R.string.large_name),
        stringResource(id = R.string.extended_fab)
    )
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val messageEnding = stringResource(id = R.string.tapped_button_msg)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.floating_action_button_view_name)) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController?.navigate(Screens.Home) }) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            when (selectedIndex) {
                0 -> {
                    // 普通のFAB
                    FloatingActionButton(
                        onClick = {
                            SnackbarUtils.showSnackbar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                message = buttonNameList[0] + messageEnding
                            )
                        },
//                        containerColor = Color.Magenta, // ボタンの背景色を変更できる
//                        contentColor = Color.LightGray, // ボタンの中の要素の色帰ることができる
//                        shape = CircleShape // 角丸にできる
                    ) {
                        Icon(Icons.Filled.Edit, "Floating ActionButton")
                    }
                }
                1 -> {
                    // 小さいのFAB
                    SmallFloatingActionButton(
                        onClick = {
                            SnackbarUtils.showSnackbar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                message = buttonNameList[1] + messageEnding
                            )
                        }
                    ) {
                        Icon(Icons.Filled.Edit, "Small floating action button.")
                    }
                }
                2 -> {
                    // 大きいFAB
                    LargeFloatingActionButton(
                        onClick = {
                            SnackbarUtils.showSnackbar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                message = buttonNameList[2] + messageEnding
                            )
                        }
                    ) {
                        Icon(Icons.Filled.Edit, "Large floating action button")
                    }
                }
                3 -> {
                    // 拡張FAB
                    ExtendedFloatingActionButton(
                        onClick = {
                            SnackbarUtils.showSnackbar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                message = buttonNameList[3] + messageEnding
                            )
                        },
                        icon = { Icon(Icons.Filled.Edit, contentDescription = "Extended Floating ActionButton") },
                        text = { Text(stringResource(id = R.string.extended_fab)) }
                    )
                }
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(buttonNameList) { index, name ->
                    ElevatedButton(
                        onClick = {
                            selectedIndex = index
                        }
                    ) {
                        Text(
                            text = name
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FloatingActionButtonViewPreview() {
    ComposeViewTheme {
        FloatingActionButtonView(null, description = stringResource(id = R.string.floating_action_button_view_description))
    }
}