package com.aksara.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aksara.R
import com.aksara.ui.component.BottomBar
import com.aksara.ui.component.TopBar
import com.aksara.ui.navigation.AksaraScreen
import com.aksara.ui.screen.ArticleScreen
import com.aksara.ui.screen.HistoryScreen
import com.aksara.ui.screen.HomeScreen
import com.aksara.ui.screen.QnaScreen
import com.aksara.ui.screen.ResultScreen
import com.aksara.ui.screen.ScanScreen
import com.aksara.ui.screen.ScoreScreen
import com.aksara.ui.theme.AksaraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AksaraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AksaraApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AksaraApp(
    navController: NavHostController = rememberNavController()
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (currentRoute != AksaraScreen.Result.route
                && currentRoute != AksaraScreen.Scan.route
                && currentRoute != AksaraScreen.Article.route

            ) {
                BottomBar(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    navController)
            }
        },
        topBar = {
            if (currentRoute != AksaraScreen.Home.route
                && currentRoute != AksaraScreen.Scan.route
                && currentRoute != AksaraScreen.Qna.route
                && currentRoute != AksaraScreen.Score.route
                && currentRoute != AksaraScreen.History.route
            ) {
                var text = ""
                when (currentRoute) {
                    AksaraScreen.Result.route -> {
                        text = stringResource(R.string.result_title)
                    }
                    AksaraScreen.Article.route -> {
                        text = stringResource(R.string.article_title)
                    }
                }
                TopBar(
                    navigateBack = { navController.navigateUp() },
                    title = text
                )
            }
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AksaraScreen.Home.route,
        ) {
            composable(AksaraScreen.Home.route) {
                HomeScreen(
//                    navigateToHistory = {
//                        navController.navigate(AksaraScreen.History.route)
//                    },
                    navigateToDetail = {
                        val detailRoute = AksaraScreen.Result.createRoute(it)
                        navController.navigate(detailRoute)
                    },
                    navigateToArticle = { article ->
                        val articleRoute = AksaraScreen.Article.createRoute(article)
                        navController.navigate(articleRoute)
                    }
                )
            }
            composable(AksaraScreen.Qna.route) {
                QnaScreen()
            }
            composable(AksaraScreen.Scan.route) {
                ScanScreen(
                    navigateToDetail = {
                        val route = AksaraScreen.Result.createRoute(it)
                        navController.navigate(route)
                    }
                )
            }
            composable(AksaraScreen.Score.route) {
                ScoreScreen()
            }
            composable(AksaraScreen.History.route) {
                HistoryScreen(
//                    navigateToDetail = {
//                        val route = AksaraScreen.Result.createRoute(it)
//                        navController.navigate(route)
//                    },
                )
            }
            composable(
                route = AksaraScreen.Result.route,
                arguments = listOf(
                    navArgument("historyId") { type = NavType.StringType }
                )
            ) {
                val historyId =
                    it.arguments?.getString("historyId") ?: ""
                ResultScreen(
//                    modifier,
//                    historyId = historyId,
//                    navigateBack = {
//                        navController.navigateUp()
//                    },
                )
            }
            composable(
                route = AksaraScreen.Article.route,
                arguments = listOf(
                    navArgument("articleId") { type = NavType.IntType }
                )
            ) {
                val articleId = it.arguments?.getInt("articleId")
                ArticleScreen(
                    articleId = articleId!!,
                )
            }
        }
    }
}