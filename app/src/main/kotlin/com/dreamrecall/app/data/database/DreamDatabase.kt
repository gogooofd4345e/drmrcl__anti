package com.dreamrecall.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dreamrecall.app.data.database.dao.*
import com.dreamrecall.app.data.database.entities.*

@Database(
    entities = [
        DreamEntity::class,
        SleepSessionEntity::class,
        WakeInterruptionEntity::class,
        AIAnalysisEntity::class,
        PendingAIJobEntity::class,
        NotificationHistoryEntity::class,
        ReminderSettingsEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class DreamDatabase : RoomDatabase() {
    abstract fun dreamDao(): DreamDao
    abstract fun sleepSessionDao(): SleepSessionDao
    abstract fun wakeInterruptionDao(): WakeInterruptionDao
    abstract fun aiAnalysisDao(): AIAnalysisDao
    abstract fun pendingAIJobDao(): PendingAIJobDao
    abstract fun notificationHistoryDao(): NotificationHistoryDao
    abstract fun reminderSettingsDao(): ReminderSettingsDao

    companion object {
        const val DATABASE_NAME = "dream_recall_db"
    }
}
