package com.danilp.aquariumhelper.domain.use_case.calculation.temperature

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

/**
 * Celsius also can be used as Centigrade
 */
@Singleton
class CalculateCelsiusFahrenheit {
    /**
     * @param celsius to calculate fahrenheit
     * @param fahrenheit to calculate celsius
     */
    fun execute(celsius: Double = 0.0, fahrenheit: Double = 0.0): CalculationResult =
        if (fahrenheit == 0.0)
            CalculationResult(result = (celsius * 1.8) + 32)
        else
            CalculationResult(result = (fahrenheit - 32) / 1.8)
}