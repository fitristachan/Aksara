package com.aksara.ui.screen

import android.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aksara.R
import com.aksara.ui.component.ArticleCard
import com.dietinapp.article.readArticleFromJson

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToScan: () -> Unit,
    navigateToDetail: (String) -> Unit,
//    navigateToHistory: () -> Unit,
    navigateToArticle: (Int) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val articleJson = remember { readArticleFromJson(context) }

    LazyColumn(
        modifier
            .padding(horizontal = 8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.onTertiary)
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Column(
                    modifier
                        .weight(2f)
                        .padding(bottom = 14.dp, top = 14.dp, start = 24.dp, end = 0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.scan_from_home_desc),
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Button(
                        shape = RoundedCornerShape(6.dp),
                        onClick = { navigateToScan() }
                    ) {
                        Text(
                            text = stringResource(R.string.scan_from_home_btn),
                            color = MaterialTheme.colorScheme.background,
                            maxLines = 2,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Column(
                    modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    //gambar wayang
                    Image(
                        painter = painterResource(id = R.drawable.wayang),
                        contentDescription = "tes",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 21.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
        }

        stickyHeader {
            Text(
                text = stringResource(R.string.article_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 4.dp, vertical = 12.dp)
            )
        }

        items(articleJson, key = { it.id }) { article ->
            ArticleCard(
                modifier = Modifier,
                title = article.title,
                writer = article.writer,
                publisher = article.publisher,
                photo = article.photo,
                onClick = {
                    navigateToArticle(article.id)
                }
            )

        }
    }
}