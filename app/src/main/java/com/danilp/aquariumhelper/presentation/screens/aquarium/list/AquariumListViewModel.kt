package com.danilp.aquariumhelper.presentation.screens.aquarium.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.domain.aquairum.repository.AquariumRepository
import com.danilp.aquariumhelper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AquariumListViewModel @Inject constructor(
    private val repository: AquariumRepository
) : ViewModel() {

    var state by mutableStateOf(AquariumListState())

    private var searchJob: Job? = null

    init {
        searchAquariums()
    }

    fun onEvent(event: AquariumListEvent) {
        when (event) {
            is AquariumListEvent.Refresh -> {
                searchAquariums()
            }
            is AquariumListEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    searchAquariums()
                }
            }
        }
    }

    private fun searchAquariums(
        name: String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            repository
                .searchAquariums(name)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { aquariums ->
                                state = state.copy(
                                    aquariums = aquariums,
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