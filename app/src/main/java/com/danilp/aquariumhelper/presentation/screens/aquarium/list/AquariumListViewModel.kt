package com.danilp.aquariumhelper.presentation.screens.aquarium.list

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.domain.aquairum.repository.AquariumRepository
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
class AquariumListViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: AquariumRepository,
    logService: LogService
) : ProfessionalAquaristViewModel(logService) {

    var state by mutableStateOf(AquariumListState())

    private var sharedPreferences: SharedPreferences? = null
    private var aquariumIdKey: String = ""

    private var searchJob: Job? = null

    init {
        viewModelScope.launch(logErrorExceptionHandler) {
            searchAquariums()

            sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.in_aquarium_info_shared_preferences_key),
                Context.MODE_PRIVATE
            )
            aquariumIdKey = context.getString(R.string.saved_aquarium_id_key)
        }
    }

    fun onEvent(event: AquariumListEvent) {
        when (event) {
            is AquariumListEvent.Refresh -> {
                searchAquariums()
            }
            is AquariumListEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch(logErrorExceptionHandler) {
                    delay(500L)
                    searchAquariums()
                }
            }
            is AquariumListEvent.OnAquariumClicked -> {
                viewModelScope.launch(logErrorExceptionHandler) {
                    saveAquariumId(event.aquariumId)
                }
            }
        }
    }

    private fun searchAquariums(
        name: String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch(logErrorExceptionHandler) {
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

    private fun saveAquariumId(id: Int) {
        with(sharedPreferences?.edit()) {
            this?.putInt(aquariumIdKey, id) ?: return
            commit()
        }
    }

}