package com.danilp.aquariumhelper.domain.use_case.calculation.aquairum.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Takes centimeters, returns milliliters
 */
@Singleton
class CalculateCapacity {

    fun rectangle(length: Double, width: Double, height: Double): CalculationResult =
        CalculationResult(length * width * height)

    fun cylinder(height: Double, diameter: Double): CalculationResult =
        CalculationResult(PI * height * (diameter / 2).pow(2))

    fun halfCylinder(height: Double, diameter: Double): CalculationResult =
        CalculationResult(0.5 * PI * height * (diameter / 2).pow(2))

    fun bowfront(
        height: Double,
        width: Double,
        fullWidth: Double,
        length: Double
    ): CalculationResult =
        if (fullWidth < width)
            CalculationResult(
                successful = false,
                errorMessage = ""
            )
        else
            CalculationResult(
                (height * width * length) +
                        (length / 2) * (fullWidth - width) * PI * height / 2
            )

    fun cornerBowfront(height: Double, width: Double, length: Double): CalculationResult =
        CalculationResult(length * width * height * PI / 4)

    fun lShape(
        height: Double,
        fullWidth: Double,
        width: Double,
        fullLength: Double,
        length: Double
    ): CalculationResult =
        CalculationResult((fullWidth * fullLength * height) - width * length * height)

    fun ellipticalCylinder(height: Double, width: Double, length: Double): CalculationResult =
        CalculationResult(height * (width / 2) * (length / 2) * PI)

    fun triangle(height: Double, side1: Double, side2: Double, side3: Double): CalculationResult {
        val p = side1 + side2 + side3
        val s = sqrt(p * (p - side1) * (p - side2) * (p - side3))
        return CalculationResult(height * s)
    }

    fun trapezoid(
        height: Double,
        width: Double,
        fullWidth: Double,
        length: Double
    ): CalculationResult =
        CalculationResult((0.5 * (width + fullWidth) * length) * height)

    fun flatBackHex(
        height: Double,
        length: Double,
        fullLength: Double,
        width: Double,
        fullWidth: Double
    ): CalculationResult =
        CalculationResult(
            (height * length * width) +
                    ((fullWidth - width) * 0.5 * (fullLength + length) * height)
        )
}