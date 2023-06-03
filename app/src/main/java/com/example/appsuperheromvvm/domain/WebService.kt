package com.example.appsuperheromvvm.domain

import com.example.appsuperheromvvm.application.AppConstants
import com.example.appsuperheromvvm.data.model.SuperHero
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("10161266202252240/search/{name}")
    suspend fun getSuperHero(@Path("name") superheroName: String): SuperHero

}