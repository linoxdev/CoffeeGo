package com.alonso.domain

import com.alonso.domain.entity.CoffeeEntity
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(value: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Local> {
        return searchRepository.searchCoffeeByName(value)
    }

}