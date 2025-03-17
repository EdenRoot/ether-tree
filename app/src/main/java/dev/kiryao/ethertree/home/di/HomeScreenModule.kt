package dev.kiryao.ethertree.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.kiryao.ethertree.home.domain.usecase.AddNodeUseCase
import dev.kiryao.ethertree.home.domain.usecase.DeleteNodeUseCase
import dev.kiryao.ethertree.home.domain.usecase.GetNodesUseCase
import dev.kiryao.ethertree.home.domain.usecase.impl.AddNodeUseCaseImpl
import dev.kiryao.ethertree.home.domain.usecase.impl.DeleteNodeUseCaseImpl
import dev.kiryao.ethertree.home.domain.usecase.impl.GetNodesUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeScreenModule {

    @Binds
    abstract fun bindGetNodesUseCase(
        getNodesUseCaseImpl: GetNodesUseCaseImpl
    ): GetNodesUseCase

    @Binds
    abstract fun bindAddNodeUseCase(
        addNodeUseCaseImpl: AddNodeUseCaseImpl
    ): AddNodeUseCase

    @Binds
    abstract fun bindDeleteNodeUseCase(
        deleteNodeUseCaseImpl: DeleteNodeUseCaseImpl
    ): DeleteNodeUseCase
}