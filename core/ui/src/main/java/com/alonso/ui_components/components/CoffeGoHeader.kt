package com.alonso.ui_components.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme

@Composable
fun CoffeeGoHeader(modifier: Modifier = Modifier, title: String) {
    Text(
        title,
        style = CoffeeGoTheme.typography.commonBoldTextStyle.copy(
            fontSize = 22.sp,
            color = CoffeeGoTheme.colors.textColor,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
            .fillMaxWidth()
    )

}