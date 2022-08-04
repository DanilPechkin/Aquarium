package com.danilp.aquariumhelper.domain.use_case.calculation.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

@Singleton
class CalculateLitersCubicMeters {
    /**
     * @param liters to calculate meters
     * @param meters to calculate liters
     */
    fun execute(liters: Double = 0.0, meters: Double = 0.0): CalculationResult =
        if (meters == 0.0)
            CalculationResult(result = liters * 0.001)
        else
            CalculationResult(result = meters / 0.001)
}