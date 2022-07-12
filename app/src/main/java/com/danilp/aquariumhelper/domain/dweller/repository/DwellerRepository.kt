package com.danilp.aquariumhelper.domain.dweller.repository

import com.danilp.aquariumhelper.domain.dweller.model.Dweller
import com.danilp.aquariumhelper.util.Resource
import kotlinx.coroutines.flow.Flow

interface DwellerRepository {

    suspend fun insert(dweller: Dweller)

    suspend fun update(dweller: Dweller)

    suspend fun delete(dweller: Dweller)

    suspend fun searchDwellersByAquarium(
        aquariumId: Int,
        query: String
    ): Flow<Resource<List<Dweller>>>

    suspend fun findDwllerById(id: Int): Flow<Resource<Dweller>>

}