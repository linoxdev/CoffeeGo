package com.alonso.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Database(
    entities = [CoffeeEntity::class],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun coffeeDao(): CoffeeDao

    companion object {
        const val DATABASE_NAME = "coffee_go_app_database.db"
    }
}

@Entity(tableName = "coffee_table")
data class CoffeeEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "ranking")
    val ranking: Double,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "volume")
    val volume: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean
)
