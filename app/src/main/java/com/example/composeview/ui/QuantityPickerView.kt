package com.example.composeview.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composeview.QuantityPickerState
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.viewModel.QuantityPickerViewModel

@Composable
fun QuantityPickerView(
    navController: NavHostController?,
    viewName: String,
    description: String,
    viewModel: QuantityPickerViewModel = viewModel()
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, viewName, true)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            Text(text = description)
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            QuantityPicker(state = viewModel.quantityPickerState)
        }
    }
}

@Composable
fun QuantityPicker(state: QuantityPickerState, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        IconButton(
            enabled = !state.isMinQuantity(), // このやり方自分のプロジェクトでも使えそう
            onClick = { state.decrease() }
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease quantity" // これただの説明じゃなくてテストで使うから超重要
            )
        }

        Text(
            text = state.quantity.toString(),
            style = MaterialTheme.typography.titleLarge
        )

        IconButton(
            enabled = !state.isMaxQuantity(),
            onClick = { state.increase() }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase quantity"
            )
        }

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(fontScale = 2.0f)
@Composable
fun QuantityPickerViewPreview() {
    ComposeViewTheme {
        QuantityPickerView(
            null,
            viewName = stringResource(id = R.string.quantity_picker_view_name),
            description = stringResource(id = R.string.quantity_picker_view_description)
        )
    }
}
