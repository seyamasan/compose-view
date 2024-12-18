package com.example.composeview.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composeview.R
import com.example.composeview.ui.theme.ComposeViewTheme

enum class CardType { Default, Elevated, Outlined }

@Composable
fun CardView(navController: NavHostController?, viewName: String, description: String) {
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
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 普通のカード
                CardViewCardItem(
                    type = CardType.Default,
                    title = stringResource(id = R.string.card_view_comment_title),
                    comment = stringResource(id = R.string.card_view_comment1),
                    count = "192"
                )
                // 立体感のあるカード
                CardViewCardItem(
                    type = CardType.Elevated,
                    title = stringResource(id = R.string.card_view_comment_title),
                    comment = stringResource(id = R.string.card_view_comment2),
                    count = "19"
                )
                // 枠線のあるカード
                CardViewCardItem(
                    type = CardType.Outlined,
                    title = stringResource(id = R.string.card_view_comment_title),
                    comment = stringResource(id = R.string.card_view_comment3),
                    count = "57"
                )
            }
        }
    }
}

@Composable
private fun CardViewCardItem(type: CardType, title: String, comment: String, count: String) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)

    val colors = when (type) {
        CardType.Default -> CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        CardType.Elevated -> CardDefaults.cardColors()
        CardType.Outlined -> CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    }

    val cardContent: @Composable ColumnScope.() -> Unit = {
        Column {
            Row(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(modifier = Modifier.alignByBaseline(), text = title)
                Spacer(modifier = Modifier.width(6.dp))
                Text(modifier = Modifier.alignByBaseline(), text = count, fontSize = 14.sp)
            }
            Row(
                modifier = Modifier.padding(top = 4.dp, start = 10.dp, end = 10.dp, bottom = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "Users icon",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(modifier = Modifier.alignByBaseline(), text = comment, fontSize = 14.sp)
            }
        }
    }

    when (type) {
        CardType.Default -> Card(modifier = modifier, colors = colors, content = cardContent)
        CardType.Elevated -> ElevatedCard(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            content = cardContent
        )
        CardType.Outlined -> OutlinedCard(
            modifier = modifier,
            colors = colors,
            border = BorderStroke(1.dp, Color.Black),
            content = cardContent
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardViewPreview() {
    ComposeViewTheme {
        CardView(
            null,
            viewName = stringResource(id = R.string.card_view_name),
            description = stringResource(id = R.string.card_view_description)
        )
    }
}