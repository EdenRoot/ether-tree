package dev.kiryao.ethertree.home.domain.usecase

import dev.kiryao.ethertree.core.domain.model.Node
import kotlinx.coroutines.flow.Flow

interface GetNodesUseCase {

    operator fun invoke(parentHash: String?): Flow<List<Node>>
}