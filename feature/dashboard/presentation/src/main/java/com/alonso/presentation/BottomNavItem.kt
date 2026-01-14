package com.alonso.presentation

import com.alonso.designsystem.R

sealed class BottomNavItem(
    val path: String,
    val title: String,
    val icon: Int,
) {

    data object OverviewScreen :
        BottomNavItem(
            path = BottomNavItemPath.HOME.name,
            title = "Overview",
            icon = R.drawable.ic_home
        )


    data object FavoriteScreen : BottomNavItem(
        path = BottomNavItemPath.FAVORITE.name,
        title = "Favorites",
        icon = R.drawable.ic_favorites
    )

    data object SearchScreen : BottomNavItem(
        path = BottomNavItemPath.SEARCH.name,
        title = "Search",
        icon = R.drawable.ic_search
    )

    data object SettingScreen : BottomNavItem(
        path = BottomNavItemPath.SETTING.name,
        title = "Settings",
        icon = R.drawable.ic_setting
    )
}

enum class BottomNavItemPath {
    HOME, FAVORITE, SEARCH, SETTING
}
