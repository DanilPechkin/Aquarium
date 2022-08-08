package com.danilp.aquariumhelper.data.dweller.mapper

import com.danilp.aquariumhelper.data.dweller.local.DwellerEntity
import com.danilp.aquariumhelper.domain.dweller.model.Dweller

fun DwellerEntity.toDweller() = Dweller(
    id = id,
    aquariumId = aquariumId,
    imageUri = imageUri,
    name = name,
    genus = genus,
    amount = amount,
    minTemperature = minTemperature,
    maxTemperature = maxTemperature,
    liters = liters,
    minPh = minPh,
    maxPh = maxPh,
    minGh = minGh,
    maxGh = maxGh,
    minKh = minKh,
    maxKh = maxKh,
    description = description
)

fun Dweller.toDwellerEntity() = DwellerEntity(
    id = id,
    aquariumId = aquariumId,
    imageUri = imageUri,
    name = name,
    genus = genus,
    amount = amount,
    minTemperature = minTemperature,
    maxTemperature = maxTemperature,
    liters = liters,
    minPh = minPh,
    maxPh = maxPh,
    minGh = minGh,
    maxGh = maxGh,
    minKh = minKh,
    maxKh = maxKh,
    description = description
)