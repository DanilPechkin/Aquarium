package com.danilp.aquariumhelper.domain.use_case.calculation.alkalinity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

@Singleton
class CalculateDKHMeqL {
    /**
     * @param dKH to calculate meqL
     * @param meqL to calculate dKH
     */
    fun execute(dKH: Double = 0.0, meqL: Double = 0.0): CalculationResult =
        if (meqL == 0.0)
            CalculationResult(result = dKH * 0.357)
        else
            CalculationResult(result = meqL / 0.357)
}