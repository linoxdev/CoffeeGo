package com.alonso.data.datasource

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity

interface SearchDataSource {
    suspend fun searchCoffeeByName(value: String): ResultDataEntity<List<CoffeeEntity>, ErrorEntity.Local>
}