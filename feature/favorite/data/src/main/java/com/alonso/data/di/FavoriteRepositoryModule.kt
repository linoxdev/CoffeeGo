package com.alonso.data.di

import com.alonso.data.repository.FavoriteRepositoryImp
import com.alonso.domain.repository.FavoriteRepository
import com.alonso.data.local.db.CoffeeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavoriteRepositoryModule {

    @Singleton
    @Provides
    internal fun provideFavoriteRepository(coffeeDao: CoffeeDao): FavoriteRepository {
        return FavoriteRepositoryImp(coffeeDao)
    }

}