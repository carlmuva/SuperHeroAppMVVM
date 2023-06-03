package com.example.appsuperheromvvm.domain

import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.data.remote.SuperHeroDataSource
import javax.inject.Inject

class SuperHeroRepositoryImpl @Inject constructor(
    private val dataSource: SuperHeroDataSource,
): SuperHeroRepository {

    override suspend fun getSuperHero(superHeroName:String): SuperHero {
        return dataSource.getSuperHero(superHeroName)
    }



}