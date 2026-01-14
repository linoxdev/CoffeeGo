package com.alonso.domain.usecase

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import com.alonso.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Local> {
        return favoriteRepository.getFavoriteCoffees()
    }

}