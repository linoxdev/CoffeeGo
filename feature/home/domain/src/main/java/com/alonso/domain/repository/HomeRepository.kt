package com.alonso.domain.repository

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity

interface HomeRepository {
    suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network>
}