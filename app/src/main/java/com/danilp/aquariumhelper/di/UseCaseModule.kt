package com.danilp.aquariumhelper.di

import android.content.Context
import com.danilp.aquariumhelper.domain.use_case.*
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

    @Provides
    @Singleton
    fun provideValidateLiters(@ApplicationContext context: Context): ValidateLiters =
        ValidateLiters(context)

    @Provides
    @Singleton
    fun provideValidateName(@ApplicationContext context: Context): ValidateName =
        ValidateName(context)

    @Provides
    @Singleton
    fun provideValidateTemperature(@ApplicationContext context: Context): ValidateTemperature =
        ValidateTemperature(context)

    @Provides
    @Singleton
    fun provideValidatePh(@ApplicationContext context: Context): ValidatePh =
        ValidatePh(context)

    @Provides
    @Singleton
    fun provideValidateGh(@ApplicationContext context: Context): ValidateGh =
        ValidateGh(context)

    @Provides
    @Singleton
    fun provideValidateKh(@ApplicationContext context: Context): ValidateKh =
        ValidateKh(context)

    @Provides
    @Singleton
    fun provideValidateCO2(@ApplicationContext context: Context): ValidateCO2 =
        ValidateCO2(context)

    @Provides
    @Singleton
    fun provideValidateIllumination(@ApplicationContext context: Context): ValidateIllumination =
        ValidateIllumination(context)

    @Provides
    @Singleton
    fun provideValidateAmount(@ApplicationContext context: Context): ValidateAmount =
        ValidateAmount(context)
}