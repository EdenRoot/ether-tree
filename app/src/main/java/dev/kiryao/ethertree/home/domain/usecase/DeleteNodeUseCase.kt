package dev.kiryao.ethertree.home.domain.usecase

interface DeleteNodeUseCase {

    suspend operator fun invoke(hash: String)
}