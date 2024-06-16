package com.aksara.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aksara.R
import com.aksara.ui.component.ArticleCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
//    navigateToHistory: () -> Unit,
    navigateToArticle: (Int) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
//    val articleJson = remember { readArticleFromJson(context) }

    LazyColumn(){
        item{
            Row(
                modifier = Modifier
            ){
                Column(

                ){
                    Text("Aksara jawa apa yang ingin kamu scan hari ini?")
                    Button(
                        onClick = {}
                    ){
                        Text("Scan Aksara Baru")
                    }
                }
                Column(

                ){
                    //gambar wayang
                }
            }
        }
        stickyHeader {
            Text(
                text = stringResource(R.string.article_title),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 4.dp, vertical = 12.dp)
                    .padding(top = 8.dp)
            )
        }

//        items(articleJson, key = { it.id }) { article ->
//            ArticleCard(
//                modifier = Modifier,
//                title = article.title,
//                writer = article.writer,
//                publisher = article.publisher,
//                photo = article.photo,
//                onClick = {
//                    navigateToArticle(article.id)
//                }
//            )
//
//        }
    }
}