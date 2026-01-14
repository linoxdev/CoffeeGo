package com.alonso.data.repository

import com.alonso.data.datasource.SearchDataSource
import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.SearchRepository
import com.alonso.domain.entity.CoffeeEntity

class SearchRepositoryImpl(val dataSource: SearchDataSource) : SearchRepository {
    override suspend fun searchCoffeeByName(value: String): ResultDataEntity<List<CoffeeEntity>, ErrorEntity.Local> {
        return dataSource.searchCoffeeByName(value)
    }
}