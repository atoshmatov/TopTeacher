package uz.toshmatov.bookpro.di

import org.koin.dsl.module
import uz.toshmatov.bookpro.data.remote.api.AuthRepository
import uz.toshmatov.bookpro.data.remote.api.AuthenticationManager

object NetWorkModule {
    val netWorkModule = module {
        single { AuthenticationManager() }
        single { AuthRepository() }
    }
}