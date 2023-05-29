package com.example.appsuperheromvvm.domain

import com.example.appsuperheromvvm.data.model.SuperHero

interface SuperHeroRepository {

    suspend fun getSuperHero(superHeroName:String): SuperHero


}