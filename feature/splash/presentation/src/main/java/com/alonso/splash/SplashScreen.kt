package com.alonso.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.navigation.navRoot
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator = navRoot
) {

    var startLogoAnimation by remember { mutableStateOf(false) }
    var startScaleAnimation by remember { mutableStateOf(false) }
    var showBackground by remember { mutableStateOf(false) }


    val logoAlpha by animateFloatAsState(
        targetValue = if (startLogoAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1300),
        label = "logoAlpha"
    )


    val logoScale by animateFloatAsState(
        targetValue = if (startScaleAnimation) 1f else 2.3f,
        animationSpec = tween(durationMillis = 1000),
        label = "logoScale"
    )


    LaunchedEffect(Unit) {
        delay(200)
        startLogoAnimation = true

        delay(1000)
        startScaleAnimation = true

        delay(1000)
        showBackground = true

        delay(1500)
        appNavigator.goTo(AppScreen.Dashboard)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CoffeeGoTheme.colors.backgroundSplash),
        contentAlignment = Alignment.Center
    ) {
        SplashBackground(
            isVisible = showBackground
        )

        SplashLogoContent(
            scale = logoScale,
            alpha = logoAlpha
        )
    }
}

@Composable
private fun SplashBackground(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background_coffee),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.5f }
        )
    }
}

@Composable
private fun SplashLogoContent(
    scale: Float,
    alpha: Float,
    modifier: Modifier = Modifier
) {

    val customFont = remember {
        FontFamily(Font(R.font.roboto_flex))
    }
    val iconCoffee = if (CoffeeGoTheme.isDarkModeApp) R.drawable.ic_coffee_bean_dark
    else R.drawable.ic_coffee_bean

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(iconCoffee),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(70.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
        )


        AnimatedVisibility(
            modifier = Modifier.offset(y = (-5).dp),
            visible = scale < 2f,
            enter = fadeIn(animationSpec = tween(500))
        ) {
            Text(
                text = "CoffeeGo",
                color = CoffeeGoTheme.colors.textIconSplash,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = customFont
                )
            )
        }
    }
}