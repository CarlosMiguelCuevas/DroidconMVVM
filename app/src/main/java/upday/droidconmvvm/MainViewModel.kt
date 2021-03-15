package upday.droidconmvvm

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import upday.droidconmvvm.datamodel.IDataModel
import upday.droidconmvvm.model.Language
import upday.droidconmvvm.model.Language.LanguageCode
import upday.droidconmvvm.schedulers.ISchedulerProvider

/**
 * View model for the main activity.
 */
class MainViewModel(
    private val mDataModel: IDataModel,
    private val mSchedulerProvider: ISchedulerProvider
) : ViewModel() {

    private val mSelectedLanguage = BehaviorSubject.create<Language>()
    var selectedLanguage: Language? = null
        private set

    val greeting: Observable<String>
        get() = mSelectedLanguage
            .map { selected: Language? ->
                selectedLanguage = selected
                selected
            }
            .observeOn(mSchedulerProvider.computation())
            .map { obj: Language -> obj.code }
            .flatMap { code: LanguageCode? ->
                mDataModel.getGreetingByLanguageCode(
                    code!!
                )
            }

    val supportedLanguages: Observable<List<Language>>
        get() = mDataModel.supportedLanguages

    fun languageSelected(language: Language) {
        mSelectedLanguage.onNext(language)
    }
}