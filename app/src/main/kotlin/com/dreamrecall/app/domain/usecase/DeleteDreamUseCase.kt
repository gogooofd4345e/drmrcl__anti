package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.Dream
import com.dreamrecall.app.domain.repository.DreamRepository
import javax.inject.Inject

class DeleteDreamUseCase @Inject constructor(
    private val dreamRepository: DreamRepository
) {
    suspend operator fun invoke(dream: Dream) {
        dreamRepository.deleteDream(dream)
    }
}
