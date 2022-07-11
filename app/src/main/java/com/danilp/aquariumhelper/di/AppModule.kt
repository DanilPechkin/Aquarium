package com.danilp.aquariumhelper.di

import android.app.Application
import androidx.room.Room
import com.danilp.aquariumhelper.data.AppDatabase
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

}