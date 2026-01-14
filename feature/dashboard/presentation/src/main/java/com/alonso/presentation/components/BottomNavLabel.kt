package com.alonso.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme


@Composable
internal fun BottomNavLabel(
    title: String,
    isSelected: Boolean
) {
    val color =
        if (isSelected) CoffeeGoTheme.colors.navBarItemSelected else CoffeeGoTheme.colors.navBarItemUnselected
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = CoffeeGoTheme.typography.commonRegularTextStyle.copy(
                color = color,
                fontSize = 10.sp,
                fontWeight = fontWeight
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .width(10.dp)
                .height(2.dp)
                .alpha(if (isSelected) 1f else 0f)
                .background(color, shape = RoundedCornerShape(50))
        )
    }
}