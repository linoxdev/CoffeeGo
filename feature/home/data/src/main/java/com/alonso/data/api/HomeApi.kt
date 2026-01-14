package com.alonso.data.api

import com.alonso.data.model.CoffeeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("/{category}.json")
    suspend fun getCofferByCategory(@Path("category") category: String): Response<List<CoffeeResponse>>
}