package com.danilp.aquariumhelper.data.dweller.repository

import com.danilp.aquariumhelper.data.AppDatabase
import com.danilp.aquariumhelper.data.dweller.mapper.toDweller
import com.danilp.aquariumhelper.data.dweller.mapper.toDwellerEntity
import com.danilp.aquariumhelper.domain.dweller.model.Dweller
import com.danilp.aquariumhelper.domain.dweller.repository.DwellerRepository
import com.danilp.aquariumhelper.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDwellerRepository @Inject constructor(
    private val db: AppDatabase
) : DwellerRepository {

    private val dao = db.dwellerDao

    override suspend fun insert(dweller: Dweller) {
        dao.insert(dweller.toDwellerEntity())
    }

    override suspend fun update(dweller: Dweller) {
        dao.update(dweller.toDwellerEntity())
    }

    override suspend fun delete(dweller: Dweller) {
        dao.delete(dweller.toDwellerEntity())
    }

    override suspend fun searchDwellersByAquarium(
        aquarium: String,
        query: String
    ): Flow<Resource<List<Dweller>>> =
        flow {
            emit(Resource.Loading(true))
            val localDwellers = dao.searchDwellersByAquarium(aquarium, query)
            emit(Resource.Success(
                data = localDwellers.map { it.toDweller() }
            ))
            val isDbEmpty = localDwellers.isEmpty()
            if (!isDbEmpty) {
                emit(Resource.Loading(false))
                return@flow
            }
        }

    override suspend fun findDwllerById(id: Int): Flow<Resource<Dweller>> =
        flow {
            emit(Resource.Loading(true))
            val dweller = dao.findDwellerById(id)
            emit(Resource.Success(
                data = dweller?.toDweller()
            ))
        }
}