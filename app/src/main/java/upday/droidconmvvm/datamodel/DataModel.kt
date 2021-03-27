package upday.droidconmvvm.datamodel

import android.util.Log
import upday.droidconmvvm.model.Language
import java.util.*

class DataModel : IDataModel {
    override fun getSupportedLanguages(): List<Language> = Arrays.asList(*Language.values())

    override fun getGreetingByLanguageCode(language: Language): String =
        when (language) {
            Language.German -> "Guten Tag!"
            Language.English -> "Hello!"
            Language.Slovakian -> "Zdravo!"
            Language.Spanish -> "Queobo prro!"
            else -> "####"
        }.also { Log.d("DataModel", "retrieving greeting") }

}