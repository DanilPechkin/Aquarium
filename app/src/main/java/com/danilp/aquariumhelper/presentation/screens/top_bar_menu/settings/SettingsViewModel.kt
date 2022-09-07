package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    var state by mutableStateOf(SettingsState())

    private val savingEventChannel = Channel<SavingEvent>()
    val savingEvents = savingEventChannel.receiveAsFlow()

    private var sharedPreferences: SharedPreferences? = null

    private lateinit var measureCapacity: String
    private lateinit var measureMetric: String
    private lateinit var measureAlkalinity: String
    private lateinit var measureTemperature: String

    private lateinit var measureCapacityDefault: String
    private lateinit var measureMetricDefault: String
    private lateinit var measureAlkalinityDefault: String
    private lateinit var measureTemperatureDefault: String

    init {
        viewModelScope.launch {
            sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.in_aquarium_info_shared_preferences_key),
                Context.MODE_PRIVATE
            )

            measureAlkalinity = context.getString(R.string.alkalinity_measure_id_key)
            measureCapacity = context.getString(R.string.capacity_measure_id_key)
            measureMetric = context.getString(R.string.metric_measure_id_key)
            measureTemperature = context.getString(R.string.temperature_measure_id_key)

            measureAlkalinityDefault = context.getString(R.string.alkalinity_measure_dkh)
            measureCapacityDefault = context.getString(R.string.capacity_measure_liters)
            measureMetricDefault = context.getString(R.string.metric_measure_meters)
            measureTemperatureDefault = context.getString(R.string.temp_measure_celsius)

            state = state.copy(
                alkalinityMeasure = sharedPreferences?.getString(
                    measureAlkalinity,
                    measureAlkalinityDefault
                ) ?: measureAlkalinityDefault,
                capacityMeasure = sharedPreferences?.getString(
                    measureCapacity,
                    measureCapacityDefault
                ) ?: measureCapacityDefault,
                metricMeasure = sharedPreferences?.getString(
                    measureMetric,
                    measureMetricDefault
                ) ?: measureMetricDefault,
                temperatureMeasure = sharedPreferences?.getString(
                    measureTemperature,
                    measureTemperatureDefault
                ) ?: measureTemperatureDefault,
                capacityList = listOf(
                    context.getString(R.string.capacity_measure_liters),
                    context.getString(R.string.capacity_measure_cubic_inches),
                    context.getString(R.string.capacity_measure_gallons),
                    context.getString(R.string.capacity_measure_cubic_meters),
                    context.getString(R.string.capacity_measure_metric_cups),
                    context.getString(R.string.capacity_measure_milliliters),
                    context.getString(R.string.capacity_measure_tablespoons),
                    context.getString(R.string.capacity_measure_teaspoons),
                    context.getString(R.string.capacity_measure_us_cups),
                    context.getString(R.string.capacity_measure_cubic_feet)
                ),
                temperatureList = listOf(
                    context.getString(R.string.temp_measure_celsius),
                    context.getString(R.string.temp_measure_kelvin),
                    context.getString(R.string.temp_measure_fahrenheit)
                ),
                alkalinityList = listOf(
                    context.getString(R.string.alkalinity_measure_dkh),
                    context.getString(R.string.alkalinity_measure_ppm),
                    context.getString(R.string.alkalinity_measure_meql)
                ),
                metricList = listOf(
                    context.getString(R.string.metric_measure_meters),
                    context.getString(R.string.metric_measure_centimeters),
                    context.getString(R.string.metric_measure_feet),
                    context.getString(R.string.metric_measure_inches),
                    context.getString(R.string.metric_measure_millimeters)
                )
            )


        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SaveButtonPressed -> {
                viewModelScope.launch {
                    with(sharedPreferences?.edit()) {
                        this?.putString(measureAlkalinity, state.alkalinityMeasure)
                        this?.putString(measureCapacity, state.capacityMeasure)
                        this?.putString(measureMetric, state.metricMeasure)
                        this?.putString(measureTemperature, state.temperatureMeasure)
                        this?.apply()
                    }
                    savingEventChannel.send(SavingEvent.Success)
                }
            }
            is SettingsEvent.DefaultButtonPressed -> {
                state = state.copy(
                    alkalinityMeasure = measureAlkalinityDefault,
                    capacityMeasure = measureCapacityDefault,
                    metricMeasure = measureMetricDefault,
                    temperatureMeasure = measureTemperatureDefault
                )
            }
            is SettingsEvent.AlkalinityChanged -> {
                state = state.copy(alkalinityMeasure = event.alkalinity)
            }
            is SettingsEvent.CapacityChanged -> {
                state = state.copy(capacityMeasure = event.capacity)
            }
            is SettingsEvent.MetricChanged -> {
                state = state.copy(metricMeasure = event.metric)
            }
            is SettingsEvent.TemperatureChanged -> {
                state = state.copy(temperatureMeasure = event.temperature)
            }
        }
    }

    sealed class SavingEvent {
        object Success : SavingEvent()
    }

}