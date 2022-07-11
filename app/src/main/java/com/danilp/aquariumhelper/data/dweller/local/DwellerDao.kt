package com.danilp.aquariumhelper.data.dweller.local

import androidx.room.*

@Dao
interface DwellerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dweller: DwellerEntity)

    @Update
    suspend fun update(dweller: DwellerEntity)

    @Delete
    suspend fun delete(dweller: DwellerEntity)

    @Query(
        """
        SELECT * 
        FROM dwellers
        WHERE (LOWER(genus) LIKE '%' || LOWER(:query) || '%' OR
        LOWER(name) LIKE '%' || LOWER(:query) || '%') AND
        aquarium = :aquarium
    """
    )
    suspend fun searchDwellersByAquarium(aquarium: String, query: String): List<DwellerEntity>

    @Query("SELECT * FROM dwellers WHERE id = :id")
    suspend fun findDwellerById(id: Int): DwellerEntity?
}