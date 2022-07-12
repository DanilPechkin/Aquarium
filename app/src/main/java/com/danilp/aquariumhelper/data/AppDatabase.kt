package com.danilp.aquariumhelper.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danilp.aquariumhelper.data.aquarium.local.AquariumDao
import com.danilp.aquariumhelper.data.aquarium.local.AquariumEntity
import com.danilp.aquariumhelper.data.dweller.local.DwellerDao
import com.danilp.aquariumhelper.data.dweller.local.DwellerEntity
import com.danilp.aquariumhelper.data.plant.local.PlantDao
import com.danilp.aquariumhelper.data.plant.local.PlantEntity

@Database(
    version = 6,
    entities = [
        AquariumEntity::class,
        DwellerEntity::class,
        PlantEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract val aquariumDao: AquariumDao

    abstract val dwellerDao: DwellerDao

    abstract val plantDao: PlantDao

}