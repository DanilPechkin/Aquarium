package com.danilp.aquariumhelper.data.plant.local

import androidx.room.*

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: PlantEntity)

    @Update
    suspend fun update(plant: PlantEntity)

    @Delete
    suspend fun delete(plant: PlantEntity)

    @Query(
        """
        SELECT * 
        FROM plants
        WHERE (LOWER(genus) LIKE '%' || LOWER(:query) || '%' OR
        LOWER(name) LIKE '%' || LOWER(:query) || '%') AND
        aquariumId = :aquariumId
    """
    )
    suspend fun searchPlantsByAquarium(aquariumId: Int, query: String): List<PlantEntity>

    @Query("SELECT * FROM plants WHERE id = :id")
    suspend fun findPlantById(id: Int): PlantEntity?
}