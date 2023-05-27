package com.example.appsuperheromvvm.presentation

import androidx.lifecycle.*
import com.example.appsuperheromvvm.core.Resource
import com.example.appsuperheromvvm.domain.SuperHeroRepository
import kotlinx.coroutines.Dispatchers

class SuperHeroViewModel(private val repo: SuperHeroRepository): ViewModel() {

    private val superHeroData= MutableLiveData<String>()

    fun setSuperHeroName(superHeroName:String){
        superHeroData.value=superHeroName
    }

    init {
        setSuperHeroName("batman")
    }

    val fetchMainScreenSuperHeros = superHeroData.distinctUntilChanged().switchMap{superHeroName->
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try {
                emit(Resource.Success(repo.getSuperHero(superHeroName)))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }

    }



}

class SuperheroViewModelFactory(private val repo: SuperHeroRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SuperHeroRepository::class.java).newInstance(repo)
    }
}