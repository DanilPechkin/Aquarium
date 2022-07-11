package com.danilp.aquariumhelper.di

import android.content.Context
import com.danilp.aquariumhelper.domain.use_case.ValidateLiters
import com.danilp.aquariumhelper.domain.use_case.ValidateName
import dagger.Binds
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

}