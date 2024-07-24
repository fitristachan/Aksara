package com.aksara.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aksara.hilt.retrofit.QnaViewModel
import com.aksara.ui.component.contextItems

@Composable
fun QnaScreen(
    contextId: Int,
    qnaViewModel: QnaViewModel,
    modifier: Modifier = Modifier
){

    val context = contextItems.find { it.id == contextId }?.context
    val question = "Berapa kemungkinan mendapat nilai tinggi?"

    Text(text = context.toString())

    LaunchedEffect(context, question) {
        qnaViewModel.getAnswer(context.toString(), question)
    }

    val isLoading by qnaViewModel.isLoading.collectAsState()
    val response by qnaViewModel.qnaResponseResult.collectAsState()
    val errorMessage by qnaViewModel.errorMessage.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        when {
            isLoading -> {
                // Show loading UI
                CircularProgressIndicator()
            }
            errorMessage.isNotEmpty() -> {
                // Show error message
                Text(text = "Error: $errorMessage", color = Color.Red)
            }
            response != null -> {
                // Show response
                Text(text = response?.answer.toString())
            }
            else -> {
                // Show a placeholder or initial state UI
                Text(text = "Please wait...")
            }
        }
    }
}