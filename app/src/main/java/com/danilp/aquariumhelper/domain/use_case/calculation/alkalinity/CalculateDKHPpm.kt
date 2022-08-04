package com.danilp.aquariumhelper.domain.use_case.calculation.alkalinity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

@Singleton
class CalculateDKHPpm {
    /**
     * @param dKH to calculate ppm
     * @param ppm to calculate dKH
     */
    fun execute(dKH: Double = 0.0, ppm: Double = 0.0): CalculationResult =
        if (ppm == 0.0)
            CalculationResult(result = dKH * 17.887)
        else
            CalculationResult(result = ppm / 17.887)
}