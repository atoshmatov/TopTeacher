package uz.toshmatov.bookpro.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uz.toshmatov.bookpro.presentation.TopTeacherViewModel
import uz.toshmatov.bookpro.presentation.screen.auth.signin.LoginViewModel
import uz.toshmatov.bookpro.presentation.screen.main.MainViewModel
import uz.toshmatov.bookpro.presentation.screen.setting.SettingsViewModel
import uz.toshmatov.bookpro.presentation.screen.setting.feature.language.LanguageViewModel
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.ThemeViewModel

object ViewModelModules {
    val viewModelModules = module {
        viewModel { LoginViewModel(get(), get()) }
        viewModel { ThemeViewModel(get()) }
        viewModel { SettingsViewModel(get(), get()) }
        viewModel { LanguageViewModel() }
        viewModel { TopTeacherViewModel(get()) }
        viewModel { MainViewModel(get()) }
    }
}