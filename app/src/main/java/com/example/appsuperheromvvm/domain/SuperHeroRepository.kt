package com.example.appsuperheromvvm.domain

import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.data.model.SuperHeroDetail

interface SuperHeroRepository {

    suspend fun getSuperHero(superHeroName:String): SuperHero

    suspend fun getSuperHeroDetail(superHeroId:String): SuperHeroDetail

}