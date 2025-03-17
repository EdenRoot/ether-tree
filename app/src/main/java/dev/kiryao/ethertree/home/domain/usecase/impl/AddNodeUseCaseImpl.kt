package dev.kiryao.ethertree.home.domain.usecase.impl

import dev.kiryao.ethertree.core.domain.model.Node
import dev.kiryao.ethertree.core.domain.repository.LocalDataSourceRepository
import dev.kiryao.ethertree.home.domain.usecase.AddNodeUseCase
import javax.inject.Inject

class AddNodeUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
): AddNodeUseCase {

    override suspend operator fun invoke(node: Node) {
        localDataSourceRepository.addNode(node)
    }
}