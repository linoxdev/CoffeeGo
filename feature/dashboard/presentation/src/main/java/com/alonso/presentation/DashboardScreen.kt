package com.alonso.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.home.HomeScreen
import com.alonso.presentation.components.BottomNavIcon
import com.alonso.presentation.components.BottomNavLabel
import com.alonso.presentation.components.BrushOverlayBar
import com.alonso.search.SearchScreen

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
) {
    var currentDestination by rememberSaveable { mutableStateOf(BottomNavItem.OverviewScreen.path) }

    val navItems = remember {
        listOf(
            BottomNavItem.OverviewScreen,
            BottomNavItem.SearchScreen,
            BottomNavItem.FavoriteScreen,
            BottomNavItem.SettingScreen
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CoffeeGoTheme.colors.backgroundHome),
    ) {
        DashboardContent(
            currentDestination = currentDestination,
            modifier = Modifier.fillMaxSize()
        )

        DashboardBottomBar(
            navItems = navItems,
            currentDestination = currentDestination,
            onNavigate = { currentDestination = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun DashboardContent(
    currentDestination: String,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        modifier = modifier,
        targetState = currentDestination,
        transitionSpec = {
            fadeIn(tween(durationMillis = 200)) togetherWith fadeOut(tween(durationMillis = 200))
        },
        label = "DashboardContentAnimation"
    ) { destination ->
        when (destination) {
            BottomNavItem.OverviewScreen.path -> HomeScreen()
            BottomNavItem.FavoriteScreen.path -> FavoriteScreen()
            BottomNavItem.SearchScreen.path -> SearchScreen()
            BottomNavItem.SettingScreen.path -> SettingScreen()
            else -> HomeScreen()
        }
    }
}

@Composable
private fun DashboardBottomBar(
    navItems: List<BottomNavItem>,
    currentDestination: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isOverlayVisible = currentDestination != BottomNavItem.SettingScreen.path

    Column(modifier = modifier) {
        BrushOverlayBar(isOverlayVisible)
        NavigationBar(
            containerColor = CoffeeGoTheme.colors.backgroundHome,
            contentColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            navItems.forEach { item ->
                val isSelected = currentDestination == item.path
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onNavigate(item.path) },
                    icon = {
                        BottomNavIcon(
                            icon = item.icon,
                            title = item.title,
                            isSelected = isSelected
                        )
                    },
                    label = {
                        BottomNavLabel(
                            title = item.title,
                            isSelected = isSelected
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = CoffeeGoTheme.colors.optionCategoryBackgroundEnabled,
                        unselectedIconColor = CoffeeGoTheme.colors.optionCategoryBackgroundEnabled,
                        selectedTextColor = CoffeeGoTheme.colors.optionCategoryBackgroundEnabled,
                        unselectedTextColor = CoffeeGoTheme.colors.optionCategoryBackgroundEnabled
                    )
                )
            }
        }
    }
}
