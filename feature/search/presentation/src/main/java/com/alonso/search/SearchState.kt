package com.alonso.search

import com.alonso.navigation.CoffeeItem

data class SearchState(
    val coffees: List<CoffeeItem> = emptyList(),
    val isLoading: Boolean = false,
    val coffeeToSearch: String = ""
)
