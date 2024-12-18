package com.example.composeview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.loading.LoadingType
import com.example.composeview.ui.theme.ComposeViewTheme
import com.example.composeview.viewModel.IndicatorViewModel

/*
* rememberCoroutineScopeでは非同期処理を保持できなかったので、viewModelを採用。多分、コルーチンがキャンセルされてる。
* 画面が回転するたびに @Composable 関数が再構築されることを忘れずに！！！
*/
@Composable
fun IndicatorView(
    navController: NavHostController?,
    viewName: String,
    description: String,
    indicatorViewModel: IndicatorViewModel = viewModel()
) {
    var loadingIndeterminateLinearIndicator by rememberSaveable { mutableStateOf(false) }
    var loadingIndeterminateCircularIndicator by rememberSaveable { mutableStateOf(false) }

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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearDeterminateIndicator(
                    currentProgress = indicatorViewModel.linearDeterminateIndicatorCurrentProgress,
                    loading = indicatorViewModel.linearDeterminateIndicatorLoading,
                    onStartLoading = { indicatorViewModel.startLoading(LoadingType.LINEAR) }
                )
                Spacer(modifier = Modifier.height(32.dp))
                CircularDeterminateIndicator(
                    currentProgress = indicatorViewModel.circularDeterminateIndicatorCurrentProgress,
                    loading = indicatorViewModel.circularDeterminateIndicatorLoading,
                    onStartLoading = { indicatorViewModel.startLoading(LoadingType.CIRCULAR) }
                )
                Spacer(modifier = Modifier.height(32.dp))
                IndeterminateLinearIndicator(
                    loading = loadingIndeterminateLinearIndicator,
                    onClick = {
                        loadingIndeterminateLinearIndicator = !loadingIndeterminateLinearIndicator
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
                IndeterminateCircularIndicator(
                    loading = loadingIndeterminateCircularIndicator,
                    onClick = {
                        loadingIndeterminateCircularIndicator = !loadingIndeterminateCircularIndicator
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun LinearDeterminateIndicator(
    currentProgress: Float,
    loading: Boolean,
    onStartLoading: () -> Unit
) {
    // タスクが完了したら終える線形プログレスバー
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = onStartLoading,
            enabled = !loading // ローディングが終わるまで非活性
        ) {
            Text(stringResource(id = R.string.linear_determinate_indicator_button))
        }

        if (loading) {
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun CircularDeterminateIndicator(
    currentProgress: Float,
    loading: Boolean,
    onStartLoading: () -> Unit
) {
    // タスクが完了したら終える円形プログレスバー
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = onStartLoading,
            enabled = !loading
        ) {
            Text(stringResource(id = R.string.circular_determinate_indicator_button))
        }

        if (loading) {
            CircularProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.width(64.dp)
            )
        }
    }
}

@Composable
private fun IndeterminateLinearIndicator(
    loading: Boolean,
    onClick: () -> Unit
) {
    // ボタンを再度タップするまで終えない線形プログレスバー
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = onClick
        ) {
            Text(stringResource(id = R.string.indeterminate_linear_indicator_button))
        }

        if (!loading) return

        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun IndeterminateCircularIndicator(
    loading: Boolean,
    onClick: () -> Unit
) {
    // ボタンを再度タップするまで終えない円形プログレスバー
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = onClick
        ) {
            Text(stringResource(id = R.string.indeterminate_circular_indicator_button))
        }

        if (!loading) return

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IndicatorViewPreview() {
    ComposeViewTheme {
        IndicatorView(
            null,
            viewName = stringResource(id = R.string.indicator_view_name),
            description = stringResource(id = R.string.indicator_view_description)
        )
    }
}