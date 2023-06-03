package com.example.appsuperheromvvm.data.remote

import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.domain.WebService

class SuperHeroDataSource(private val webService: WebService) {

    suspend fun getSuperHero(superHeroName:String):SuperHero{
        return webService.getSuperHero(superHeroName)
    }

}