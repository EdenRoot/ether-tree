package dev.kiryao.ethertree.home.domain.usecase.impl

import dev.kiryao.ethertree.core.domain.repository.LocalDataSourceRepository
import dev.kiryao.ethertree.home.domain.usecase.GetNodesUseCase
import javax.inject.Inject

class GetNodesUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
): GetNodesUseCase {

    override operator fun invoke(parentHash: String?) =
        localDataSourceRepository.getNodes(parentHash)
}