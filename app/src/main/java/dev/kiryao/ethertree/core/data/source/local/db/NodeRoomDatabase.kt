package dev.kiryao.ethertree.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.kiryao.ethertree.core.data.source.local.dao.NodeDao
import dev.kiryao.ethertree.core.data.source.local.model.NodeEntity

@Database(entities = [NodeEntity::class], version = 2)
abstract class NodeRoomDatabase : RoomDatabase() {
    abstract fun nodeDao(): NodeDao
}