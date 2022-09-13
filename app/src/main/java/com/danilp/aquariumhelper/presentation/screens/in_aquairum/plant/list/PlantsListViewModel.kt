package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.list

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.domain.plant.repository.PlantRepository
import com.danilp.aquariumhelper.domain.service.LogService
import com.danilp.aquariumhelper.presentation.screens.ProfessionalAquaristViewModel
import com.danilp.aquariumhelper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantsListViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: PlantRepository,
    logService: LogService
) : ProfessionalAquaristViewModel(logService) {

    var state by mutableStateOf(PlantsListState())

    private var searchJob: Job? = null

    init {
        viewModelScope.launch(logErrorExceptionHandler) {
            val sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.in_aquarium_info_shared_preferences_key),
                Context.MODE_PRIVATE
            )
            state = state.copy(
                aquariumId = sharedPreferences.getInt(
                    context.getString(R.string.saved_aquarium_id_key),
                    0
                )
            )
            searchPlants()
        }
    }

    fun onEvent(event: PlantsListEvent) {
        when (event) {
            is PlantsListEvent.Refresh -> {
                searchPlants()
            }
            is PlantsListEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch(logErrorExceptionHandler) {
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
        viewModelScope.launch(logErrorExceptionHandler) {
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