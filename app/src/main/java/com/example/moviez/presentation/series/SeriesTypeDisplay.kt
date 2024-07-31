package com.example.moviez.presentation.series

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviez.R
import com.example.moviez.dataModels.SeriesListItem
import com.example.moviez.presentation.commons.itemCards.ExtraLargeItemCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SeriesTypeDisplay(
    type: String,
    typeNumb: Int,
    seriesList: List<SeriesListItem>,
    onArrowClick:(Int) -> Unit,
    onSeriesClick: (Int) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(colorResource(id = R.color.bg_gray)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = type,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(15.dp, 7.dp))
            Image(painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "View All",
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        onArrowClick(typeNumb)
                    })
        }
        val pagerState = rememberPagerState()
        Column (
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            HorizontalPager(count = Math.min(9, seriesList.size),
                state = pagerState,
            ) {page->
                val series = seriesList[page]
                ExtraLargeItemCard(onItemClick = { onSeriesClick(series.id) },
                    posterPath = series.posterPath)
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .height(15.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) colorResource(id = R.color.main) else colorResource(
                        id = R.color.disabled_color
                    )
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
@Preview
fun SeriesTypeDisplayPreview(){
    SeriesTypeDisplay(
        type="Trending Shows",
        typeNumb =1,
        seriesList= listOf(
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
            SeriesListItem(),
        ),
    onArrowClick= {  },
    onSeriesClick={}
    )
}