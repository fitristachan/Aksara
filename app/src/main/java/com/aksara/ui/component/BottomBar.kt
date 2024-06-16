package com.aksara.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aksara.R
import com.aksara.ui.navigation.AksaraScreen
import com.aksara.ui.navigation.BottomNavItem


@Composable
fun BottomBar(
    modifier: Modifier,
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .fillMaxWidth()
            .shadow(2.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 2.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            BottomNavItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                aksaraScreen = AksaraScreen.Home
            ),
            BottomNavItem(
                title = stringResource(R.string.menu_qna),
                icon = ImageVector.vectorResource(R.drawable.qna_icon),
                aksaraScreen = AksaraScreen.Qna
            ),
            BottomNavItem(
                title = stringResource(R.string.menu_scan),
                icon = ImageVector.vectorResource(R.drawable.scan_icon),
                aksaraScreen = AksaraScreen.Scan
            ),
            BottomNavItem(
                title = stringResource(R.string.menu_nilai),
                icon = ImageVector.vectorResource(R.drawable.score_icon),
                aksaraScreen = AksaraScreen.Score
            ),
            BottomNavItem(
                title = stringResource(R.string.menu_history),
                icon = ImageVector.vectorResource(R.drawable.history_icon),
                aksaraScreen = AksaraScreen.History
            ),
        )
        navigationItems.forEachIndexed { index, item ->
            if (index != 2) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon, contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.aksaraScreen.route,
                    onClick = {
                        navController.navigate(item.aksaraScreen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            } else {
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { },
                    label = { },
                    enabled = false
                )
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = {
                AksaraScreen.Scan.route.let {
                    navController.navigate(it) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }

                }
            },
            containerColor = MaterialTheme.colorScheme.primary,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.scan_icon),
                contentDescription = stringResource(R.string.menu_scan),
                tint = MaterialTheme.colorScheme.background
            )
        }
    }
}