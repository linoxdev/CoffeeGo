package com.alonso.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf


@Immutable
data class CoffeeGoThemeData(
    val colors: CustomColors,
    val typography: CustomTypography,
    val isDarkModeApp: Boolean = false,
    val isDarkModeSystem: Boolean = false
)

val LocalCoffeeGoTheme = compositionLocalOf<CoffeeGoThemeData> {
    error("No CoffeeGoTheme provided!")
}

object CoffeeGoTheme {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCoffeeGoTheme.current.colors

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCoffeeGoTheme.current.typography

    val isDarkModeApp: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalCoffeeGoTheme.current.isDarkModeApp

    val isDarkModeSystem: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalCoffeeGoTheme.current.isDarkModeSystem
}

