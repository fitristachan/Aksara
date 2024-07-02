package com.aksara.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    historyId: String
) {
    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Column(
            modifier
                .height(230.dp)
                .fillMaxWidth()
        ){
            Column(
                modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .height(35.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ){
                Text(
                    modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    text = "  Aksaramu adalah....  "
                )
            }
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .height(200.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        text = historyId.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                    )
                }
            }
        }
    }

}