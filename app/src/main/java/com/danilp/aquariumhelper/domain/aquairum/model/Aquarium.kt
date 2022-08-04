package com.danilp.aquariumhelper.domain.aquairum.model

data class Aquarium(
    val id: Int,
    val name: String,
    val description: String,
    val liters: Double,
    val minIllumination: Double, // ватт/л
    val currentIllumination: Double, // ватт/л
    val currentCO2: Double,          // мг/л
    val minCO2: Double,       // мг/л
    val currentTemperature: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val currentPh: Double,
    val minPh: Double,
    val maxPh: Double,
    val currentGh: Double,
    val minGh: Double,
    val maxGh: Double,
    val currentKh: Double,
    val minKh: Double,
    val maxKh: Double,
    val currentK: Double,
    val minK: Double,
    val maxK: Double,
    val currentNO3: Double,
    val minNO3: Double,
    val maxNO3: Double,
    val currentFe: Double,
    val minFe: Double,
    val maxFe: Double,
    val currentCa: Double,
    val minCa: Double,
    val maxCa: Double,
    val currentMg: Double,
    val minMg: Double,
    val maxMg: Double,
    val currentPO4: Double,
    val minPO4: Double,
    val maxPO4: Double,
    val currentAmmonia: Double,
    val minAmmonia: Double,
    val maxAmmonia: Double
) {
    companion object {
        fun createEmpty(): Aquarium = Aquarium(
            0,
            "",
            "",
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
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
