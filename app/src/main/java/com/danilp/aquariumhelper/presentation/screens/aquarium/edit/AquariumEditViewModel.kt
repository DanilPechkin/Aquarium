package com.danilp.aquariumhelper.presentation.screens.aquarium.edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.domain.aquairum.repository.AquariumRepository
import com.danilp.aquariumhelper.domain.use_case.calculation.conversion.capacity.ConvertLiters
import com.danilp.aquariumhelper.domain.use_case.validation.Validate
import com.danilp.aquariumhelper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AquariumEditViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val repository: AquariumRepository,
    private val validate: Validate,
    private val convertLiters: ConvertLiters
) : ViewModel() {

    var state by mutableStateOf(AquariumEditState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private lateinit var measureCubicFeet: String
    private lateinit var measureUSCups: String
    private lateinit var measureTeaspoons: String
    private lateinit var measureTablespoons: String
    private lateinit var measureMilliliters: String
    private lateinit var measureMetricCups: String
    private lateinit var measureGallons: String
    private lateinit var measureCubicMeters: String
    private lateinit var measureCubicInches: String
    private lateinit var measureLiters: String

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

            val sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.in_aquarium_info_shared_preferences_key),
                Context.MODE_PRIVATE
            )

            state = state.copy(
                capacityMeasure = sharedPreferences.getString(
                    context.getString(R.string.capacity_measure_id_key),
                    context.getString(R.string.capacity_measure_liters)
                ) ?: context.getString(R.string.capacity_measure_liters)
            )

            measureCubicFeet = context.getString(R.string.capacity_measure_cubic_feet)
            measureUSCups = context.getString(R.string.capacity_measure_us_cups)
            measureTeaspoons = context.getString(R.string.capacity_measure_teaspoons)
            measureTablespoons = context.getString(R.string.capacity_measure_tablespoons)
            measureMilliliters = context.getString(R.string.capacity_measure_milliliters)
            measureMetricCups = context.getString(R.string.capacity_measure_metric_cups)
            measureGallons = context.getString(R.string.capacity_measure_gallons)
            measureCubicMeters = context.getString(R.string.capacity_measure_cubic_meters)
            measureCubicInches = context.getString(R.string.capacity_measure_cubic_inches)
            measureLiters = context.getString(R.string.capacity_measure_liters)

            state = state.copy(
                name = state.aquarium.name,
                liters = if (state.aquarium.liters == 0.0) "" else
                    when (state.capacityMeasure) {
                        measureLiters -> state.aquarium.liters.toString()
                        measureCubicFeet -> convertLiters.toCubicFeet(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureUSCups -> convertLiters.toUSCups(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureTeaspoons -> convertLiters.toTeaspoons(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureTablespoons -> convertLiters.toTablespoons(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureMilliliters -> convertLiters.toMilliliters(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureMetricCups -> convertLiters.toMetricCups(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureGallons -> convertLiters.toGallons(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureCubicMeters -> convertLiters.toCubicMeters(
                            liters = state.aquarium.liters
                        ).result.toString()
                        measureCubicInches -> convertLiters.toCubicInches(
                            liters = state.aquarium.liters
                        ).result.toString()
                        else -> state.aquarium.liters.toString()
                    },
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
        val nameResult = validate.string(state.name)
        val litersResult = validate.decimal(state.liters, isRequired = true)

        val hasError = listOf(
            nameResult,
            litersResult
        ).any { it.errorMessage != null }

        if (hasError) {
            state = state.copy(
                nameErrorCode = nameResult.errorMessage,
                litersErrorCode = litersResult.errorMessage
            )
            return
        }

        state = state.copy(
            aquarium = state.aquarium.copy(
                name = state.name,
                liters =
                when (state.capacityMeasure) {
                    measureLiters -> state.liters.toDouble()
                    measureCubicFeet -> convertLiters.toCubicFeet(
                        feet = state.liters.toDouble()
                    ).result
                    measureUSCups -> convertLiters.toUSCups(
                        cups = state.liters.toDouble()
                    ).result
                    measureTeaspoons -> convertLiters.toTeaspoons(
                        teaspoons = state.liters.toDouble()
                    ).result
                    measureTablespoons -> convertLiters.toTablespoons(
                        tablespoons = state.liters.toDouble()
                    ).result
                    measureMilliliters -> convertLiters.toMilliliters(
                        milliliters = state.liters.toDouble()
                    ).result
                    measureMetricCups -> convertLiters.toMetricCups(
                        cups = state.liters.toDouble()
                    ).result
                    measureGallons -> convertLiters.toGallons(
                        gallons = state.liters.toDouble()
                    ).result
                    measureCubicMeters -> convertLiters.toCubicMeters(
                        meters = state.liters.toDouble()
                    ).result
                    measureCubicInches -> convertLiters.toCubicInches(
                        inches = state.liters.toDouble()
                    ).result
                    else -> state.liters.toDouble()
                },
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
