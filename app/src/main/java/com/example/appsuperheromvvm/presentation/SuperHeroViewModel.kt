package com.example.appsuperheromvvm.presentation

import androidx.lifecycle.*
import com.example.appsuperheromvvm.core.Resource
import com.example.appsuperheromvvm.data.model.SuperHero
import com.example.appsuperheromvvm.domain.SuperHeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val repo: SuperHeroRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(SuperHeroUiState())
    val uiState: StateFlow<SuperHeroUiState> = _uiState.asStateFlow()

    init {
        setSuperHeroName("g")
    }
    fun setSuperHeroName(superHeroName: String) {
        //superHeroData.value = superHeroName

        viewModelScope.launch(Dispatchers.IO) {
            repo.getSuperHero(superHeroName)
                .onStart {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }
                .catch {
                    _uiState.update {
                        it.copy(isLoading = false)
                    }
                }
                .collect { resource ->
                    when(resource){
                        is Resource.Failure -> {
                            _uiState.update {
                                it.copy(isLoading = false)
                            }
                        }
                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    superHero = resource.data
                                )
                            }
                        }
                    }
                }
        }
    }
    data class SuperHeroUiState(
        val superHero: SuperHero? = null,
        val isLoading: Boolean = false,
    )
}

