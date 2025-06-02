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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme

@Composable
fun SplitButtonView(navController: NavHostController?, viewName: String, description: String) {
    var isChecked by rememberSaveable { mutableStateOf(false) }

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
                FilledSplitButtonSample(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun FilledSplitButtonSample(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    SplitButtonLayout(
        leadingButton = {
            SplitButtonDefaults.LeadingButton(
                onClick = { /* Do Nothing */ },
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.Send,
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = "Send Icon",
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(id = R.string.leading_button_name))
            }
        },
        trailingButton = {
            Box {
                SplitButtonDefaults.TrailingButton(
                    checked = checked,
                    onCheckedChange = onCheckedChange,
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
                        contentDescription = "Keyboard Arrow Down Icon"
                    )
                }

                DropdownMenu(
                    expanded = checked,
                    onDismissRequest = { onCheckedChange(false) }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.set_sending_date_and_time)) },
                        onClick = {
                            onCheckedChange(false)
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SplitButtonViewPreview() {
    ComposeViewTheme {
        SplitButtonView(
            null,
            viewName = stringResource(id = R.string.split_button_view_name),
            description = stringResource(id = R.string.split_button_view_description)
        )
    }
}