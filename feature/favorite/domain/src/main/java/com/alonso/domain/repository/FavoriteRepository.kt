package com.alonso.domain.repository

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity

interface FavoriteRepository {
    suspend fun getFavoriteCoffees(): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Local>
}