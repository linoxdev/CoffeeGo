package com.alonso.home

import com.alonso.domain.entity.CoffeeEntity
import com.alonso.navigation.CoffeeItem

fun List<CoffeeEntity>.toCoffeeItem() = map {
    CoffeeItem(
        id = it.id,
        name = it.name,
        price = it.price,
        description = it.description,
        image = it.image,
        qualification = it.ranking,
        category = it.category,
        volume = it.volume,
        favorite = it.favorite
    )
}
