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
import com.danilp.aquariumhelper.domain.use_case.calculation.conversion.alkalinity.ConvertDKH
import com.danilp.aquariumhelper.domain.use_case.calculation.conversion.temperature.ConvertCelsius
import com.danilp.aquariumhelper.domain.use_case.validation.Validate
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
    private val validate: Validate,
    private val convertCelsius: ConvertCelsius,
    private val convertDKH: ConvertDKH
) : ViewModel() {

    var state by mutableStateOf(PlantEditState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private lateinit var measureCelsius: String
    private lateinit var measureKelvin: String
    private lateinit var measureFahrenheit: String
    private lateinit var measureDKH: String
    private lateinit var measureMeqL: String
    private lateinit var measurePpm: String

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
                ),
                tempMeasure = sharedPreferences.getString(
                    context.getString(R.string.temperature_measure_id_key),
                    context.getString(R.string.temp_measure_celsius)
                ) ?: context.getString(R.string.temp_measure_celsius),
                alkalinityMeasure = sharedPreferences.getString(
                    context.getString(R.string.alkalinity_measure_id_key),
                    context.getString(R.string.alkalinity_measure_dkh)
                ) ?: context.getString(R.string.alkalinity_measure_dkh)
            )

            measureCelsius = context.getString(R.string.temp_measure_celsius)
            measureFahrenheit = context.getString(R.string.temp_measure_fahrenheit)
            measureKelvin = context.getString(R.string.temp_measure_kelvin)
            measureDKH = context.getString(R.string.alkalinity_measure_dkh)
            measurePpm = context.getString(R.string.alkalinity_measure_ppm)
            measureMeqL = context.getString(R.string.alkalinity_measure_meql)

            state = state.copy(
                name = state.plant.name,
                genus = state.plant.genus,
                minTemperature = if (state.plant.minTemperature == 0.0) "" else
                    when (state.tempMeasure) {
                        measureCelsius -> state.plant.minTemperature.toString()
                        measureKelvin -> convertCelsius.toKelvin(
                            celsius = state.plant.minTemperature
                        ).result.toString()
                        measureFahrenheit -> convertCelsius.toFahrenheit(
                            celsius = state.plant.minTemperature
                        ).result.toString()
                        else -> state.plant.minTemperature.toString()
                    },
                maxTemperature = if (state.plant.maxTemperature == 0.0) "" else
                    when (state.tempMeasure) {
                        measureCelsius -> state.plant.maxTemperature.toString()
                        measureKelvin -> convertCelsius.toKelvin(
                            celsius = state.plant.maxTemperature
                        ).result.toString()
                        measureFahrenheit -> convertCelsius.toFahrenheit(
                            celsius = state.plant.maxTemperature
                        ).result.toString()
                        else -> state.plant.maxTemperature.toString()
                    },
                minPh = if (state.plant.minPh == 0.0) "" else
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.plant.minPh.toString()
                        measurePpm -> convertDKH.toPpm(dKH = state.plant.minPh).result.toString()
                        measureMeqL -> convertDKH.toMeqL(dKH = state.plant.minPh).result.toString()
                        else -> state.plant.minPh.toString()
                    },
                maxPh = if (state.plant.maxPh == 0.0) "" else
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.plant.maxPh.toString()
                        measurePpm -> convertDKH.toPpm(dKH = state.plant.maxPh).result.toString()
                        measureMeqL -> convertDKH.toMeqL(dKH = state.plant.maxPh).result.toString()
                        else -> state.plant.maxPh.toString()
                    },
                minGh = if (state.plant.minGh == 0.0) "" else
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.plant.minGh.toString()
                        measurePpm -> convertDKH.toPpm(dKH = state.plant.minGh).result.toString()
                        measureMeqL -> convertDKH.toMeqL(dKH = state.plant.minGh).result.toString()
                        else -> state.plant.minGh.toString()
                    },
                maxGh = if (state.plant.maxGh == 0.0) "" else
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.plant.maxGh.toString()
                        measurePpm -> convertDKH.toPpm(dKH = state.plant.maxGh).result.toString()
                        measureMeqL -> convertDKH.toMeqL(dKH = state.plant.maxGh).result.toString()
                        else -> state.plant.maxGh.toString()
                    },
                minKh = if (state.plant.minKh == 0.0) "" else
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.plant.minKh.toString()
                        measurePpm -> convertDKH.toPpm(dKH = state.plant.minKh).result.toString()
                        measureMeqL -> convertDKH.toMeqL(dKH = state.plant.minKh).result.toString()
                        else -> state.plant.minKh.toString()
                    },
                maxKh = if (state.plant.maxKh == 0.0) "" else
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.plant.maxKh.toString()
                        measurePpm -> convertDKH.toPpm(dKH = state.plant.maxKh).result.toString()
                        measureMeqL -> convertDKH.toMeqL(dKH = state.plant.maxKh).result.toString()
                        else -> state.plant.maxKh.toString()
                    },
                minCO2 = if (state.plant.minCO2 == 0.0) "" else state.plant.minCO2.toString(),
                minIllumination = if (state.plant.minIllumination == 0.0) "" else state.plant.minIllumination.toString(),
                description = state.plant.description
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
            is PlantEditEvent.ImagePicked -> {
                state = state.copy(
                    plant = state.plant.copy(imageUri = event.imageUri)
                )
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
        val nameResult = validate.string(state.name)
        val minTemperatureResult = validate.decimal(state.minTemperature, isRequired = true)
        val maxTemperatureResult = validate.decimal(state.maxTemperature, isRequired = true)
        val minPhResult = validate.decimal(state.minPh)
        val maxPhResult = validate.decimal(state.maxPh)
        val minGhResult = validate.decimal(state.minGh)
        val maxGhResult = validate.decimal(state.maxGh)
        val minKhResult = validate.decimal(state.minKh)
        val maxKhResult = validate.decimal(state.maxKh)
        val minCO2Result = validate.decimal(state.minCO2)
        val minIlluminationResult = validate.decimal(state.minIllumination)

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
                nameErrorCode = nameResult.errorMessage,
                minTemperatureErrorCode = minTemperatureResult.errorMessage,
                maxTemperatureErrorCode = maxTemperatureResult.errorMessage,
                minPhErrorCode = minPhResult.errorMessage,
                maxPhErrorCode = maxPhResult.errorMessage,
                minGhErrorCode = minGhResult.errorMessage,
                maxGhErrorCode = maxGhResult.errorMessage,
                minKhErrorCode = minKhResult.errorMessage,
                maxKhErrorCode = maxKhResult.errorMessage,
                minCO2ErrorCode = minCO2Result.errorMessage,
                minIlluminationErrorCode = minIlluminationResult.errorMessage
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

            if (!isTempCorrect) {
                kotlin.run {
                    val temp = state.minTemperature
                    state = state.copy(minTemperature = state.maxTemperature)
                    state = state.copy(maxTemperature = temp)
                }
            }

            if (!isPhCorrect) {
                kotlin.run {
                    val temp = state.minPh
                    state = state.copy(minPh = state.maxPh)
                    state = state.copy(maxPh = temp)
                }
            }

            if (!isGhCorrect) {
                kotlin.run {
                    val temp = state.minGh
                    state = state.copy(minGh = state.maxGh)
                    state = state.copy(maxGh = temp)
                }
            }

            if (!isKhCorrect) {
                kotlin.run {
                    val temp = state.minKh
                    state = state.copy(minKh = state.maxKh)
                    state = state.copy(maxKh = temp)
                }
            }

            state = state.copy(
                plant = state.plant.copy(
                    name = state.name,
                    genus = state.genus,
                    minTemperature =
                    when (state.tempMeasure) {
                        measureCelsius -> state.minTemperature.toDouble()
                        measureFahrenheit -> convertCelsius.toFahrenheit(
                            fahrenheit = state.minTemperature.toDouble()
                        ).result
                        measureKelvin -> convertCelsius.toKelvin(
                            kelvin = state.minTemperature.toDouble()
                        ).result
                        else -> state.minTemperature.toDouble()
                    },
                    maxTemperature =
                    when (state.tempMeasure) {
                        measureCelsius -> state.maxTemperature.toDouble()
                        measureFahrenheit -> convertCelsius.toFahrenheit(
                            fahrenheit = state.maxTemperature.toDouble()
                        ).result
                        measureKelvin -> convertCelsius.toKelvin(
                            kelvin = state.maxTemperature.toDouble()
                        ).result
                        else -> state.maxTemperature.toDouble()
                    },
                    minPh =
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.minPh.toDouble()
                        measurePpm -> convertDKH.toPpm(
                            ppm = state.minPh.toDouble()
                        ).result
                        measureMeqL -> convertDKH.toMeqL(
                            meqL = state.minPh.toDouble()
                        ).result
                        else -> state.minPh.toDouble()
                    },
                    maxPh =
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.maxPh.toDouble()
                        measurePpm -> convertDKH.toPpm(
                            ppm = state.maxPh.toDouble()
                        ).result
                        measureMeqL -> convertDKH.toMeqL(
                            meqL = state.maxPh.toDouble()
                        ).result
                        else -> state.maxPh.toDouble()
                    },
                    minGh =
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.minGh.toDouble()
                        measurePpm -> convertDKH.toPpm(
                            ppm = state.minGh.toDouble()
                        ).result
                        measureMeqL -> convertDKH.toMeqL(
                            meqL = state.minGh.toDouble()
                        ).result
                        else -> state.minGh.toDouble()
                    },
                    maxGh =
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.maxGh.toDouble()
                        measurePpm -> convertDKH.toPpm(
                            ppm = state.maxGh.toDouble()
                        ).result
                        measureMeqL -> convertDKH.toMeqL(
                            meqL = state.maxGh.toDouble()
                        ).result
                        else -> state.maxGh.toDouble()
                    },
                    minKh =
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.minKh.toDouble()
                        measurePpm -> convertDKH.toPpm(
                            ppm = state.minKh.toDouble()
                        ).result
                        measureMeqL -> convertDKH.toMeqL(
                            meqL = state.minKh.toDouble()
                        ).result
                        else -> state.minKh.toDouble()
                    },
                    maxKh =
                    when (state.alkalinityMeasure) {
                        measureDKH -> state.maxKh.toDouble()
                        measurePpm -> convertDKH.toPpm(
                            ppm = state.maxKh.toDouble()
                        ).result
                        measureMeqL -> convertDKH.toMeqL(
                            meqL = state.maxKh.toDouble()
                        ).result
                        else -> state.maxKh.toDouble()
                    },
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