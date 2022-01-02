package com.pose.app.db

import androidx.room.*
import com.pose.app.db.entity.CompEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEntity(entity: CompEntity)

    @Delete
    suspend fun deleteEntity(entity: CompEntity)

    @Update
    suspend fun updateEntity(entity: CompEntity)

    @Query("delete from entities")
    suspend fun deleteAllEntities()

    @Query("select * from entities")
    fun getEntities(): Flow<List<CompEntity>>

}