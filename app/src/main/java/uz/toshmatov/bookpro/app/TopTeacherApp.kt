package uz.toshmatov.bookpro.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import uz.toshmatov.bookpro.BuildConfig
import uz.toshmatov.bookpro.core.logger.Logger
import uz.toshmatov.bookpro.di.KoinModules.koinModule

class TopTeacherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setup(BuildConfig.DEBUG)

        startKoin {
            androidContext(this@TopTeacherApp)
            koin.loadModules(
                koinModule
            )
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}