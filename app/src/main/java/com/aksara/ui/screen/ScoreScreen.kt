package com.aksara.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aksara.R
import com.aksara.ui.component.RulesCard
import com.aksara.ui.component.rulesItems

@Composable
fun ScoreScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //imageresult
        Image(
            modifier = modifier
                .padding(horizontal = 0.dp, vertical = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .width(290.dp)
                .height(161.dp),
            painter = painterResource(id = R.drawable.wayang),
            contentDescription = stringResource(
                R.string.score_association_result
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(rulesItems, key = { it.id }) { item ->
                RulesCard(item.text)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }


}