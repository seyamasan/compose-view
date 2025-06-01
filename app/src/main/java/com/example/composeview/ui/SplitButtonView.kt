package com.example.composeview.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SplitButtonView(navController: NavHostController?, viewName: String, description: String) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, viewName, true)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                FilledSplitButtonSample()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FilledSplitButtonSample() {
    var checked by remember { mutableStateOf(false) }

    SplitButtonLayout(
        leadingButton = {
            SplitButtonDefaults.LeadingButton(
                onClick = { /* Do Nothing */ },
            ) {
                Icon(
                    Icons.Filled.Edit,
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = "Edit Icon",
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Edit")
            }
        },
        trailingButton = {
            SplitButtonDefaults.TrailingButton(
                checked = checked,
                onCheckedChange = { checked = it },
                modifier =
                    Modifier.semantics {
                        stateDescription = if (checked) "Expanded" else "Collapsed"
                        contentDescription = "Toggle Button"
                    },
            ) {
                val rotation: Float by
                animateFloatAsState(
                    targetValue = if (checked) 180f else 0f,
                    label = "Trailing Icon Rotation"
                )
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    modifier =
                        Modifier.size(SplitButtonDefaults.TrailingIconSize).graphicsLayer {
                            this.rotationZ = rotation
                        },
                    contentDescription = "Localized description"
                )
            }
        }
    )
}