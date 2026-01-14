package com.alonso.detail.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.alonso.navigation.CoffeeItem
import com.alonso.ui_components.components.LoadImage
import kotlin.math.absoluteValue
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun CoffeeListSlider(
    modifier: Modifier = Modifier,
    modifierImg: Modifier = Modifier,
    coffeeList: List<CoffeeItem>,
    coffeeClicked: Int,
    currentPage: (CoffeeItem?) -> Unit = {},
) {
    if (coffeeList.isEmpty()) return

    // Initialize pager with the clicked coffee index, ensuring it's within bounds
    val initialPage = coffeeClicked.coerceIn(0, coffeeList.size - 1)

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { coffeeList.size }
    )

    // Handle page changes
    LaunchedEffect(pagerState) {
        pagerState.currentPageOffsetFraction
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                if (page < coffeeList.size) {
                    currentPage(coffeeList[page])
                }
            }
    }




    // Initialize with the clicked coffee instead of first coffee
    LaunchedEffect(coffeeList, coffeeClicked) {
        if (coffeeList.isNotEmpty() && initialPage < coffeeList.size) {
            currentPage(coffeeList[initialPage])
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp),
        pageSpacing = 5.dp,
        contentPadding = PaddingValues(horizontal = 90.dp),
    ) { pageIndex ->
        if (pageIndex < coffeeList.size) {
            CoffeeImageCard(
                modifier = modifierImg,
                coffee = coffeeList[pageIndex],
                pageIndex = pageIndex,
                pagerState = pagerState
            )
        }
    }
}


@Composable
private fun CoffeeImageCard(
    modifier: Modifier = Modifier,
    coffee: CoffeeItem,
    pageIndex: Int,
    pagerState: PagerState
) {
    val blurRadius = remember(pagerState.currentPage, pageIndex) {
        if (pagerState.currentPage == pageIndex) 0.dp else 12.dp
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .pagerTransition(pageIndex, pagerState)
            .blur(radius = blurRadius),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CoffeeImage(imageUrl = coffee.image)
        /*CoffeeShadow(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(20.dp)

                //.offset(y = (-40).dp)
        )*/
    }
}

@Composable
private fun CoffeeImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    LoadImage(
        url = imageUrl,
        modifier = modifier
            .height(310.dp)
            .width(190.dp)

        //.aspectRatio(190.dp / 320.dp),
    )
}

@Composable
private fun CoffeeShadow(modifier: Modifier = Modifier) {
    val shadowBrush = remember {
        Brush.radialGradient(
            colors = listOf(
                Color(0x50404040), // Center - more opaque for better visibility
                Color(0x40303030), // Mid-inner
                Color(0x25202020), // Mid-outer
                Color(0x15101010), // Outer
                Color.Transparent   // Edge
            ),
            radius = 12.dp.value // Slightly larger radius for better shadow effect
        )
    }

    Canvas(modifier = modifier) {
        scale(
            scaleX = 12f, // Reduced scale for better proportions
            scaleY = 1.8f // Adjusted for more natural shadow appearance
        ) {
            drawCircle(
                brush = shadowBrush,
                radius = 6.dp.toPx() // Slightly larger base radius
            )
        }
    }
}

private fun Modifier.pagerTransition(pageIndex: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset = (pagerState.currentPage - pageIndex + pagerState.currentPageOffsetFraction)
            .absoluteValue

        val offsetFraction = 1f - pageOffset.coerceIn(0f, 1f)
        val scaleValue = lerp(
            start = 70.dp,
            stop = 110.dp,
            fraction = offsetFraction
        )
        scaleY = scaleValue / 100.dp
        translationY = lerp(
            start = 0f,
            stop = 380f,
            fraction = offsetFraction
        )
    }

enum class ScrollDirection {
    Left,
    Right,
    Idle
}