package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.metric

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult

class ConvertMeters {
    /**
     * @param measureCode required measure
     * @param meters value to convert
     */
    fun to(measureCode: Int, meters: Double): CalculationResult =
        when (measureCode) {
            MetricMeasureCode.CENTIMETERS -> toCentimeters(meters = meters)
            MetricMeasureCode.FEET -> toFeet(meters = meters)
            MetricMeasureCode.INCHES -> toInches(meters = meters)
            MetricMeasureCode.MILLIMETERS -> toMillimeters(meters = meters)
            MetricMeasureCode.METERS -> CalculationResult(meters)
            else -> CalculationResult(meters)
        }

    /**
     * @param measureCode required measure
     * @param value to convert into meters
     */
    fun from(measureCode: Int, value: Double): CalculationResult =
        when (measureCode) {
            MetricMeasureCode.CENTIMETERS -> toCentimeters(centimeters = value)
            MetricMeasureCode.FEET -> toFeet(feet = value)
            MetricMeasureCode.INCHES -> toInches(inches = value)
            MetricMeasureCode.MILLIMETERS -> toMillimeters(millimeters = value)
            MetricMeasureCode.METERS -> CalculationResult(value)
            else -> CalculationResult(value)
        }

    /**
     * @param meters to calculate centimeters
     * @param centimeters to calculate meters
     */
    private fun toCentimeters(meters: Double = 0.0, centimeters: Double = 0.0): CalculationResult =
        if (centimeters == 0.0)
            CalculationResult(result = meters * 100)
        else
            CalculationResult(result = centimeters / 100)

    /**
     * @param meters to calculate millimeters
     * @param millimeters to calculate meters
     */
    private fun toMillimeters(meters: Double = 0.0, millimeters: Double = 0.0): CalculationResult =
        if (millimeters == 0.0)
            CalculationResult(result = meters * 1000)
        else
            CalculationResult(result = millimeters / 1000)

    /**
     * @param meters to calculate feet
     * @param feet to calculate meters
     */
    private fun toFeet(meters: Double = 0.0, feet: Double = 0.0): CalculationResult =
        if (feet == 0.0)
            CalculationResult(result = meters * 3.28084)
        else
            CalculationResult(result = feet / 3.28084)

    /**
     * @param meters to calculate inches
     * @param inches to calculate meters
     */
    private fun toInches(meters: Double = 0.0, inches: Double = 0.0): CalculationResult =
        if (inches == 0.0)
            CalculationResult(result = meters * 39.37008)
        else
            CalculationResult(result = inches / 39.37008)
}