package com.danilp.aquariumhelper.data.aquarium.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "aquariums",
    indices = [
        Index("name")
    ]
)
data class AquariumEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val liters: Int,
    val illumination: Int,
    val currentIllumination: Int,
    @ColumnInfo(name = "co2") val currentCO2: Int,
    @ColumnInfo(name = "min_co2") val minCO2: Int,
    val currentTemperature: Int,
    @ColumnInfo(name = "min_temperature") val minTemperature: Int,
    @ColumnInfo(name = "max_temperature") val maxTemperature: Int,
    val currentPh: Double,
    @ColumnInfo(name = "min_ph") val minPh: Double,
    @ColumnInfo(name = "max_ph") val maxPh: Double,
    val currentGh: Double,
    @ColumnInfo(name = "min_gh") val minGh: Double,
    @ColumnInfo(name = "max_gh") val maxGh: Double,
    val currentKh: Double,
    @ColumnInfo(name = "min_kh") val minKh: Double,
    @ColumnInfo(name = "max_kh") val maxKh: Double
)
