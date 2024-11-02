package com.example.composeview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.composeview.ui.theme.ComposeViewTheme

private enum class DialogType { ALERT, MINIMAL, IMAGE }

@Composable
fun DialogView(navController: NavHostController?, description: String) {

    var openDialogType by rememberSaveable { mutableStateOf<DialogType?>(null) } // ONの時はType、OFFの時はnull

    val buttonNameList = listOf(
        stringResource(id = R.string.dialog_view_alert_dialog_button),
        stringResource(id = R.string.dialog_view_dialog_button),
        stringResource(id = R.string.dialog_view_dialog_with_image_button)
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBarView(navController, stringResource(id = R.string.dialog_view_name), true)
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(buttonNameList) { index, name ->
                    ElevatedButton(
                        onClick = { openDialogType = DialogType.entries.toTypedArray()[index] }
                    ) {
                        Text(text = name)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }

            openDialogType?.let { dialogType ->
                when (dialogType) {
                    DialogType.ALERT -> {
                        // アラートダイアログ
                        AlertDialogExample(
                            onDismissRequest = { openDialogType = null },
                            onConfirmation = { openDialogType = null },
                            dialogTitle = stringResource(id = R.string.dialog_view_alert_dialog_title),
                            dialogText = stringResource(id = R.string.dialog_view_alert_dialog_msg),
                            icon = Icons.Default.Info
                        )
                    }
                    DialogType.MINIMAL -> {
                        // 最小限のダイアログ
                        MinimalDialog(onDismissRequest = { openDialogType = null })
                    }
                    DialogType.IMAGE -> {
                        // 画像付きダイアログ
                        DialogWithImage(
                            onDismissRequest = { openDialogType = null },
                            onConfirmation = { openDialogType = null },
                            painter = painterResource(id = R.drawable.night_view)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Info Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(id = R.string.dialog_view_dialog_confirm_button))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.dialog_view_dialog_dismiss_button))
            }
        }
    )
}

@Composable
private fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.dialog_view_minimal_dialog_msg),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Night View Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(160.dp)
                )
                Text(
                    text = stringResource(id = R.string.dialog_view_dialog_with_image_dialog_msg),
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(stringResource(id = R.string.dialog_view_dialog_dismiss_button))
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(stringResource(id = R.string.dialog_view_dialog_confirm_button))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogViewPreview() {
    ComposeViewTheme {
        DialogView(null, description = stringResource(id = R.string.dialog_view_description))
    }
}