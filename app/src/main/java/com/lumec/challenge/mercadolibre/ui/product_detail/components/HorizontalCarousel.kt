package com.lumec.challenge.mercadolibre.ui.product_detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCarousel(images: List<String>) {
    val pageState = rememberPagerState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(pageCount = images.size, state = pageState) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = "Product Image",
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        DotsIndicator(
            totalDots = images.size,
            selectedIndex = pageState.currentPage,
            selectedColor = Color(0xFFFEDC13),
            unSelectedColor = Color(0xffE6E6E6)
        )
    }
}


@Composable
fun DotsIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
){
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}