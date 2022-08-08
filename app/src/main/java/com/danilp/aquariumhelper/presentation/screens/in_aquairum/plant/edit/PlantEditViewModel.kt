package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.domain.plant.model.Plant
import com.danilp.aquariumhelper.domain.plant.repository.PlantRepository
import com.danilp.aquariumhelper.domain.use_case.validation.*
import com.danilp.aquariumhelper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantEditViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlantRepository,
    private val validateIllumination: ValidateIllumination,
    private val validateCO2: ValidateCO2,
    private val validateKh: ValidateKh,
    private val validateGh: ValidateGh,
    private val validatePh: ValidatePh,
    private val validateTemperature: ValidateTemperature,
    private val validateName: ValidateName
) : ViewModel() {

    var state by mutableStateOf(PlantEditState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<Int>("plantId") ?: return@launch
            state = state.copy(isLoading = true)
            val plantInfoResult = repository.findPlantById(id)

            plantInfoResult.collect { result ->
                state = when (result) {
                    is Resource.Success -> {
                        state.copy(
                            plant = result.data ?: Plant.createEmpty(),
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Loading -> {
                        state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, error = result.message)
                    }
                }
            }
            state = state.copy(
                name = state.plant.name,
                genus = state.plant.genus,
                minTemperature = if (state.plant.minTemperature == 0.0) "" else state.plant.minTemperature.toString(),
                maxTemperature = if (state.plant.maxTemperature == 0.0) "" else state.plant.maxTemperature.toString(),
                minPh = if (state.plant.minPh == 0.0) "" else state.plant.minPh.toString(),
                maxPh = if (state.plant.maxPh == 0.0) "" else state.plant.maxPh.toString(),
                minGh = if (state.plant.minGh == 0.0) "" else state.plant.minGh.toString(),
                maxGh = if (state.plant.maxGh == 0.0) "" else state.plant.maxGh.toString(),
                minKh = if (state.plant.minKh == 0.0) "" else state.plant.minKh.toString(),
                maxKh = if (state.plant.maxKh == 0.0) "" else state.plant.maxKh.toString(),
                minCO2 = if (state.plant.minCO2 == 0.0) "" else state.plant.minCO2.toString(),
                minIllumination = if (state.plant.minIllumination == 0.0) "" else state.plant.minIllumination.toString(),
                description = state.plant.description
            )

            val sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.in_aquarium_info_shared_preferences_key),
                Context.MODE_PRIVATE
            )
            state = state.copy(
                plant = state.plant.copy(
                    aquariumId = sharedPreferences.getInt(
                        context.getString(R.string.saved_aquarium_id_key),
                        0
                    )
                )
            )
        }
    }

    fun onEvent(event: PlantEditEvent) {
        when (event) {
            is PlantEditEvent.InsertButtonPressed -> {
                submitData()
            }
            is PlantEditEvent.DeleteButtonPressed -> {
                viewModelScope.launch {
                    delete(state.plant)
                    validationEventChannel.send(ValidationEvent.Success)
                }
            }
            is PlantEditEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
            is PlantEditEvent.GenusChanged -> {
                state = state.copy(genus = event.genus)
            }
            is PlantEditEvent.MinTemperatureChanged -> {
                state = state.copy(minTemperature = event.temp)
            }
            is PlantEditEvent.MaxTemperatureChanged -> {
                state = state.copy(maxTemperature = event.temp)
            }
            is PlantEditEvent.MinPhChanged -> {
                state = state.copy(minPh = event.ph)
            }
            is PlantEditEvent.MaxPhChanged -> {
                state = state.copy(maxPh = event.ph)
            }
            is PlantEditEvent.MinGhChanged -> {
                state = state.copy(minGh = event.gh)
            }
            is PlantEditEvent.MaxGhChanged -> {
                state = state.copy(maxGh = event.gh)
            }
            is PlantEditEvent.MinKhChanged -> {
                state = state.copy(minKh = event.kh)
            }
            is PlantEditEvent.MaxKhChanged -> {
                state = state.copy(maxKh = event.kh)
            }
            is PlantEditEvent.MinCO2Changed -> {
                state = state.copy(minCO2 = event.co2)
            }
            is PlantEditEvent.MinIlluminationChanged -> {
                state = state.copy(minIllumination = event.illumination)
            }
            is PlantEditEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }
        }
    }

    private fun insert(plant: Plant) = viewModelScope.launch {
        repository.insert(plant)
    }

    private fun delete(plant: Plant) = viewModelScope.launch {
        repository.delete(plant)
    }

    private fun submitData() {
        val nameResult = validateName.execute(state.name)
        val minTemperatureResult = validateTemperature.execute(state.minTemperature)
        val maxTemperatureResult = validateTemperature.execute(state.maxTemperature)
        val minPhResult = validatePh.execute(state.minPh.ifEmpty { "0" })
        val maxPhResult = validatePh.execute(state.maxPh.ifEmpty { "0" })
        val minGhResult = validateGh.execute(state.minGh.ifEmpty { "0" })
        val maxGhResult = validateGh.execute(state.maxGh.ifEmpty { "0" })
        val minKhResult = validateKh.execute(state.minKh.ifEmpty { "0" })
        val maxKhResult = validateKh.execute(state.maxKh.ifEmpty { "0" })
        val minCO2Result = validateCO2.execute(state.minCO2)
        val minIlluminationResult = validateIllumination.execute(state.minIllumination)

        val hasError = listOf(
            nameResult,
            minTemperatureResult,
            maxTemperatureResult,
            minPhResult,
            maxPhResult,
            minGhResult,
            maxGhResult,
            minKhResult,
            maxKhResult,
            minCO2Result,
            minIlluminationResult
        ).any { it.errorMessage != null }

        if (hasError) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                minTemperatureError = minTemperatureResult.errorMessage,
                maxTemperatureError = maxTemperatureResult.errorMessage,
                minPhError = minPhResult.errorMessage,
                maxPhError = maxPhResult.errorMessage,
                minGhError = minGhResult.errorMessage,
                maxGhError = maxGhResult.errorMessage,
                minKhError = minKhResult.errorMessage,
                maxKhError = maxKhResult.errorMessage,
                minCO2Error = minCO2Result.errorMessage,
                minIlluminationError = minIlluminationResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            val isTempCorrect = (state.minTemperature.toDouble() < state.maxTemperature.toDouble())
            val isPhCorrect = (((state.minPh.toDoubleOrNull()
                ?: 0.0) < (state.maxPh.toDoubleOrNull() ?: 0.0)))
            val isGhCorrect = (((state.minGh.toDoubleOrNull()
                ?: 0.0) < (state.maxGh.toDoubleOrNull() ?: 0.0)))
            val isKhCorrect = (((state.minKh.toDoubleOrNull()
                ?: 0.0) < (state.maxKh.toDoubleOrNull() ?: 0.0)))

            state = state.copy(
                plant = state.plant.copy(
                    name = state.name,
                    genus = state.genus,
                    minTemperature = if (isTempCorrect) state.minTemperature.toDouble()
                    else state.maxTemperature.toDouble(),
                    maxTemperature = if (isTempCorrect) state.maxTemperature.toDouble()
                    else state.minTemperature.toDouble(),
                    minPh = if (isPhCorrect) state.minPh.toDoubleOrNull() ?: 0.0
                    else state.maxPh.toDoubleOrNull() ?: 0.0,
                    maxPh = if (isPhCorrect) state.maxPh.toDoubleOrNull() ?: 0.0
                    else state.minPh.toDoubleOrNull() ?: 0.0,
                    minGh = if (isGhCorrect) state.minGh.toDoubleOrNull() ?: 0.0
                    else state.maxGh.toDoubleOrNull() ?: 0.0,
                    maxGh = if (isGhCorrect) state.maxGh.toDoubleOrNull() ?: 0.0
                    else state.minGh.toDoubleOrNull() ?: 0.0,
                    minKh = if (isKhCorrect) state.minKh.toDoubleOrNull() ?: 0.0
                    else state.maxKh.toDoubleOrNull() ?: 0.0,
                    maxKh = if (isKhCorrect) state.maxKh.toDoubleOrNull() ?: 0.0
                    else state.minKh.toDoubleOrNull() ?: 0.0,
                    minIllumination = state.minIllumination.toDoubleOrNull() ?: 0.0,
                    minCO2 = state.minCO2.toDoubleOrNull() ?: 0.0,
                    description = state.description
                )
            )

            insert(state.plant)
            validationEventChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}