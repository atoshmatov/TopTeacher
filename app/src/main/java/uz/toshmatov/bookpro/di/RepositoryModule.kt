package uz.toshmatov.bookpro.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uz.toshmatov.bookpro.data.local.repository.DataStoreRepositoryImpl
import uz.toshmatov.bookpro.data.remote.api.TeacherRepositoryImpl
import uz.toshmatov.bookpro.domain.repository.DataStoreRepository
import uz.toshmatov.bookpro.domain.repository.TeacherRepository

object RepositoryModule {
    val dataStoreModule = module {
        singleOf(::DataStoreRepositoryImpl) { bind<DataStoreRepository>() }
        singleOf(::TeacherRepositoryImpl) { bind<TeacherRepository>() }
    }
}