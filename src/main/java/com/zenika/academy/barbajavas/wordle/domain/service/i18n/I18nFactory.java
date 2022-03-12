package com.zenika.academy.barbajavas.wordle.domain.service.i18n;

public class I18nFactory {

    public static I18n getI18n(String language) throws Exception {
        I18n i18n;
        if (Language.EN.toString().equalsIgnoreCase(language)) {
            i18n = new En();
        } else if (Language.FR.toString().equalsIgnoreCase(language)) {
            i18n = new Fr();
        } else throw new Exception("Language not allowed. Choose between FR or EN.");

        return i18n;

    }

}
