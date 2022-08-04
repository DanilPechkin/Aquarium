package com.danilp.aquariumhelper.domain.use_case.calculation.temperature

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

/**
 * Celsius also can be used as Centigrade
 */
@Singleton
class CalculateCelsiusKelvin {
    /**
     * @param celsius to calculate kelvin
     * @param kelvin to calculate celsius
     */
    fun execute(celsius: Double = 0.0, kelvin: Double = 0.0): CalculationResult =
        if (kelvin == 0.0)
            CalculationResult(result = celsius + 273.15)
        else
            CalculationResult(result = kelvin - 273.15)
}