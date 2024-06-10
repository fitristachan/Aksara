package com.dicoding.aksara.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dicoding.aksara.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
//                modifier = Modifier.
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.menu_home))
                        Text("Home")
                    }
                    //perlu ganti icon
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.List, contentDescription = stringResource(R.string.menu_qna))
                        Text("QnA")
                    }
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        //perlu ganti icon
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = stringResource(R.string.menu_scan)
                        )
                        Text("Scan")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Star, contentDescription = stringResource(R.string.menu_nilai))
                        Text("Nilai")
                    }
                    //perlu ganti icon
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = stringResource(R.string.menu_history),
                        )
                        Text("Riwayat")
                    }
                },

            )
        },
    ) {
        it
    }
}