package com.alonso.data.datasource

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import com.alonso.data.local.db.CoffeeDao

class SearchDataSourceImpl(private val coffeeDao: CoffeeDao) : SearchDataSource {
    override suspend fun searchCoffeeByName(value: String): ResultDataEntity<List<CoffeeEntity>, ErrorEntity.Local> {
        return try {
            val response = coffeeDao.searchCoffee(value)
            ResultDataEntity.success(
                data = response.map {
                    CoffeeEntity(
                        id = it.id,
                        name = it.name,
                        price = it.price,
                        description = it.description,
                        image = it.image,
                        ranking = it.ranking,
                        category = it.category,
                        volume = it.volume,
                        favorite = it.favorite
                    )
                }
            )

        } catch (e: Exception) {
            ResultDataEntity.error(ErrorEntity.Local.UnknownError(e))
        }
    }
}