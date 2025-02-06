package uz.toshmatov.bookpro.di

import uz.toshmatov.bookpro.di.AppModule.appModule
import uz.toshmatov.bookpro.di.NetWorkModule.netWorkModule
import uz.toshmatov.bookpro.di.RepositoryModule.dataStoreModule
import uz.toshmatov.bookpro.di.ViewModelModules.viewModelModules

object KoinModules {
    val koinModule = listOf(
        appModule,
        netWorkModule,
        dataStoreModule,
        viewModelModules
    )
}