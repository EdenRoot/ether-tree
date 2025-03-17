package dev.kiryao.ethertree.core.data.source.local

import dev.kiryao.ethertree.core.data.mapper.toNode
import dev.kiryao.ethertree.core.data.mapper.toNodeEntity
import dev.kiryao.ethertree.core.data.source.local.dao.NodeDao
import dev.kiryao.ethertree.core.domain.model.Node
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(
    private val nodeDao: NodeDao
) : LocalDataSource {

    override fun getNodes(parentHash: String?): Flow<List<Node>> {
        return nodeDao.getNodes(parentHash).map { nodeEntityList ->
            nodeEntityList.map { nodeEntity -> nodeEntity.toNode() }
        }
    }

    override fun getNodeByHash(hash: String): Flow<Node> {
        return nodeDao.getNodeByHash(hash).map { nodeEntity ->
            nodeEntity.toNode()
        }
    }

    override suspend fun addNode(node: Node) {
        nodeDao.insert(node.toNodeEntity())
    }

    override suspend fun deleteNode(hash: String) {
        nodeDao.delete(hash)
    }
}