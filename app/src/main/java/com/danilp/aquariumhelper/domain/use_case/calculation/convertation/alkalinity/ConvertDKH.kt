package com.danilp.aquariumhelper.domain.use_case.calculation.convertation.alkalinity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

@Singleton
class ConvertDKH {
    /**
     * @param dKH to calculate meqL
     * @param meqL to calculate dKH
     */
    fun toMeqL(dKH: Double = 0.0, meqL: Double = 0.0): CalculationResult =
        if (meqL == 0.0)
            CalculationResult(result = dKH * 0.357)
        else
            CalculationResult(result = meqL / 0.357)

    /**
     * @param dKH to calculate ppm
     * @param ppm to calculate dKH
     */
    fun toPpm(dKH: Double = 0.0, ppm: Double = 0.0): CalculationResult =
        if (ppm == 0.0)
            CalculationResult(result = dKH * 17.887)
        else
            CalculationResult(result = ppm / 17.887)
}