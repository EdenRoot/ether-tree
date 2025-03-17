package dev.kiryao.ethertree.core.domain.model

data class Node(
    val hash: String,
    val name: String,
    val parentHash: String?
)