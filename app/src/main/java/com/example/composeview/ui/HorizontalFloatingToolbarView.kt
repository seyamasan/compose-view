package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.utils.SnackbarUtils

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HorizontalFloatingToolbarView(navController: NavHostController?, viewName: String, description: String) {
    var expanded by rememberSaveable { mutableStateOf(true) }
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val personIconTappedMessage = stringResource(id = R.string.horizontal_floating_toolbar_view_icon_tapped, "Person")
    val editIconTappedMessage = stringResource(id = R.string.horizontal_floating_toolbar_view_icon_tapped, "Edit")
    val favoriteIconTappedMessage = stringResource(id = R.string.horizontal_floating_toolbar_view_icon_tapped, "Favorite")
    val moreVertIconTappedMessage = stringResource(id = R.string.horizontal_floating_toolbar_view_icon_tapped, "MoreVert")

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, viewName, true)
        },
        floatingActionButton = {
            HorizontalFloatingToolbar(
                expanded = expanded,
                floatingActionButton = {
                    FloatingToolbarDefaults.VibrantFloatingActionButton(
                        onClick = { expanded = !expanded }
                    ) {
                        Icon(Icons.Filled.Add, "Add Icon")
                    }
                },
                colors = vibrantColors,
                content = {
                    IconButton(onClick = { SnackbarUtils.showSnackbar(scope, snackbarHostState, personIconTappedMessage) }) {
                        Icon(Icons.Filled.Person, contentDescription = "Person Icon")
                    }
                    IconButton(onClick = { SnackbarUtils.showSnackbar(scope, snackbarHostState, editIconTappedMessage) }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit Icon")
                    }
                    IconButton(onClick = { SnackbarUtils.showSnackbar(scope, snackbarHostState, favoriteIconTappedMessage) }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite Icon")
                    }
                    IconButton(onClick = { SnackbarUtils.showSnackbar(scope, snackbarHostState, moreVertIconTappedMessage) }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "MoreVert Icon")
                    }
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {  innerPadding ->
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

@Preview(showBackground = true)
@Composable
fun HorizontalFloatingToolbarViewPreview() {
    ComposeViewTheme {
        HorizontalFloatingToolbarView(
            null,
            viewName = stringResource(id = R.string.horizontal_floating_toolbar_view_name),
            description = stringResource(id = R.string.horizontal_floating_toolbar_view_description)
        )
    }
}