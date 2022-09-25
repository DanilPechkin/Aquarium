package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult

class ConvertLiters {
    /**
     * @param measure required measure
     * @param liters value to convert
     */
    fun to(measure: CapacityMeasure, liters: Double): CalculationResult =
        when (measure) {
            is CapacityMeasure.CubicCentimeters -> toMilliliters(liters = liters)
            is CapacityMeasure.CubicFeet -> toCubicFeet(liters = liters)
            is CapacityMeasure.CubicInches -> toCubicInches(liters = liters)
            is CapacityMeasure.CubicMeters -> toCubicMeters(liters = liters)
            is CapacityMeasure.Gallons -> toGallons(liters = liters)
            is CapacityMeasure.MetricCups -> toMetricCups(liters = liters)
            is CapacityMeasure.Milliliters -> toMilliliters(liters = liters)
            is CapacityMeasure.Tablespoons -> toTablespoons(liters = liters)
            is CapacityMeasure.Teaspoons -> toTeaspoons(liters = liters)
            is CapacityMeasure.USCups -> toUSCups(liters = liters)
            is CapacityMeasure.Liters -> CalculationResult(liters)
        }

    /**
     * @param measure required measure
     * @param value to convert into liters
     */
    fun from(measure: CapacityMeasure, value: Double): CalculationResult =
        when (measure) {
            is CapacityMeasure.CubicCentimeters -> toMilliliters(milliliters = value)
            is CapacityMeasure.CubicFeet -> toCubicFeet(feet = value)
            is CapacityMeasure.CubicInches -> toCubicInches(inches = value)
            is CapacityMeasure.CubicMeters -> toCubicMeters(meters = value)
            is CapacityMeasure.Gallons -> toGallons(gallons = value)
            is CapacityMeasure.MetricCups -> toMetricCups(cups = value)
            is CapacityMeasure.Milliliters -> toMilliliters(milliliters = value)
            is CapacityMeasure.Tablespoons -> toTablespoons(tablespoons = value)
            is CapacityMeasure.Teaspoons -> toTeaspoons(teaspoons = value)
            is CapacityMeasure.USCups -> toUSCups(cups = value)
            is CapacityMeasure.Liters -> CalculationResult(value)
        }

    /**
     * @param liters to calculate feet
     * @param feet to calculate liters
     */
    private fun toCubicFeet(liters: Double = 0.0, feet: Double = 0.0): CalculationResult =
        if (feet == 0.0)
            CalculationResult(result = liters * 0.0353147)
        else
            CalculationResult(result = feet / 0.0353147)

    /**
     * Using level U.S. Cups
     * @param liters to calculate cups
     * @param cups to calculate liters
     */
    private fun toUSCups(liters: Double = 0.0, cups: Double = 0.0): CalculationResult =
        if (cups == 0.0)
            CalculationResult(result = liters * 4.167)
        else
            CalculationResult(result = cups / 4.167)

    /**
     * Using level teaspoons
     * @param liters to calculate teaspoons
     * @param teaspoons to calculate liters
     */
    private fun toTeaspoons(liters: Double = 0.0, teaspoons: Double = 0.0): CalculationResult =
        if (teaspoons == 0.0)
            CalculationResult(result = liters * 200)
        else
            CalculationResult(result = teaspoons / 200)

    /**
     * Using level Tablespoons
     * @param liters to calculate tablespoons
     * @param tablespoons to calculate liters
     */
    private fun toTablespoons(liters: Double = 0.0, tablespoons: Double = 0.0): CalculationResult =
        if (tablespoons == 0.0)
            CalculationResult(result = liters * 66.667)
        else
            CalculationResult(result = tablespoons / 66.667)

    /**
     * Also can be used to calculate cubic centimeters
     * @param liters to calculate milliliters
     * @param milliliters to calculate liters
     */
    private fun toMilliliters(liters: Double = 0.0, milliliters: Double = 0.0): CalculationResult =
        if (milliliters == 0.0)
            CalculationResult(result = liters * 1000)
        else
            CalculationResult(result = milliliters / 1000)

    /**
     * Using level Metric Cups
     * @param liters to calculate cups
     * @param cups to calculate liters
     */
    private fun toMetricCups(liters: Double = 0.0, cups: Double = 0.0): CalculationResult =
        if (cups == 0.0)
            CalculationResult(result = liters * 3.52)
        else
            CalculationResult(result = cups / 3.52)

    /**
     * Using U.S. gallons
     * @param liters to calculate gallons
     * @param gallons to calculate liters
     */
    private fun toGallons(liters: Double = 0.0, gallons: Double = 0.0): CalculationResult =
        if (gallons == 0.0)
            CalculationResult(result = liters * 0.264172)
        else
            CalculationResult(result = gallons / 0.264172)

    /**
     * @param liters to calculate meters
     * @param meters to calculate liters
     */
    private fun toCubicMeters(liters: Double = 0.0, meters: Double = 0.0): CalculationResult =
        if (meters == 0.0)
            CalculationResult(result = liters * 0.001)
        else
            CalculationResult(result = meters / 0.001)

    /**
     * @param liters to calculate inches
     * @param inches to calculate liters
     */
    private fun toCubicInches(liters: Double = 0.0, inches: Double = 0.0): CalculationResult =
        if (inches == 0.0)
            CalculationResult(result = liters * 61.0237)
        else
            CalculationResult(result = inches / 61.0237)
}