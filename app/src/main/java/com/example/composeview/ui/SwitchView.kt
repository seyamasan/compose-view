package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme

@Composable
fun SwitchView(navController: NavHostController?, description: String) {
    var checkedSwitchMinimalExample by rememberSaveable { mutableStateOf(false) }
    var checkedSwitchWithIconExample by rememberSaveable { mutableStateOf(false) }
    var checkedSwitchWithCustomColors by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, stringResource(id = R.string.switch_view_name), true)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SwitchMinimalExample(
                    checked = checkedSwitchMinimalExample,
                    onChecked = { checkedSwitchMinimalExample = it }
                )

                SwitchWithIconExample(
                    checked = checkedSwitchWithIconExample,
                    onChecked = { checkedSwitchWithIconExample = it }
                )

                SwitchWithCustomColors(
                    checked = checkedSwitchWithCustomColors,
                    onChecked = { checkedSwitchWithCustomColors = it }
                )
            }
        }
    }
}

@Composable
private fun SwitchMinimalExample(checked: Boolean, onChecked : (Boolean) -> Unit) {
    // 最小限のSwitch
    Switch(
        checked = checked,
        onCheckedChange = { onChecked(it) }
    )
}

@Composable
private fun SwitchWithIconExample(checked: Boolean, onChecked : (Boolean) -> Unit) {
    // アイコン付きSwitch
    Switch(
        checked = checked,
        onCheckedChange = { onChecked(it) },
        thumbContent = if (checked) {
            {
                // サムネイルコンテンツをIconにする
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            null
        }
    )
}

@Composable
private fun SwitchWithCustomColors(checked: Boolean, onChecked : (Boolean) -> Unit) {
    // カスタムカラーのSwitch
    Switch(
        checked = checked,
        onCheckedChange = { onChecked(it) },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ) // カラー設定
    )
}

@Preview(showBackground = true)
@Composable
fun SwitchViewPreview() {
    ComposeViewTheme {
        SwitchView(null, description = stringResource(id = R.string.switch_view_description))
    }
}