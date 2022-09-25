package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.alkalinity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult

class ConvertDKH {
    /**
     * @param measure required measure
     * @param dKH value to convert
     */
    fun to(measure: AlkalinityMeasure, dKH: Double): CalculationResult =
        when (measure) {
            is AlkalinityMeasure.Ppm -> toPpm(dKH = dKH)
            is AlkalinityMeasure.MeqL -> toMeqL(dKH = dKH)
            is AlkalinityMeasure.DKH -> CalculationResult(dKH)
            is AlkalinityMeasure.MgL -> toPpm(dKH = dKH)
        }

    /**
     * @param measure required measure
     * @param value to convert into dKH
     */
    fun from(measure: AlkalinityMeasure, value: Double): CalculationResult =
        when (measure) {
            is AlkalinityMeasure.Ppm -> toPpm(ppm = value)
            is AlkalinityMeasure.MeqL -> toMeqL(meqL = value)
            is AlkalinityMeasure.DKH -> CalculationResult(value)
            is AlkalinityMeasure.MgL -> toPpm(ppm = value)
        }

    /**
     * @param dKH to calculate meqL
     * @param meqL to calculate dKH
     */
    private fun toMeqL(dKH: Double = 0.0, meqL: Double = 0.0): CalculationResult =
        if (meqL == 0.0)
            CalculationResult(result = dKH * 0.357)
        else
            CalculationResult(result = meqL / 0.357)

    /**
     * Ppm also can be used as mg/L
     * @param dKH to calculate ppm
     * @param ppm to calculate dKH
     */
    private fun toPpm(dKH: Double = 0.0, ppm: Double = 0.0): CalculationResult =
        if (ppm == 0.0)
            CalculationResult(result = dKH * 17.887)
        else
            CalculationResult(result = ppm / 17.887)
}