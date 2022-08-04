package com.danilp.aquariumhelper.domain.use_case.calculation.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

/**
 * Using U.S. gallons
 */
@Singleton
class CalculateLitersGallons {
    /**
     * @param liters to calculate gallons
     * @param gallons to calculate liters
     */
    fun execute(liters: Double = 0.0, gallons: Double = 0.0): CalculationResult =
        if (gallons == 0.0)
            CalculationResult(result = liters * 0.264172)
        else
            CalculationResult(result = gallons / 0.264172)
}