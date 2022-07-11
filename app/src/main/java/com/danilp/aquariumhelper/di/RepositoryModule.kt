package com.danilp.aquariumhelper.di

import com.danilp.aquariumhelper.data.aquarium.repository.RoomAquariumRepository
import com.danilp.aquariumhelper.data.dweller.repository.RoomDwellerRepository
import com.danilp.aquariumhelper.data.plant.repository.RoomPlantRepository
import com.danilp.aquariumhelper.domain.aquairum.repository.AquariumRepository
import com.danilp.aquariumhelper.domain.dweller.repository.DwellerRepository
import com.danilp.aquariumhelper.domain.plant.repository.PlantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAquariumRepository(
        roomAquariumRepository: RoomAquariumRepository
    ): AquariumRepository

    @Binds
    @Singleton
    abstract fun bindDwellerRepository(
        roomDwellerRepository: RoomDwellerRepository
    ): DwellerRepository

    @Binds
    @Singleton
    abstract fun bindPlantRepository(
        roomPlantRepository: RoomPlantRepository
    ): PlantRepository
}