package com.danilp.aquariumhelper.domain.aquairum.model

data class Aquarium(
    val id: Int,
    val name: String,
    val description: String,
    val liters: Int,
    val illumination: Int, // ватт/л
    val currentIllumination: Int, // ватт/л
    val currentCO2: Int,          // мг/л
    val minCO2: Int,       // мг/л
    val currentTemperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val currentPh: Double,
    val minPh: Double,
    val maxPh: Double,
    val currentGh: Double,
    val minGh: Double,
    val maxGh: Double,
    val currentKh: Double,
    val minKh: Double,
    val maxKh: Double
) {
    companion object {
        fun createEmpty(): Aquarium = Aquarium(
            0,
            "",
            "",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0
        )
    }
}
