package com.alonso.data.repository

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import com.alonso.domain.repository.FavoriteRepository
import com.alonso.data.local.db.CoffeeDao

class FavoriteRepositoryImp(private val coffeeDao: CoffeeDao) : FavoriteRepository {
    override suspend fun getFavoriteCoffees(): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Local> {
        return try {
            ResultDataEntity.success(data = coffeeDao.getFavoriteCoffees().map {
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
            })
        } catch (e: Exception) {
            ResultDataEntity.error(error = ErrorEntity.Local.UnknownError(e))
        }

    }
}