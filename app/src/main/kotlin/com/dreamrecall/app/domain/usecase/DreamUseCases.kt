package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.Dream
import com.dreamrecall.app.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDreamsUseCase @Inject constructor(
    private val repository: DreamRepository
) {
    operator fun invoke(): Flow<List<Dream>> = repository.getAllDreams()
}

class GetDreamByIdUseCase @Inject constructor(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(id: Long): Dream? = repository.getDreamById(id)
}

class SaveDreamUseCase @Inject constructor(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(dream: Dream): Long = repository.saveDream(dream)
}

class DeleteDreamUseCase @Inject constructor(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteDream(id)
}

class SearchDreamsUseCase @Inject constructor(
    private val repository: DreamRepository
) {
    operator fun invoke(query: String): Flow<List<Dream>> = repository.searchDreams(query)
}

data class DreamUseCases(
    val getDreams: GetDreamsUseCase,
    val getDreamById: GetDreamByIdUseCase,
    val saveDream: SaveDreamUseCase,
    val deleteDream: DeleteDreamUseCase,
    val searchDreams: SearchDreamsUseCase
)
