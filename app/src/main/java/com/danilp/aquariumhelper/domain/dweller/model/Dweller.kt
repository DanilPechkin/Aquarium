package com.danilp.aquariumhelper.domain.dweller.model

data class Dweller(
    var id: Int,
    val aquariumId: Int,
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
        fun createEmpty(aquariumId: Int): Dweller = Dweller(
            0,
            aquariumId,
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

