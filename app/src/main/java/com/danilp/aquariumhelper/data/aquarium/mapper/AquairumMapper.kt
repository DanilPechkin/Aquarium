package com.danilp.aquariumhelper.data.aquarium.mapper

import com.danilp.aquariumhelper.data.aquarium.local.AquariumEntity
import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium

fun AquariumEntity.toAquarium() = Aquarium(
    id = id,
    name = name,
    description = description,
    liters = liters,
    illumination = illumination,
    currentIllumination = currentIllumination,
    currentCO2 = currentCO2,
    minCO2 = minCO2,
    currentTemperature = currentTemperature,
    minTemperature = minTemperature,
    maxTemperature = maxTemperature,
    currentPh = currentPh,
    minPh = minPh,
    maxPh = maxPh,
    currentGh = currentGh,
    minGh = minGh,
    maxGh = maxGh,
    currentKh = currentKh,
    minKh = minKh,
    maxKh = maxKh
)

fun Aquarium.toAquariumEntity() = AquariumEntity(
    id = id,
    name = name,
    description = description,
    liters = liters,
    illumination = illumination,
    currentIllumination = currentIllumination,
    currentCO2 = currentCO2,
    minCO2 = minCO2,
    currentTemperature = currentTemperature,
    minTemperature = minTemperature,
    maxTemperature = maxTemperature,
    currentPh = currentPh,
    minPh = minPh,
    maxPh = maxPh,
    currentGh = currentGh,
    minGh = minGh,
    maxGh = maxGh,
    currentKh = currentKh,
    minKh = minKh,
    maxKh = maxKh
)