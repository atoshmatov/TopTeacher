package uz.toshmatov.bookpro.presentation.screen.setting.feature.language

import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.core.utils.string
import uz.toshmatov.bookpro.presentation.screen.setting.feature.language.component.LangType
import uz.toshmatov.bookpro.presentation.screen.setting.feature.language.component.LanguageModel
import uz.toshmatov.currency.presentation.main.tabscreen.setting.feature.language.intents.LanguageState

class LanguageViewModel : ViewModel() {

    private val _state: MutableStateFlow<LanguageState> = MutableStateFlow(LanguageState())
    val state: StateFlow<LanguageState> = _state.asStateFlow()

    private val languageList = listOf(
        LanguageModel(string.uzbek_language, drawable.uzbekista_n, LangType.UZBEK, "uz"),
        LanguageModel(string.english_language, drawable.united_states, LangType.ENGLISH, "en"),
        LanguageModel(string.russian_language, drawable.russia, LangType.RUSSIAN, "ru"),
    )

    init {
        _state.update {
            it.copy(languageList = languageList.toPersistentList())
        }
    }
}