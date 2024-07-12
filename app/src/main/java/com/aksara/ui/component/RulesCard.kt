package com.aksara.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RulesCard(
    ruleText: String
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
        verticalAlignment = if (!expanded) Alignment.CenterVertically else Alignment.Top
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(3f)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
            ) {
                Text(
                    text = if (expanded) ruleText else ruleText.take(50) + "...",
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(if (expanded) 8.dp else 0.dp)
                .weight(1f)
        ) {
            Text(
                text = if (expanded) "︿" else "﹀",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable { expanded = !expanded }
            )
        }
    }
}
