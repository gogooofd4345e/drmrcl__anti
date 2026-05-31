package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.SleepSession
import com.dreamrecall.app.domain.repository.SleepRepository
import javax.inject.Inject

class SaveSleepSessionUseCase @Inject constructor(
    private val sleepRepository: SleepRepository
) {
    suspend operator fun invoke(session: SleepSession) {
        if (session.startTimeMillis >= session.endTimeMillis) {
            throw IllegalArgumentException("Sleep start time must be before end time")
        }
        sleepRepository.insertSleepSession(session)
    }
}
