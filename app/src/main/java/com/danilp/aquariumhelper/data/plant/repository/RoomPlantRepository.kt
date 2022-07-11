package com.danilp.aquariumhelper.data.plant.repository

import com.danilp.aquariumhelper.data.AppDatabase
import com.danilp.aquariumhelper.data.plant.mapper.toPlant
import com.danilp.aquariumhelper.data.plant.mapper.toPlantEntity
import com.danilp.aquariumhelper.domain.plant.model.Plant
import com.danilp.aquariumhelper.domain.plant.repository.PlantRepository
import com.danilp.aquariumhelper.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomPlantRepository @Inject constructor(
    private val db: AppDatabase
) : PlantRepository{

    private val dao = db.plantDao

    override suspend fun insert(plant: Plant) {
        dao.insert(plant.toPlantEntity())
    }

    override suspend fun update(plant: Plant) {
        dao.update(plant.toPlantEntity())
    }

    override suspend fun delete(plant: Plant) {
        dao.delete(plant.toPlantEntity())
    }

    override suspend fun searchPlantsByAquarium(
        aquariumId: Int,
        query: String
    ): Flow<Resource<List<Plant>>> =
        flow {
            emit(Resource.Loading(true))
            val localPlants = dao.searchPlantsByAquarium(aquariumId, query)
            emit(Resource.Success(
                data = localPlants.map { it.toPlant() }
            ))

            val isDbEmpty = localPlants.isEmpty()
            if (!isDbEmpty) {
                emit(Resource.Loading(false))
                return@flow
            }
        }

    override suspend fun findPlantById(id: Int): Flow<Resource<Plant>> =
        flow {
            emit(Resource.Loading(true))
            val plant = dao.findPlantById(id)
            emit(Resource.Success(
                data = plant?.toPlant()
            ))
        }
}