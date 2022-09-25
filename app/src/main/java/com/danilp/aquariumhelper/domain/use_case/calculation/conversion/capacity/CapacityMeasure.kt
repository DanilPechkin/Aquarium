package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.capacity

sealed interface CapacityMeasure {
    object CubicFeet : CapacityMeasure
    object USCups : CapacityMeasure
    object Teaspoons : CapacityMeasure
    object Tablespoons : CapacityMeasure
    object Milliliters : CapacityMeasure
    object MetricCups : CapacityMeasure
    object Gallons : CapacityMeasure
    object CubicMeters : CapacityMeasure
    object CubicInches : CapacityMeasure
    object CubicCentimeters : CapacityMeasure
    object Liters : CapacityMeasure
}