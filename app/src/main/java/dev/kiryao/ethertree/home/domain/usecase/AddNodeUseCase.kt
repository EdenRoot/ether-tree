package dev.kiryao.ethertree.home.domain.usecase

import dev.kiryao.ethertree.core.domain.model.Node

interface AddNodeUseCase {

    suspend operator fun invoke(node: Node)
}