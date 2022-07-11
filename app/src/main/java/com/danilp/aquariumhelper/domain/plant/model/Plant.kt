package com.danilp.aquariumhelper.domain.plant.model

data class Plant(
    val id: Int,
    val aquariumId: Int,
    val name: String,
    val genus: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val minPh: Double,
    val maxPh: Double,
    val minGh: Double,
    val maxGh: Double,
    val minKh: Double,
    val maxKh: Double,
    val minCO2: Int,
    val minIllumination: Int,
    val description: String
) {
    companion object {
        fun createEmpty(): Plant = Plant(
            0,
            0,
            "",
            "",
            0,
            0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0,
            0,
            ""
        )
    }
}
