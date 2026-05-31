package com.dreamrecall.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        DreamEntity::class,
        SleepSessionEntity::class,
        WakeInterruptionEntity::class,
        AIAnalysisEntity::class,
        NotificationHistoryEntity::class,
        UserPreferencesEntity::class,
        PendingAIJobEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class DreamRecallDatabase : RoomDatabase() {
    abstract fun dreamDao(): DreamDao
    abstract fun sleepSessionDao(): SleepSessionDao
    abstract fun wakeInterruptionDao(): WakeInterruptionDao
    abstract fun aiAnalysisDao(): AIAnalysisDao
    abstract fun notificationHistoryDao(): NotificationHistoryDao
    abstract fun userPreferencesDao(): UserPreferencesDao
    abstract fun pendingAIJobDao(): PendingAIJobDao
}
