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
    val liters: Double,
    val minIllumination: Double,
    val currentIllumination: Double,
    @ColumnInfo(name = "co2") val currentCO2: Double,
    @ColumnInfo(name = "min_co2") val minCO2: Double,
    val currentTemperature: Double,
    @ColumnInfo(name = "min_temperature") val minTemperature: Double,
    @ColumnInfo(name = "max_temperature") val maxTemperature: Double,
    val currentPh: Double,
    @ColumnInfo(name = "min_ph") val minPh: Double,
    @ColumnInfo(name = "max_ph") val maxPh: Double,
    val currentGh: Double,
    @ColumnInfo(name = "min_gh") val minGh: Double,
    @ColumnInfo(name = "max_gh") val maxGh: Double,
    val currentKh: Double,
    @ColumnInfo(name = "min_kh") val minKh: Double,
    @ColumnInfo(name = "max_kh") val maxKh: Double,
    val currentK: Double,
    @ColumnInfo(name = "min_k") val minK: Double,
    @ColumnInfo(name = "max_k") val maxK: Double,
    val currentNO3: Double,
    @ColumnInfo(name = "min_no3") val minNO3: Double,
    @ColumnInfo(name = "max_no3") val maxNO3: Double,
    val currentFe: Double,
    @ColumnInfo(name = "min_fe") val minFe: Double,
    @ColumnInfo(name = "max_fe") val maxFe: Double,
    val currentCa: Double,
    @ColumnInfo(name = "min_ca") val minCa: Double,
    @ColumnInfo(name = "max_ca") val maxCa: Double,
    val currentMg: Double,
    @ColumnInfo(name = "min_mg") val minMg: Double,
    @ColumnInfo(name = "max_mg") val maxMg: Double,
    val currentPO4: Double,
    @ColumnInfo(name = "min_po4") val minPO4: Double,
    @ColumnInfo(name = "max_po4") val maxPO4: Double
)
