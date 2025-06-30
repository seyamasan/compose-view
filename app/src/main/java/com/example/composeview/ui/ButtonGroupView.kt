package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.utils.types.ButtonGroupType

@Composable
fun ButtonGroupView(navController: NavHostController?, viewName: String, description: String) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    val options = listOf(
        ButtonGroupType.WORK,
        ButtonGroupType.RESTAURANT,
        ButtonGroupType.COFFEE,
        ButtonGroupType.SEARCH,
        ButtonGroupType.HOME
    )

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
                SingleSelectConnectedButtonGroupWithFlowLayoutSample(
                    options = options,
                    selectedIndex = selectedIndex,
                    onCheckedChange = { selectedIndex = it }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SingleSelectConnectedButtonGroupWithFlowLayoutSample(
    options: List<ButtonGroupType>,
    selectedIndex: Int,
    onCheckedChange: (Int) -> Unit
) {
    FlowRow(
        Modifier.padding(horizontal = 8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        options.forEachIndexed { index, option ->
            ToggleButton(
                checked = selectedIndex == index,
                onCheckedChange = { onCheckedChange(index) },
                shapes =
                    when (index) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
                modifier = Modifier.semantics { role = Role.RadioButton }
            ) {
                Icon(
                    if (selectedIndex == index) option.getCheckedIcon() else option.getUnCheckedIcon(),
                    contentDescription = "${option.label} Icon"
                )
                Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                Text(option.label)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonGroupViewPreview() {
    ComposeViewTheme {
        ButtonGroupView(
            null,
            viewName = stringResource(id = R.string.button_group_view_name),
            description = stringResource(id = R.string.button_group_view_description)
        )
    }
}