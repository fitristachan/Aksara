package com.aksara.ui.navigation

sealed class AksaraScreen (val route: String) {

    object Home : AksaraScreen("home")
    object Scan : AksaraScreen("scan")
    object SelectContext : AksaraScreen("qna")
    object Qna : AksaraScreen("qna/{contextId}") {
        fun createRoute(contextId: Int): String = "qna/${contextId}"
    }
    object History: AksaraScreen("history")

    object Score: AksaraScreen("score")

    object Result : AksaraScreen("home/{scanId}") {
        fun createRoute(scanId: String): String = "home/${scanId}"
    }
    object Article : AksaraScreen("article/{articleId}") {
        fun createRoute(articleId: Int): String = "article/$articleId"
    }

}