package com.dreamrecall.app.di

import com.dreamrecall.app.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideDreamUseCases(
        getDreams: GetDreamsUseCase,
        getDreamById: GetDreamByIdUseCase,
        saveDream: SaveDreamUseCase,
        deleteDream: DeleteDreamUseCase,
        searchDreams: SearchDreamsUseCase
    ): DreamUseCases = DreamUseCases(
        getDreams = getDreams,
        getDreamById = getDreamById,
        saveDream = saveDream,
        deleteDream = deleteDream,
        searchDreams = searchDreams
    )

    @Provides
    @Singleton
    fun provideSleepUseCases(
        getSessions: GetSleepSessionsUseCase,
        manageSession: ManageSleepSessionUseCase,
        recordInterruption: RecordWakeInterruptionUseCase
    ): SleepUseCases = SleepUseCases(
        getSessions = getSessions,
        manageSession = manageSession,
        recordInterruption = recordInterruption
    )

    @Provides
    @Singleton
    fun provideAIUseCases(
        analyzeDream: AnalyzeDreamUseCase,
        getInsights: GetAIInsightsUseCase
    ): AIUseCases = AIUseCases(
        analyzeDream = analyzeDream,
        getInsights = getInsights
    )

    @Provides
    @Singleton
    fun provideSettingsUseCases(
        getSettings: GetSettingsUseCase,
        updateSettings: UpdateSettingsUseCase
    ): SettingsUseCases = SettingsUseCases(
        getSettings = getSettings,
        updateSettings = updateSettings
    )
}
