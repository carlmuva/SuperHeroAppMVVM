package com.example.appsuperheromvvm.di

import com.example.appsuperheromvvm.application.AppConstants
import com.example.appsuperheromvvm.domain.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Josias de la Cruz on 03, junio, 2023
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule  {

    @Provides
    @Singleton
    fun provideRetrofit(): WebService {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }

}