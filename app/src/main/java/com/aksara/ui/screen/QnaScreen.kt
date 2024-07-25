package com.aksara.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aksara.R
import com.aksara.hilt.retrofit.QnaViewModel
import com.aksara.ui.component.contextItems

@Composable
fun QnaScreen(
    contextId: Int,
    qnaViewModel: QnaViewModel,
    modifier: Modifier = Modifier
) {
    val context = contextItems.find { it.id == contextId }?.context
    var question by remember { mutableStateOf("") }

    LaunchedEffect(key1 = context, key2 = question) {
        if (context!!.isNotEmpty() && question.isNotEmpty()) {
            qnaViewModel.getAnswer(context, question)
        }
    }

    val isLoading by qnaViewModel.isLoading.collectAsState()
    val response by qnaViewModel.qnaResponseResult.collectAsState()
    val errorMessage by qnaViewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp)
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = context.toString(),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = stringResource(R.string.question_example),
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.size(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            contextItems.find { it.id == contextId }?.sample_question?.forEach { item ->
                    Text(
                        text = item,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
            }
            Spacer(modifier = Modifier.size(8.dp))
        }

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = stringResource(R.string.question_textfield),
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.size(4.dp))

        OutlinedTextField(
            value = question,
            onValueChange = { question = it  },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = stringResource(R.string.question_result),
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.size(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }

                errorMessage.isNotEmpty() -> {
                    Text(
                        text = "Error: $errorMessage",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                question.isNullOrEmpty() -> {
                    Text(
                        text = stringResource(R.string.question_empty_message),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                response != null -> {
                    Text(
                        text = response?.answer.toString(),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                else -> {
                    Text(
                        text = stringResource(R.string.question_empty_message),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Justify,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}