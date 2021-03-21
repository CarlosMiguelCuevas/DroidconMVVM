package upday.droidconmvvm.datamodel;

import androidx.annotation.NonNull;

import java.util.List;

import upday.droidconmvvm.model.Language;

public interface IDataModel {

    @NonNull
    List<Language> getSupportedLanguages();

    @NonNull
    String getGreetingByLanguageCode(@NonNull final Language code);
}
