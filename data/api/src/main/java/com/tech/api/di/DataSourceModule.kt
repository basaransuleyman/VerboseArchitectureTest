package com.tech.api.di

import com.tech.api.Api
import com.tech.api.datasource.InitialResponseDataSource
import com.tech.api.datasource.InitialResponseDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    internal fun provideInitialResponseDataSource(apiService: Api): InitialResponseDataSource {
        return InitialResponseDataSourceImpl(apiService)
    }

}