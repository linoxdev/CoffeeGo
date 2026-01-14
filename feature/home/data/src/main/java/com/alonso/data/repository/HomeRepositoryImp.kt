package com.alonso.data.repository

import android.util.Log
import com.alonso.data.datasource.HomeDataSource
import com.alonso.data.local.db.CoffeeDao
import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import com.alonso.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImp(
    val homeDataSource: HomeDataSource,
    private val coffeeDao: CoffeeDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HomeRepository {
    override suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network> {
        return withContext(ioDispatcher) {
            val response = homeDataSource.getCoffeesByCategory(category)
            cacheCoffeesIfNeeded(response.data, category)
            response
        }

    }

    private suspend fun cacheCoffeesIfNeeded(data: List<CoffeeEntity>?, category: String) {
        data?.let {
            val isCacheable = category == "all" && !coffeeDao.hasCoffees()
            if (isCacheable) {
                coffeeDao.insertCoffees(
                    data.map {
                        com.alonso.data.local.db.CoffeeEntity(
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

            }
        }

    }
}