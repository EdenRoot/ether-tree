package dev.kiryao.ethertree.core.data.repository

import dev.kiryao.ethertree.core.data.source.local.LocalDataSource
import dev.kiryao.ethertree.core.domain.model.Node
import dev.kiryao.ethertree.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
): LocalDataSourceRepository {

    override fun getNodes(parentHash: String?): Flow<List<Node>> =
        localDataSource.getNodes(parentHash)

    override fun getNodeByHash(hash: String): Flow<Node> =
        localDataSource.getNodeByHash(hash)

    override suspend fun addNode(node: Node) =
        localDataSource.addNode(node)

    override suspend fun deleteNode(hash: String) =
        localDataSource.deleteNode(hash)
}