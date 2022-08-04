package com.danilp.aquariumhelper.domain.use_case.calculation.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

/**
 * Also can be used to calculate cubic centimeters
 */
@Singleton
class CalculateLitersMilliliters {
    /**
     * @param liters to calculate milliliters
     * @param milliliters to calculate liters
     */
    fun execute(liters: Double = 0.0, milliliters: Double = 0.0): CalculationResult =
        if (milliliters == 0.0)
            CalculationResult(result = liters * 1000)
        else
            CalculationResult(result = milliliters / 1000)
}