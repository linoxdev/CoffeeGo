package com.alonso.domain.entity

data class CoffeeEntity(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val image: String,
    val ranking: Double,
    val category: String,
    val volume: String,
    val favorite: Boolean
)
