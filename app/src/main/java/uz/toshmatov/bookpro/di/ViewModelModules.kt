package uz.toshmatov.bookpro.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uz.toshmatov.bookpro.presentation.screen.auth.signin.LoginViewModel

object ViewModelModules {
    val viewModelModules = module {
        viewModel { LoginViewModel(get(), get()) }
    }
}