package com.alonso.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.alonso.designsystem.CoffeeGoTheme

@Composable
internal fun BottomNavIcon(
    icon: Int,
    title: String,
    isSelected: Boolean
) {
    val color =
        if (isSelected) CoffeeGoTheme.colors.navBarItemSelected else CoffeeGoTheme.colors.navBarItemUnselected
    Icon(
        painter = painterResource(id = icon),
        contentDescription = title,
        tint = color
    )
}
