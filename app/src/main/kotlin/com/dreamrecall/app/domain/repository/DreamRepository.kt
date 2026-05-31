package com.dreamrecall.app.domain.repository

import com.dreamrecall.app.domain.model.Dream
import kotlinx.coroutines.flow.Flow

interface DreamRepository {
    fun getAllDreams(): Flow<List<Dream>>
    fun getDreamById(id: String): Flow<Dream?>
    suspend fun insertDream(dream: Dream)
    suspend fun updateDream(dream: Dream)
    suspend fun deleteDream(dream: Dream)
    suspend fun searchDreams(query: String): List<Dream>
}
