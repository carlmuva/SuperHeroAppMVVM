package com.example.appsuperheromvvm.domain

import com.example.appsuperheromvvm.core.Resource
import com.example.appsuperheromvvm.data.model.SuperHero
import kotlinx.coroutines.flow.Flow


interface SuperHeroRepository {

    suspend fun getSuperHero(superHeroName:String): Flow<Resource<SuperHero>>



}