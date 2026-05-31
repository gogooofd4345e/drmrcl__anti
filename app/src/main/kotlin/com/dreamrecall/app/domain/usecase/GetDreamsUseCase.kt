package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.Dream
import com.dreamrecall.app.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDreamsUseCase @Inject constructor(
    private val dreamRepository: DreamRepository
) {
    operator fun invoke(): Flow<List<Dream>> {
        return dreamRepository.getAllDreams()
    }
}
