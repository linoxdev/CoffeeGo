package com.alonso.data.di

import android.content.Context
import androidx.room.Room
import com.alonso.data.local.db.AppDatabase
import com.alonso.data.local.db.CoffeeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.Companion.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideCoffeeDao(database: AppDatabase): CoffeeDao {
        return database.coffeeDao()
    }
}