package com.danilp.aquariumhelper.di

import android.content.Context
import com.danilp.aquariumhelper.domain.use_case.*
import com.danilp.aquariumhelper.domain.use_case.calculation.conversion.alkalinity.ConvertDKH
import com.danilp.aquariumhelper.domain.use_case.calculation.conversion.capacity.*
import com.danilp.aquariumhelper.domain.use_case.calculation.conversion.metric.ConvertMeters
import com.danilp.aquariumhelper.domain.use_case.calculation.conversion.temperature.ConvertCelsius
import com.danilp.aquariumhelper.domain.use_case.calculation.fresh_water.CalculateFreshCO2
import com.danilp.aquariumhelper.domain.use_case.validation.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    // ----------Validation----------
    @Provides
    @Singleton
    fun provideValidate(@ApplicationContext context: Context): Validate =
        Validate(context)


    // ----------Conversion----------

    //Capacity
    @Provides
    @Singleton
    fun provideConvertLiters(): ConvertLiters = ConvertLiters()

    //Temperature
    @Provides
    @Singleton
    fun provideConvertCelsius(): ConvertCelsius = ConvertCelsius()

    //Alkalinity
    @Provides
    @Singleton
    fun provideConvertDKH(): ConvertDKH = ConvertDKH()

    //Metric
    @Provides
    @Singleton
    fun provideConvertMeters(): ConvertMeters = ConvertMeters()


    // ----------Calculation----------

    //---Fresh water---

    @Provides
    @Singleton
    fun provideCalculateFreshCO2(): CalculateFreshCO2 = CalculateFreshCO2()

    // ---Aquarium---

    //Capacity


}