package com.tech.data.di

import com.tech.data.remote.Api
import com.tech.data.remote.datasource.InitialResponseDataSource
import com.tech.data.remote.datasource.InitialResponseDataSourceImpl
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