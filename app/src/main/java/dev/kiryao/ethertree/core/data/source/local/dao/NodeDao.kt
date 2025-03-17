package dev.kiryao.ethertree.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kiryao.ethertree.core.data.source.local.model.NodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(node: NodeEntity)

    @Query("DELETE FROM nodes WHERE hash = :hash")
    suspend fun delete(hash: String)

    @Query("SELECT * FROM nodes WHERE parentHash IS :parentHash")
    fun getNodes(parentHash: String?): Flow<List<NodeEntity>>

    @Query("SELECT * FROM nodes WHERE hash = :hash")
    fun getNodeByHash(hash: String): Flow<NodeEntity>
}