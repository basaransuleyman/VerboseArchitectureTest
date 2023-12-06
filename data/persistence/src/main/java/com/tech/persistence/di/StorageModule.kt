package com.tech.persistence.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.tech.persistence.storage.ClientStorage
import com.tech.persistence.storage.ClientStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("SatelliteDetail", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideClientStorage(
        @ApplicationContext context: Context,
        gson: Gson,
        sharedPreferences: SharedPreferences
    ): ClientStorage {
        return ClientStorageImpl(context, gson, sharedPreferences)
    }
}