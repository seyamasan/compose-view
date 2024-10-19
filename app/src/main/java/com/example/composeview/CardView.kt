package com.example.composeview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composeview.navigator.Screens
import com.example.composeview.ui.theme.ComposeViewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardView(navController: NavHostController?, description: String) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.card_view_name)) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController?.navigate(Screens.Home) }) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = description)

            // 普通のカード
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                ) {
                    // alignByBaseline()でテキストのベースラインを揃えている
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = stringResource(id = R.string.card_view_comment_title)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = "192",
                        fontSize = 14.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 4.dp, start = 10.dp, end = 10.dp, bottom = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = "Users",
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = stringResource(id = R.string.card_view_comment1),
                        fontSize = 14.sp
                    )
                }
            }

            // 立体感のあるカード
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                ) {
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = stringResource(id = R.string.card_view_comment_title)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = "19",
                        fontSize = 14.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 4.dp, start = 10.dp, end = 10.dp, bottom = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = "Users",
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = stringResource(id = R.string.card_view_comment2),
                        fontSize = 14.sp
                    )
                }
            }

            // 枠線があるカード
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                ) {
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = stringResource(id = R.string.card_view_comment_title)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = "57",
                        fontSize = 14.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 4.dp, start = 10.dp, end = 10.dp, bottom = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = "Users",
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = stringResource(id = R.string.card_view_comment3),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardViewPreview() {
    ComposeViewTheme {
        CardView(null, description = stringResource(id = R.string.card_view_description))
    }
}