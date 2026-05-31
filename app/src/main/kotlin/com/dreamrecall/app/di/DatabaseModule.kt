package com.dreamrecall.app.di

import android.content.Context
import androidx.room.Room
import com.dreamrecall.app.data.database.DreamDatabase
import com.dreamrecall.app.data.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDreamDatabase(@ApplicationContext context: Context): DreamDatabase =
        Room.databaseBuilder(
            context,
            DreamDatabase::class.java,
            DreamDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDreamDao(db: DreamDatabase): DreamDao = db.dreamDao()

    @Provides
    fun provideSleepSessionDao(db: DreamDatabase): SleepSessionDao = db.sleepSessionDao()

    @Provides
    fun provideWakeInterruptionDao(db: DreamDatabase): WakeInterruptionDao = db.wakeInterruptionDao()

    @Provides
    fun provideAIAnalysisDao(db: DreamDatabase): AIAnalysisDao = db.aiAnalysisDao()

    @Provides
    fun providePendingAIJobDao(db: DreamDatabase): PendingAIJobDao = db.pendingAIJobDao()

    @Provides
    fun provideNotificationHistoryDao(db: DreamDatabase): NotificationHistoryDao = db.notificationHistoryDao()

    @Provides
    fun provideReminderSettingsDao(db: DreamDatabase): ReminderSettingsDao = db.reminderSettingsDao()
}
