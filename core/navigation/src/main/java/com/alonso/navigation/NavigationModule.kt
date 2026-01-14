package com.alonso.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Binds
    @Singleton
    fun provideComposeNavigator(
        myShopComposeNavigator: CoffeeNavigator,
    ): AppNavigator
}