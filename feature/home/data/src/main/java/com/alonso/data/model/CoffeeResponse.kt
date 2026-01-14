package com.alonso.data.model

import com.google.gson.annotations.SerializedName

data class CoffeeResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("qualification")
    val ranking: Double,
    @SerializedName("volume")
    val volume: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("favorite")
    val favorite: Boolean
)

