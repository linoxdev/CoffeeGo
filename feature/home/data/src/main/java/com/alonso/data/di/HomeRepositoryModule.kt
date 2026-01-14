package com.alonso.data.di


import com.alonso.data.datasource.HomeDataSource
import com.alonso.data.repository.HomeRepositoryImp
import com.alonso.domain.repository.HomeRepository
import com.alonso.data.local.db.CoffeeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object HomeRepositoryModule {

    @Provides
    fun provideHomeRepository(
        homeDataSource: HomeDataSource,
        coffeeDao: CoffeeDao
    ): HomeRepository {
        return HomeRepositoryImp(homeDataSource, coffeeDao)
    }


}