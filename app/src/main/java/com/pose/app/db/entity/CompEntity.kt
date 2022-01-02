package com.pose.app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "entities")
class CompEntity(
    val name: String
) {

    @PrimaryKey
    var id: UUID = UUID.randomUUID()

}