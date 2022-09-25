package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.temperature

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult

/**
 * Celsius also can be used as Centigrade
 */
class ConvertCelsius {
    /**
     * @param measureCode required measure
     * @param celsius value to convert
     */
    fun to(measureCode: Int, celsius: Double): CalculationResult =
        when (measureCode) {
            TemperatureMeasureCode.KELVIN -> toKelvin(celsius = celsius)
            TemperatureMeasureCode.FAHRENHEIT -> toFahrenheit(celsius = celsius)
            TemperatureMeasureCode.CELSIUS -> CalculationResult(celsius)
            else -> CalculationResult(celsius)
        }

    /**
     * @param measureCode required measure
     * @param value to convert into celsius
     */
    fun from(measureCode: Int, value: Double): CalculationResult =
        when (measureCode) {
            TemperatureMeasureCode.KELVIN -> toKelvin(kelvin = value)
            TemperatureMeasureCode.FAHRENHEIT -> toFahrenheit(fahrenheit = value)
            TemperatureMeasureCode.CELSIUS -> CalculationResult(value)
            else -> CalculationResult(value)
        }

    /**
     * @param celsius to calculate fahrenheit
     * @param fahrenheit to calculate celsius
     */
    private fun toFahrenheit(celsius: Double = 0.0, fahrenheit: Double = 0.0): CalculationResult =
        if (fahrenheit == 0.0)
            CalculationResult(result = (celsius * 1.8) + 32)
        else
            CalculationResult(result = (fahrenheit - 32) / 1.8)

    /**
     * @param celsius to calculate kelvin
     * @param kelvin to calculate celsius
     */
    private fun toKelvin(celsius: Double = 0.0, kelvin: Double = 0.0): CalculationResult =
        if (kelvin == 0.0)
            CalculationResult(result = celsius + 273.15)
        else
            CalculationResult(result = kelvin - 273.15)
}