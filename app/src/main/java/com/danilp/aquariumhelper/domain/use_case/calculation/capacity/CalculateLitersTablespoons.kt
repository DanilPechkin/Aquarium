package com.danilp.aquariumhelper.domain.use_case.calculation.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

/**
 * Using level Tablespoons
 */
@Singleton
class CalculateLitersTablespoons {
    /**
     * @param liters to calculate tablespoons
     * @param tablespoons to calculate liters
     */
    fun execute(liters: Double = 0.0, tablespoons: Double = 0.0): CalculationResult =
        if (tablespoons == 0.0)
            CalculationResult(result = liters * 66.667)
        else
            CalculationResult(result = tablespoons / 66.667)
}