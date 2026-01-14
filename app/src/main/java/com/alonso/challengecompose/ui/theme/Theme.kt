package com.alonso.challengecompose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.designsystem.CoffeeGoThemeData
import com.alonso.designsystem.DarkColors
import com.alonso.designsystem.LightColors
import com.alonso.designsystem.LocalCoffeeGoTheme
import com.alonso.designsystem.TypographyCoffee
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.LocalComposeNavigator
import com.alonso.ui_components.base.LocalThemeViewModel
import com.alonso.ui_components.base.ThemeViewModel


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appNavigator: AppNavigator,
    themeViewModel: ThemeViewModel,
    content: @Composable () -> Unit
) {
    val isDarkMode by themeViewModel.themeState.collectAsStateWithLifecycle()
    val colors = when {
        darkTheme || isDarkMode -> DarkColors
        else -> LightColors
    }
    LaunchedEffect(darkTheme, isDarkMode) {

    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.backgroundHome.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, true)
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
                !(isDarkMode || darkTheme)
        }
    }

    CompositionLocalProvider(
        LocalCoffeeGoTheme provides CoffeeGoThemeData(
            colors,
            TypographyCoffee,
            isDarkModeApp = isDarkMode,
            isDarkModeSystem = darkTheme
        ),
        LocalComposeNavigator provides appNavigator,
        LocalThemeViewModel provides themeViewModel,
        content = content
    )
}