package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.UserPreferences
import com.dreamrecall.app.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    operator fun invoke(): Flow<UserPreferences> {
        return userPreferencesRepository.getUserPreferences()
    }
}
