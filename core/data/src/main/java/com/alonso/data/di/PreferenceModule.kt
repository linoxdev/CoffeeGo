package com.alonso.data.di

import android.content.Context
import com.alonso.data.local.db.AppDatabase
import com.alonso.data.local.db.CoffeeDao
import com.alonso.data.local.preferences.DataStoreUtil
import com.alonso.data.local.preferences.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {

    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil =
        DataStoreUtil(context)


    @Provides
    @Singleton
    fun provideUserPreferencesRepository(dataStore: DataStoreUtil): UserPreferencesRepository {
        return UserPreferencesRepository(dataStore)
    }

}