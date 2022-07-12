package com.danilp.aquariumhelper.di

import android.app.Application
import androidx.room.Room
import com.danilp.aquariumhelper.data.AppDatabase
import com.danilp.aquariumhelper.presentation.screens.in_aquairum.in_aquarium_screen.InAquariumInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "aquarium.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideInAquariumInfo(): InAquariumInfo = InAquariumInfo()

}