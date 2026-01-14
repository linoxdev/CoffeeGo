package com.alonso.data.datasource


import android.util.Log
import com.alonso.data.api.HomeApi
import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.accessDataToMap

import com.alonso.domain.entity.CoffeeEntity
import com.alonso.data.network.safeApiCall
import javax.inject.Inject

internal class HomeDataSourceImpl @Inject constructor(
    private val api: HomeApi
) : HomeDataSource {

    override suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network> {
        Log.d("PRINT", "HomeDataSourceImpl category: $category")
        return safeApiCall { api.getCofferByCategory(category) }
            .accessDataToMap { data ->
                data?.map {
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
            }

    }
}
