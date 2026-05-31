package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.SleepSession
import com.dreamrecall.app.domain.model.SleepQuality
import com.dreamrecall.app.domain.model.WakeInterruption
import com.dreamrecall.app.domain.repository.SleepRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSleepSessionsUseCase @Inject constructor(
    private val repository: SleepRepository
) {
    operator fun invoke(): Flow<List<SleepSession>> = repository.getAllSessions()
}

class ManageSleepSessionUseCase @Inject constructor(
    private val repository: SleepRepository
) {
    suspend fun startSession(): Long = repository.startSession()
    suspend fun endSession(id: Long, quality: SleepQuality) = repository.endSession(id, quality)
    suspend fun getActiveSession(): SleepSession? = repository.getActiveSession()
}

class RecordWakeInterruptionUseCase @Inject constructor(
    private val repository: SleepRepository
) {
    suspend operator fun invoke(sessionId: Long, note: String) = repository.recordWakeInterruption(sessionId, note)
}

data class SleepUseCases(
    val getSessions: GetSleepSessionsUseCase,
    val manageSession: ManageSleepSessionUseCase,
    val recordInterruption: RecordWakeInterruptionUseCase
)
