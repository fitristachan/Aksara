package com.aksara.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksara.R
import com.aksara.room.ScanViewModel
import com.aksara.ui.component.ScanCard

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    scanViewModel: ScanViewModel
) {
    val configuration = LocalConfiguration.current
    val columnCount = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        4
    } else {
        2
    }

    LaunchedEffect(key1 = true, block = {
        scanViewModel.getAllScans()
    })
    val contents by scanViewModel.scanDetailsList.collectAsStateWithLifecycle()

    var queryDate by remember { mutableStateOf("") }

    var isAllPressed by remember { mutableStateOf(false) }
    var isTodayPressed by remember { mutableStateOf(false) }
    var isYearPressed by remember { mutableStateOf(false) }
    var isMonthPressed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.history_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (queryDate == "") {
                isAllPressed = true
            }
            item {
                Button(
                    onClick = {
//                        if (!isAllPressed) {
//                            isAllPressed = true
//                            isTodayPressed = false
//                            isMonthPressed = false
//                            isYearPressed = false
//                            isContainPressed = false
//                            isFreePressed = false
//                            queryDate = ""
//                            queryStatus = null
//                        } else {
//                            isAllPressed = false
//                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        if (isAllPressed) MaterialTheme.colorScheme.primary.copy(
                            0.2f
                        ) else Color.White
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isAllPressed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 2.dp)
                ) {
                    Text(
                        stringResource(R.string.filter_all),
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isAllPressed) MaterialTheme.colorScheme.primary else Color.DarkGray
                    )
                }
            }
//            when (queryDate) {
//                queryToday() -> {
//                    isTodayPressed = true
//                    isMonthPressed = false
//                    isYearPressed = false
//                    isAllPressed = false
//                }
//                queryMonth() -> {
//                    isMonthPressed = true
//                    isTodayPressed = false
//                    isYearPressed = false
//                    isAllPressed = false
//                }
//                queryYear() -> {
//                    isYearPressed = true
//                    isMonthPressed = false
//                    isTodayPressed = false
//                    isAllPressed = false
//                }
//            }
            item {
                Button(
                    onClick = {
//                        if (!isTodayPressed) {
//                            isTodayPressed = true
//                            queryDate = queryToday()
//                        } else {
//                            isTodayPressed = false
//                            queryDate = ""
//                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        if (isTodayPressed) MaterialTheme.colorScheme.primary.copy(
                            0.2f
                        ) else Color.White
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isTodayPressed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 2.dp)
                ) {
                    Text(
                        stringResource(R.string.filter_today),
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isTodayPressed) MaterialTheme.colorScheme.primary else Color.DarkGray
                    )
                }
            }
            item {
                Button(
                    onClick = {
//                        if (!isMonthPressed) {
//                            isMonthPressed = true
//                            queryDate = queryMonth()
//                        } else {
//                            isMonthPressed = false
//                            queryDate = ""
//                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        if (isMonthPressed) MaterialTheme.colorScheme.primary.copy(
                            0.2f
                        ) else Color.White
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isMonthPressed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 2.dp)
                )
                {
                    Text(
                        stringResource(R.string.filter_month),
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isMonthPressed) MaterialTheme.colorScheme.primary else Color.DarkGray
                    )
                }
            }
            item {
                Button(
                    onClick = {
//                        queryDate = if (!isYearPressed) {
//                            isYearPressed = true
//                            queryYear()
//                        } else {
//                            isYearPressed = false
//                            ""
//                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        if (isYearPressed) MaterialTheme.colorScheme.primary.copy(
                            0.2f
                        ) else Color.White
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isYearPressed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 2.dp)
                )
                {
                    Text(
                        stringResource(R.string.filter_year),
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isYearPressed) MaterialTheme.colorScheme.primary else Color.DarkGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (contents.isEmpty()){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.empty_contents),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                    )
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            userScrollEnabled = true,
        ) {
            items(contents) {
                ScanCard(
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp),
                    scanResult = it.scanResult,
                    scanPhoto = it.scanPicture.toUri(),
                    onClick = {
                        navigateToDetail(it.scanId.toString())
                    }
                )
            }
        }
    }

}