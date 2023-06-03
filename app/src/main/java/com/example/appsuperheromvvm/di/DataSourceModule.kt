package com.example.appsuperheromvvm.di

import com.example.appsuperheromvvm.data.remote.SuperHeroDataSource
import com.example.appsuperheromvvm.domain.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Josias de la Cruz on 03, junio, 2023
 */

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideSuperHeroDataSource(webService: WebService): SuperHeroDataSource {
        return SuperHeroDataSource(webService)
    }

}