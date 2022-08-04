package com.danilp.aquariumhelper.domain.use_case.calculation.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

/**
 * Using level teaspoons
 */
@Singleton
class CalculateLitersTeaspoons {
    /**
     * @param liters to calculate teaspoons
     * @param teaspoons to calculate liters
     */
    fun execute(liters: Double = 0.0, teaspoons: Double = 0.0): CalculationResult =
        if (teaspoons == 0.0)
            CalculationResult(result = liters * 200)
        else
            CalculationResult(result = teaspoons / 200)
}