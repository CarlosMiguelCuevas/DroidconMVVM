package upday.droidconmvvm.datamodel;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import upday.droidconmvvm.model.Language;

public class DataModel implements IDataModel {

    @NonNull
    @Override
    public List<Language> getSupportedLanguages() {
        return Arrays.asList(Language.values());
    }

    @NonNull
    @Override
    public String getGreetingByLanguageCode(@NonNull final Language language) {
        switch (language) {
            case German:
                return "Guten Tag!";
            case English:
                return "Hello!";
            case Slovakian:
                return "Zdravo!";
            case Spanish:
                return "Queobo prro!";
            default:
                return "####";
        }
    }
}
