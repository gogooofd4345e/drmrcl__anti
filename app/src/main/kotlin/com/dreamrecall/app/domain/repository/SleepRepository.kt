package com.dreamrecall.app.domain.repository

import com.dreamrecall.app.domain.model.SleepSession
import com.dreamrecall.app.domain.model.WakeInterruption
import kotlinx.coroutines.flow.Flow

interface SleepRepository {
    fun getAllSleepSessions(): Flow<List<SleepSession>>
    fun getSleepSessionById(id: String): Flow<SleepSession?>
    suspend fun insertSleepSession(session: SleepSession)
    suspend fun updateSleepSession(session: SleepSession)
    suspend fun deleteSleepSession(session: SleepSession)

    fun getInterruptionsForSession(sessionId: String): Flow<List<WakeInterruption>>
    suspend fun insertInterruption(interruption: WakeInterruption)
    suspend fun deleteInterruption(interruption: WakeInterruption)
}
