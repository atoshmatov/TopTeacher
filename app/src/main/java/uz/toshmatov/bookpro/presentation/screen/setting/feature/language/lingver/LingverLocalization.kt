package uz.toshmatov.bookpro.presentation.screen.setting.feature.language.lingver

import android.content.Context
import com.yariksoffice.lingver.Lingver

class LingverLocalization {
    fun setLocale(
        context: Context,
        languageCode: String,
    ) {
        Lingver.getInstance().setLocale(context, languageCode)
    }
}
