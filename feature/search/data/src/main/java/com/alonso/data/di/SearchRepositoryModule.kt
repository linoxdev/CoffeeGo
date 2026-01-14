package com.alonso.data.di

import com.alonso.data.datasource.SearchDataSource
import com.alonso.data.repository.SearchRepositoryImpl
import com.alonso.domain.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class SearchRepositoryModule {

    @Provides
    internal fun provideSearchRepository(
        dataSource: SearchDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(dataSource)
    }

}