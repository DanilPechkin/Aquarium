package com.danilp.aquariumhelper.domain.aquairum.repository

import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.util.Resource
import kotlinx.coroutines.flow.Flow

interface AquariumRepository {

    suspend fun insert(aquarium: Aquarium)

    suspend fun update(aquarium: Aquarium)

    suspend fun delete(aquarium: Aquarium)

    suspend fun searchAquariums(name: String): Flow<Resource<List<Aquarium>>>

    suspend fun findAquariumById(id: Int): Flow<Resource<Aquarium>>

}