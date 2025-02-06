package uz.toshmatov.bookpro.core.logger

import timber.log.Timber

object Logger {
    fun setup(isDebug: Boolean) {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
