package com.example.composeview.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme

@Composable
fun DividerView(navController: NavHostController?, viewName: String, description: String) {
    var showList by rememberSaveable { mutableStateOf(false) }

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

            VerticalDividerExample {
                showList = it
            }

            if (showList) {
                HorizontalDividerExample()
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.divider_view_supporting_text),
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun VerticalDividerExample(onToggleList: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.divider_view_on_text),
            modifier = Modifier.clickable { onToggleList(true) },
            color = Color.Blue
        )
        VerticalDivider(color = MaterialTheme.colorScheme.secondary) // 縦分割線
        Text(
            text = stringResource(id = R.string.divider_view_off_text),
            modifier = Modifier.clickable { onToggleList(false) },
            color = Color.Red
        )
    }
}

@Composable
private fun HorizontalDividerExample() {
    LazyColumn {
        items(50) { index ->
            Text(
                text = "Item ${index + 1}",
                modifier = Modifier.padding(vertical = 8.dp)
            )
            if (index < 49) { // 最後の項目の後にDividerを表示しない
                HorizontalDivider(thickness = 1.dp, color = Color.Gray) // 横分割線
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DividerViewPreview() {
    ComposeViewTheme {
        DividerView(
            null,
            viewName = stringResource(id = R.string.divider_view_name),
            description = stringResource(id = R.string.divider_view_description)
        )
    }
}