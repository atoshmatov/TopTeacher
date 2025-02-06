package uz.toshmatov.bookpro.di

import org.koin.dsl.module
import uz.toshmatov.bookpro.data.local.datastore.AppDataStore
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.presentation.TopTeacherActivity

object AppModule {
    val appModule = module {
        single { TopTeacherActivity::class.java }
        single { AppDataStore(get()) }
        single { OnboardingUtils(get()) }
    }
}