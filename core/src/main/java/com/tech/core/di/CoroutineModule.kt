package com.tech.core.di

import com.tech.core.utils.IOCoroutineScope
import com.tech.core.utils.IODispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
internal class CoroutineModule {

    @Provides
    @IOCoroutineScope
    fun provideIOScope(
        @IODispatcher dispatcher: CoroutineContext
    ): CoroutineScope = CoroutineScope(dispatcher)

    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineContext = Dispatchers.IO
}