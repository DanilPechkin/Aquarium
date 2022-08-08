package com.danilp.aquariumhelper.domain.dweller.model

data class Dweller(
    var id: Int,
    val aquariumId: Int,
    val imageUri: String,
    val name: String,
    val genus: String,
    val amount: Int,
    val minTemperature: Double,
    val maxTemperature: Double,
    val liters: Double,
    val minPh: Double,
    val maxPh: Double,
    val minGh: Double,
    val maxGh: Double,
    val minKh: Double,
    val maxKh: Double,
    val description: String
) {
    companion object {
        fun createEmpty(): Dweller = Dweller(
            0,
            0,
            "",
            "",
            "",
            1,
            0.0,
            0.0,
            0.0,
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

