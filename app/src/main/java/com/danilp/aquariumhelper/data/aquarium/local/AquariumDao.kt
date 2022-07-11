package com.danilp.aquariumhelper.data.aquarium.local

import androidx.room.*

@Dao
interface AquariumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(aquariumEntity: AquariumEntity)

    @Update
    suspend fun update(aquariumEntity: AquariumEntity)

    @Delete
    suspend fun delete(aquariumEntity: AquariumEntity)

    @Query(
        """
            SELECT * 
            FROM aquariums
            WHERE LOWER(name) LIKE '%' || LOWER(:name) || '%'
        """
    )
    suspend fun searchAquariums(name: String): List<AquariumEntity>

    @Query("SELECT * FROM aquariums WHERE id = :id")
    suspend fun findAquariumById(id: Int): AquariumEntity?

}