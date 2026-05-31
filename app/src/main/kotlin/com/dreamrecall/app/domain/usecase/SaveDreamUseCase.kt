package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.Dream
import com.dreamrecall.app.domain.repository.DreamRepository
import javax.inject.Inject

class SaveDreamUseCase @Inject constructor(
    private val dreamRepository: DreamRepository
) {
    suspend operator fun invoke(dream: Dream) {
        if (dream.title.isBlank()) {
            throw IllegalArgumentException("Dream title cannot be empty")
        }
        dreamRepository.insertDream(dream)
    }
}
