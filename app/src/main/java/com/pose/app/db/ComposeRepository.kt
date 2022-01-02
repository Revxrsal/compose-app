package com.pose.app.db

import com.pose.app.db.entity.CompEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ComposeRepository @Inject constructor(private val dao: CompDao) {

    suspend fun addEntity(server: CompEntity) = dao.addEntity(server)

    suspend fun deleteAllEntities() = dao.deleteAllEntities()

    suspend fun deleteEntity(server: CompEntity) = dao.deleteEntity(server)

    suspend fun updateEntity(server: CompEntity) = dao.updateEntity(server)

    fun getEntities() = dao.getEntities().flowOn(Dispatchers.IO).conflate()

}