package com.aksara.ui.navigation

sealed class AksaraScreen (val route: String) {

    object Home : AksaraScreen("home")
    object Scan : AksaraScreen("scan")
    object Qna : AksaraScreen("qna")
    object Score : AksaraScreen("score")
    object History: AksaraScreen("history")
    object Result : AksaraScreen("home/{scanId}") {
        fun createRoute(scanId: String): String = "home/${scanId}"
    }
    object Article : AksaraScreen("article/{articleId}") {
        fun createRoute(articleId: Int): String = "article/$articleId"
    }

}