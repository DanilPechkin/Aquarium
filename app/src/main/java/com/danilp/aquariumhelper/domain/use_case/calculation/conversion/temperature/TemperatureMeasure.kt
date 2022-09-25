package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.temperature

sealed interface TemperatureMeasure {
    object Fahrenheit : TemperatureMeasure
    object Kelvin : TemperatureMeasure
    object Celsius : TemperatureMeasure
}