package com.alonso.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.alonso.designsystem.CoffeeGoTheme

@Composable
fun BrushOverlayBar(isOverlayVisible: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .alpha(if (isOverlayVisible) 1f else 0f)
            .background(brush = CoffeeGoTheme.colors.brush)
    )
}