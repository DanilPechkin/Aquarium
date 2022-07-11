package com.danilp.aquariumhelper.domain.plant.repository

import com.danilp.aquariumhelper.domain.plant.model.Plant
import com.danilp.aquariumhelper.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    suspend fun insert(plant: Plant)

    suspend fun update(plant: Plant)

    suspend fun delete(plant: Plant)

    suspend fun searchPlantsByAquarium(
        aquariumId: Int,
        query: String
    ): Flow<Resource<List<Plant>>>

    suspend fun findPlantById(id: Int): Flow<Resource<Plant>>

}