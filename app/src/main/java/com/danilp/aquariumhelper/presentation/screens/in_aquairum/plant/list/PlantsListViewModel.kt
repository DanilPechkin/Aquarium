package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.plant.repository.PlantRepository
import com.danilp.aquariumhelper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO: откуда взять aquariumId?

@HiltViewModel
class PlantsListViewModel @Inject constructor(
    private val repository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(PlantsListState())

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            val aquariumId = savedStateHandle.get<Int>("aquariumId") ?: return@launch
            state = state.copy(aquariumId = aquariumId)
            searchPlants()
        }
    }

    fun onEvent(event: PlantsListEvent) {
        when(event) {
            is PlantsListEvent.Refresh -> {
                searchPlants()
            }
            is PlantsListEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    searchPlants()
                }
            }
        }
    }

    private fun searchPlants(
        aquariumId: Int = state.aquariumId,
        name: String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            repository
                .searchPlantsByAquarium(aquariumId, name)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { plants ->
                                state = state.copy(
                                    plants = plants,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message, isLoading = false)
                        }
                    }
                }
        }
    }
}