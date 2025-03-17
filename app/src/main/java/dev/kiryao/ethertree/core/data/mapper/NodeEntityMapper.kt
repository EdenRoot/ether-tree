package dev.kiryao.ethertree.core.data.mapper

import dev.kiryao.ethertree.core.data.source.local.model.NodeEntity
import dev.kiryao.ethertree.core.domain.model.Node

fun NodeEntity.toNode() = Node(hash, name, parentHash)

fun Node.toNodeEntity() = NodeEntity(hash, name, parentHash)