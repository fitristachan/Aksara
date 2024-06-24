package com.aksara.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.aksara.ui.component.ScanCard

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier
){
    val configuration = LocalConfiguration.current
    val columnCount = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        4
    } else {
        2
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount),
        userScrollEnabled = true,
    ) {
        items(10) { index ->
            ScanCard(
                modifier = Modifier.padding(vertical = 16.dp),
                scanResult = index.toString(),
                scanPhoto = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1BBIr93EwTgALgredrjVyJMZSD0-84P4Ksg&s".toUri(),
                onClick = {
                }
            )
        }
    }


}