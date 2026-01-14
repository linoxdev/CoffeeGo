package com.alonso.domain.usecase

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import com.alonso.domain.repository.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend operator fun invoke(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network> {
        return homeRepository.getCoffeesByCategory(category)
    }

}