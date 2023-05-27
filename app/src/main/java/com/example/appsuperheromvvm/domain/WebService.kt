package com.example.appsuperheromvvm.domain

import com.example.appsuperheromvvm.application.AppConstants
import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.data.model.SuperHeroDetail
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService{

    @GET("10161266202252240/search/{name}")
    suspend fun getSuperHero(@Path("name")superheroName:String):SuperHero

    @GET("10161266202252240/search/{id}")
    suspend fun getSuperHeroDetail(@Path("id")superheroId:String):SuperHeroDetail

}

object RetrofitClient{

    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

}