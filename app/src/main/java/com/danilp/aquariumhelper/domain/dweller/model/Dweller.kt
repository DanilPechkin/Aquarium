package com.danilp.aquariumhelper.domain.dweller.model

data class Dweller(
    var id: Int,
    val aquarium: String,
    val name: String,
    val genus: String,
    val amount: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val liters: Int,
    val minPh: Double,
    val maxPh: Double,
    val minGh: Double,
    val maxGh: Double,
    val minKh: Double,
    val maxKh: Double,
    val description: String
) {
    companion object {
        fun createEmpty(aquarium: String): Dweller = Dweller(
            0,
            aquarium,
            "",
            "",
            1,
            0,
            0,
            0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            ""
        )
    }
}

