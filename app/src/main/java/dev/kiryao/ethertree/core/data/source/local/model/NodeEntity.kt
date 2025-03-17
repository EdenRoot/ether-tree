package dev.kiryao.ethertree.core.data.source.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "nodes",
    foreignKeys = [ForeignKey(
        entity = NodeEntity::class,
        parentColumns = ["hash"],
        childColumns = ["parentHash"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("hash", unique = true),    // Для быстрого поиска по хэшу
        Index("parentHash")             // Для поиска детей
    ]
)
data class NodeEntity(
    @PrimaryKey
    val hash: String,
    val name: String,
    val parentHash: String?
)