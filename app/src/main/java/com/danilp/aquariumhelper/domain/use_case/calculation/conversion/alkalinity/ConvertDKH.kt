package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.alkalinity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult

class ConvertDKH {
    /**
     * @param measureCode required measure
     * @param dKH value to convert
     */
    fun to(measureCode: Int, dKH: Double): CalculationResult =
        when (measureCode) {
            AlkalinityMeasureCode.PPM -> toPpm(dKH = dKH)
            AlkalinityMeasureCode.MEQL -> toMeqL(dKH = dKH)
            AlkalinityMeasureCode.DKH -> CalculationResult(dKH)
            AlkalinityMeasureCode.MGL -> toPpm(dKH = dKH)
            else -> CalculationResult(dKH)
        }

    /**
     * @param measureCode required measure
     * @param value to convert into dKH
     */
    fun from(measureCode: Int, value: Double): CalculationResult =
        when (measureCode) {
            AlkalinityMeasureCode.PPM -> toPpm(ppm = value)
            AlkalinityMeasureCode.MEQL -> toMeqL(meqL = value)
            AlkalinityMeasureCode.DKH -> CalculationResult(value)
            AlkalinityMeasureCode.MGL -> toPpm(ppm = value)
            else -> CalculationResult(value)
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