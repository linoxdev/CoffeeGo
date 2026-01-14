package com.alonso.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoffeeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCoffee(coffee: CoffeeEntity): Long


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoffees(coffees: List<CoffeeEntity>): List<Long>

    @Query("SELECT EXISTS(SELECT 1 FROM coffee_table LIMIT 1)")
    suspend fun hasCoffees(): Boolean


    @Query(
        """
        SELECT * FROM coffee_table 
        WHERE name LIKE '%' || :searchQuery || '%'
        OR category LIKE '%' || :searchQuery || '%'
    """
    )
    suspend fun searchCoffee(searchQuery: String): List<CoffeeEntity>

    @Query("SELECT * FROM coffee_table WHERE favorite = 1")
    suspend fun getFavoriteCoffees(): List<CoffeeEntity>

}