package com.danilp.aquariumhelper.data.plant.mapper

import com.danilp.aquariumhelper.data.plant.local.PlantEntity
import com.danilp.aquariumhelper.domain.plant.model.Plant

fun PlantEntity.toPlant() = Plant(
    id = id,
    aquariumId = aquariumId,
    name = name,
    genus = genus,
    minTemperature = minTemperature,
    maxTemperature = maxTemperature,
    minPh = minPh,
    maxPh = maxPh,
    minGh = minGh,
    maxGh = maxGh,
    minKh = minKh,
    maxKh = maxKh,
    minCO2 = minCO2,
    minIllumination = minIllumination,
    description = description
)

fun Plant.toPlantEntity() = PlantEntity(
    id = id,
    aquariumId = aquariumId,
    name = name,
    genus = genus,
    minTemperature = minTemperature,
    maxTemperature = maxTemperature,
    minPh = minPh,
    maxPh = maxPh,
    minGh = minGh,
    maxGh = maxGh,
    minKh = minKh,
    maxKh = maxKh,
    minCO2 = minCO2,
    minIllumination = minIllumination,
    description = description
)