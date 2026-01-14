package com.alonso.data.di

import com.alonso.data.datasource.SearchDataSource
import com.alonso.data.datasource.SearchDataSourceImpl
import com.alonso.data.local.db.CoffeeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchDataSourceModule {

    @Singleton
    @Provides
    internal fun provideSearchDataSource(coffeeDao: CoffeeDao): SearchDataSource {
        return SearchDataSourceImpl(coffeeDao)
    }

}