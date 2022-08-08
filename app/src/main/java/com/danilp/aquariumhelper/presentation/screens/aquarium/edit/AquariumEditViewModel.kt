package com.danilp.aquariumhelper.presentation.screens.aquarium.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.domain.aquairum.repository.AquariumRepository
import com.danilp.aquariumhelper.domain.use_case.validation.ValidateLiters
import com.danilp.aquariumhelper.domain.use_case.validation.ValidateName
import com.danilp.aquariumhelper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AquariumEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AquariumRepository,
    private val validateName: ValidateName,
    private val validateLiters: ValidateLiters,
) : ViewModel() {

    var state by mutableStateOf(AquariumEditState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<Int>("aquariumId") ?: return@launch
            state = state.copy(isLoading = true)
            val aquariumInfoResult = repository.findAquariumById(id)

            aquariumInfoResult.collect { result ->
                state = when (result) {
                    is Resource.Success -> {
                        state.copy(
                            aquarium = result.data ?: Aquarium.createEmpty(),
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Loading -> {
                        state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Error -> {
                        state.copy(error = result.message, isLoading = false)
                    }
                }
            }
            state = state.copy(
                name = state.aquarium.name,
                liters = if (state.aquarium.liters == 0.0) "" else state.aquarium.liters.toString(),
                description = state.aquarium.description
            )
        }

    }

    fun onEvent(event: AquariumEditEvent) {
        when (event) {
            is AquariumEditEvent.InsertButtonPressed -> {
                submitData()
            }
            is AquariumEditEvent.DeleteButtonPressed -> {
                viewModelScope.launch {
                    delete(state.aquarium)
                    validationEventChannel.send(ValidationEvent.Success)
                }
            }
            is AquariumEditEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
            is AquariumEditEvent.LitersChanged -> {
                state = state.copy(liters = event.liters)
            }
            is AquariumEditEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }
            is AquariumEditEvent.ImagePicked -> {
                state = state.copy(
                    aquarium = state.aquarium.copy(imageUri = event.imageUri.toString())
                )
            }
        }
    }

    private fun insert(aquarium: Aquarium) = viewModelScope.launch {
        repository.insert(aquarium)
    }

    private fun delete(aquarium: Aquarium) = viewModelScope.launch {
        repository.delete(aquarium)
    }

    private fun submitData() {
        val nameResult = validateName.execute(state.name)
        val litersResult = validateLiters.execute(state.liters)

        val hasError = listOf(
            nameResult,
            litersResult
        ).any { it.errorMessage != null }

        if (hasError) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                litersError = litersResult.errorMessage
            )
            return
        }

        state = state.copy(
            aquarium = state.aquarium.copy(
                name = state.name,
                liters = state.liters.toDouble(),
                description = state.description
            )
        )

        viewModelScope.launch {
            insert(state.aquarium)
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}
