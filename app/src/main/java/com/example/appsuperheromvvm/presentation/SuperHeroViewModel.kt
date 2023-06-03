package com.example.appsuperheromvvm.presentation

import androidx.lifecycle.*
import com.example.appsuperheromvvm.core.Resource
import com.example.appsuperheromvvm.domain.SuperHeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val repo: SuperHeroRepository,
): ViewModel() {

    private val superHeroData = MutableLiveData<String>()

    fun setSuperHeroName(superHeroName: String) {
        superHeroData.value = superHeroName
    }

    init {
        setSuperHeroName("batman")
    }

    val fetchMainScreenSuperHeros =
        superHeroData.distinctUntilChanged().switchMap { superHeroName ->
            liveData(Dispatchers.IO) {
                emit(Resource.Loading())
                try {
                    emit(Resource.Success(repo.getSuperHero(superHeroName)))
                } catch (e: Exception) {
                    emit(Resource.Failure(e))
                }
            }

        }
}