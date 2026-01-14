package com.alonso.domain

import com.alonso.domain.entity.CoffeeEntity

interface SearchRepository {

    suspend fun searchCoffeeByName(value: String): ResultDataEntity<List<CoffeeEntity>, ErrorEntity.Local>
}