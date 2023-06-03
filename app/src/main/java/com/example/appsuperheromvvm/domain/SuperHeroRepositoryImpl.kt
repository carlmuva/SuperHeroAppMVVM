package com.example.appsuperheromvvm.domain

import com.example.appsuperheromvvm.core.Resource
import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.data.remote.SuperHeroDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class SuperHeroRepositoryImpl @Inject constructor(
    private val dataSource: SuperHeroDataSource,
): SuperHeroRepository {

    override suspend fun getSuperHero(superHeroName:String): Flow<Resource<SuperHero>> {
        return flow {
            try {
                emit(Resource.Success(dataSource.getSuperHero(superHeroName)))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }



}