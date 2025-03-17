package dev.kiryao.ethertree.home.domain.usecase.impl

import dev.kiryao.ethertree.core.domain.repository.LocalDataSourceRepository
import dev.kiryao.ethertree.home.domain.usecase.DeleteNodeUseCase
import javax.inject.Inject

class DeleteNodeUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
): DeleteNodeUseCase {

    override suspend operator fun invoke(hash: String) {
        localDataSourceRepository.deleteNode(hash)
    }
}