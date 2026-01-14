package com.alonso.data.datasource

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity


interface HomeDataSource {
    suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network>
}


