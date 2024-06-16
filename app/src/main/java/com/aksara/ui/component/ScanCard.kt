package com.aksara.ui.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ScanCard(
    modifier: Modifier,
    scanResult: String,
    scanPhoto: Uri,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .widthIn(min = 220.dp)
            .heightIn(min = 283.dp)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 220.dp)
                .heightIn(min = 283.dp),
        ) {
            AsyncImage(
                model = scanPhoto,
                contentDescription = scanResult,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = Color.Black.copy(alpha = 0.4f),
                    blendMode = BlendMode.Multiply),
                modifier = Modifier.matchParentSize()
            )

            Column(
                modifier = Modifier
                    .matchParentSize()
                    .padding(16.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = scanResult,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}