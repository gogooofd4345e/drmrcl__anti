package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.SleepSession
import com.dreamrecall.app.domain.repository.SleepRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSleepSessionsUseCase @Inject constructor(
    private val sleepRepository: SleepRepository
) {
    operator fun invoke(): Flow<List<SleepSession>> {
        return sleepRepository.getAllSleepSessions()
    }
}
