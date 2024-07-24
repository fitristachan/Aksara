package com.aksara.ui.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.aksara.hilt.room.ScanViewModel
import java.util.Locale

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    scanViewModel: ScanViewModel,
    scanId: String
) {
    LaunchedEffect(key1 = scanId) {
        scanViewModel.getScanByScanId(scanId.toInt())
    }

    val scanDetail by scanViewModel.scanDetail.collectAsStateWithLifecycle()

    val scanPicture = scanDetail?.scanPicture?.toUri()
    val scanResult = scanDetail?.scanResult

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = scanPicture,
            contentDescription = scanResult,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .border(4.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
        )
    }

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
                    modifier = modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    text = "  Aksaramu adalah....  "
                )
            }
            Column(
                modifier = modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
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
                        text = scanResult.toString()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                    )
                }
            }
        }
    }

}