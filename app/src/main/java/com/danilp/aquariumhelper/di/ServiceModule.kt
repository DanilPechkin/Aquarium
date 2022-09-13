package com.danilp.aquariumhelper.di

import com.danilp.aquariumhelper.data.account.AccountServiceImpl
import com.danilp.aquariumhelper.data.analytics.LogServiceImpl
import com.danilp.aquariumhelper.domain.service.AccountService
import com.danilp.aquariumhelper.domain.service.LogService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

}