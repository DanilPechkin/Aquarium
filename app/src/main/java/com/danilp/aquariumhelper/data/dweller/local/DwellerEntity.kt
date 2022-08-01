package com.danilp.aquariumhelper.data.dweller.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dwellers",
    indices = [
        Index("aquariumId"),
        Index("name")
    ]
)
data class DwellerEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val aquariumId: Int,
    val name: String,
    val genus: String,
    val amount: Int,
    @ColumnInfo(name = "min_temperature") val minTemperature: Double,
    @ColumnInfo(name = "max_temperature") val maxTemperature: Double,
    val liters: Double,
    @ColumnInfo(name = "min_ph") val minPh: Double,
    @ColumnInfo(name = "max_ph") val maxPh: Double,
    @ColumnInfo(name = "min_gh") val minGh: Double,
    @ColumnInfo(name = "max_gh") val maxGh: Double,
    @ColumnInfo(name = "min_kh") val minKh: Double,
    @ColumnInfo(name = "max_kh") val maxKh: Double,
    val description: String
)
