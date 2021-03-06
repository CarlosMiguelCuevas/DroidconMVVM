package upday.droidconmvvm.model;

import androidx.annotation.NonNull;

/**
 * Language class containing the name of the language and the code.
 */
public class Language {

    @NonNull
    private final String mName;
    @NonNull
    private final LanguageCode mCode;

    public Language(@NonNull final String name, @NonNull final LanguageCode code) {
        mName = name;
        mCode = code;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public LanguageCode getCode() {
        return mCode;
    }

    public enum LanguageCode {
        EN, DE, HR
    }
}
