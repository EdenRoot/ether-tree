package dev.kiryao.ethertree.core.data.source.local.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.kiryao.ethertree.core.data.repository.LocalDataSourceRepositoryImpl
import dev.kiryao.ethertree.core.data.source.local.LocalDataSource
import dev.kiryao.ethertree.core.data.source.local.RoomLocalDataSource
import dev.kiryao.ethertree.core.data.source.local.db.NodeRoomDatabase
import dev.kiryao.ethertree.core.domain.repository.LocalDataSourceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModuleProvider {

    @Provides
    fun provideNodeDao(database: NodeRoomDatabase) = database.nodeDao()

    @Provides
    @Singleton
    fun providesLocalDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NodeRoomDatabase::class.java,
        "node_db"
    ).fallbackToDestructiveMigration().build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModuleBinder {

    @Binds
    abstract fun bindRoomLocalDataSource(
        roomLocalDataSource: RoomLocalDataSource
    ): LocalDataSource

    @Binds
    abstract fun bindDefaultEtherTreeRepository(
        defaultEtherTreeRepository: LocalDataSourceRepositoryImpl
    ): LocalDataSourceRepository
}