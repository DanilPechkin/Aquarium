package com.danilp.aquariumhelper.data.aquarium.repository

import com.danilp.aquariumhelper.data.AppDatabase
import com.danilp.aquariumhelper.data.aquarium.mapper.toAquarium
import com.danilp.aquariumhelper.data.aquarium.mapper.toAquariumEntity
import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.domain.aquairum.repository.AquariumRepository
import com.danilp.aquariumhelper.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomAquariumRepository @Inject constructor(
    private val db: AppDatabase
) : AquariumRepository {

    private val dao = db.aquariumDao

    override suspend fun insert(aquarium: Aquarium) {
        dao.insert(aquarium.toAquariumEntity())
    }

    override suspend fun update(aquarium: Aquarium) {
        dao.update(aquarium.toAquariumEntity())
    }

    override suspend fun delete(aquarium: Aquarium) {
        dao.delete(aquarium.toAquariumEntity())
    }

    override suspend fun searchAquariums(name: String): Flow<Resource<List<Aquarium>>> =
        flow {
            emit(Resource.Loading(true))
            val localAquariums = dao.searchAquariums(name)
            emit(Resource.Success(
                data = localAquariums.map { it.toAquarium() }
            ))

            val isDbEmpty = localAquariums.isEmpty()
            if (!isDbEmpty){
                emit(Resource.Loading(false))
                return@flow
            }
        }

    override suspend fun findAquariumById(id: Int): Flow<Resource<Aquarium>> =
        flow {
            emit(Resource.Loading(true))
            val aquarium = dao.findAquariumById(id)
            emit(Resource.Success(
                data = aquarium?.toAquarium()
            ))
        }
}