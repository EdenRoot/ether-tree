package dev.kiryao.ethertree.core.domain.repository

import dev.kiryao.ethertree.core.domain.model.Node
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceRepository {

    fun getNodes(parentHash: String?): Flow<List<Node>>

    fun getNodeByHash(hash: String): Flow<Node>

    suspend fun addNode(node: Node)

    suspend fun deleteNode(hash: String)
}