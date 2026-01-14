package com.alonso.presentation

import com.alonso.navigation.CoffeeItem

data class FavoriteState(
    val coffees: List<CoffeeItem> = emptyList(),
    val isLoading: Boolean = false,
)
