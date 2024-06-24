package com.aksara.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    historyId: String,
    navigateBack: () -> Unit
) {
    Text(text = historyId)
}