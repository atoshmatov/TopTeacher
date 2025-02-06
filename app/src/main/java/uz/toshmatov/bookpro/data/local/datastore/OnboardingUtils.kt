package uz.toshmatov.bookpro.data.local.datastore

import android.content.Context

const val COMPLETED = "completed"
const val IS_LOGIN = "is_login"

class OnboardingUtils(context: Context) {
    private val pref = context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)

    fun isOnboardingCompleted(): Boolean =
        pref.getBoolean(COMPLETED, false)

    fun setOnboardingCompleted() {
        pref
            .edit()
            .putBoolean(COMPLETED, true)
            .apply()
    }

    fun isLogin(): Boolean =
        pref.getBoolean(IS_LOGIN, false)

    fun setLogin() {
        pref.edit()
            .putBoolean(IS_LOGIN, true)
            .apply()
    }

    fun setLogout() {
        pref.edit()
            .putBoolean(IS_LOGIN, false)
            .apply()
    }

    fun clear() {
        pref.edit().clear().apply()
    }
}