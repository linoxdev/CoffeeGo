package com.alonso.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppScreen : NavKey {
    @Serializable
    data object Home : NavKey

    @Serializable
    data object Search : NavKey

    @Serializable
    data object Splash : NavKey

    @Serializable
    data object Dashboard : NavKey

    @Serializable
    data class Detail(
        val listCoffee: List<CoffeeItem>,
        val coffeeClicked: Int = 0
    ) : AppScreen
}

@Serializable
data class CoffeeItem(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val image: String,
    val volume: String,
    val qualification: Double,
    val category: String,
    val favorite: Boolean
)

