package com.aksara.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aksara.R
import com.aksara.ui.component.RulesCard
import com.aksara.ui.component.contextItems

@Composable
fun SelectContextScreen(
    navigateToQna: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.choose_context_title),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.choose_context_desc),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )


        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(contextItems, key = { it.id }) { item ->
                RulesCard(item.title, item.context, onClick = {
                    navigateToQna(item.id)
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }


}