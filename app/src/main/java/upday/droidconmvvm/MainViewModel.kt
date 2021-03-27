package upday.droidconmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import upday.droidconmvvm.datamodel.IDataModel
import upday.droidconmvvm.model.Language

/**
 * View model for the main activity.
 */
class MainViewModel(
    private val mDataModel: IDataModel
) : ViewModel() {

    private val currentLanguage: MutableLiveData<Language> by lazy {
        MutableLiveData<Language>()
    }

    val currentGreeting: LiveData<String> = Transformations.map(currentLanguage) { language ->
        mDataModel.getGreetingByLanguageCode(language)
    }

    fun setLanguageSelected(language: Language) {
        if (currentLanguage.value != language)
            currentLanguage.value = language
    }

    val supportedLanguages: List<Language> by lazy {
        mDataModel.supportedLanguages
    }


}