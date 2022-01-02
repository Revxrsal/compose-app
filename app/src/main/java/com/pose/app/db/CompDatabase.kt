package com.pose.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pose.app.db.entity.CompEntity

@Database(
    entities = [CompEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CompDatabase : RoomDatabase() {

    abstract fun dao(): CompDao

}