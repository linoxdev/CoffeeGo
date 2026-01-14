package com.alonso.home

import com.alonso.navigation.CoffeeItem

data class HomeUiState(
    val coffeeList: List<CoffeeItem> = emptyList(),
    val favorites: List<CoffeeItem> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCategory: String = "all",
    val categories: List<CategoryOption> = listOf(
        CategoryOption(
            id = "all",
            name = "All"
        ),
        CategoryOption(
            id = "coffee",
            name = "Coffee"
        ),
        CategoryOption(
            id = "cold_coffee",
            name = "Cold coffee"
        ),

        CategoryOption(
            id = "refreshers",
            name = "Refreshers"
        ),
        CategoryOption(
            id = "tea",
            name = "Tea "
        )

    )
)

data class CategoryOption(
    val id: String, val name: String
)