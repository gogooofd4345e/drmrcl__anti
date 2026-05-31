package com.dreamrecall.app.di

import com.dreamrecall.app.core.network.GeminiApiClient
import com.dreamrecall.app.core.network.GeminiService
import com.dreamrecall.app.data.datastore.SecureStorage
import com.dreamrecall.app.data.datastore.UserPreferencesDataStore
import com.dreamrecall.app.data.repository.DreamRepositoryImpl
import com.dreamrecall.app.data.repository.SleepRepositoryImpl
import com.dreamrecall.app.data.repository.AIRepositoryImpl
import com.dreamrecall.app.data.repository.SettingsRepositoryImpl
import com.dreamrecall.app.domain.repository.DreamRepository
import com.dreamrecall.app.domain.repository.SleepRepository
import com.dreamrecall.app.domain.repository.AIRepository
import com.dreamrecall.app.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDreamRepository(impl: DreamRepositoryImpl): DreamRepository

    @Binds
    @Singleton
    abstract fun bindSleepRepository(impl: SleepRepositoryImpl): SleepRepository

    @Binds
    @Singleton
    abstract fun bindAIRepository(impl: AIRepositoryImpl): AIRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}
