package com.example.appsuperheromvvm.di

import com.example.appsuperheromvvm.data.remote.SuperHeroDataSource
import com.example.appsuperheromvvm.domain.SuperHeroRepository
import com.example.appsuperheromvvm.domain.SuperHeroRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Josias de la Cruz on 03, junio, 2023
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideSuperHeroRepository(dataSource: SuperHeroDataSource): SuperHeroRepository {
        return SuperHeroRepositoryImpl(dataSource)
    }
}