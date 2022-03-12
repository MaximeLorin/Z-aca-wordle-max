package com.zenika.academy.barbajavas.wordle.domain.service.i18n;

public interface I18n {

    String getMessage(String key);

    String getMessage(String key, Object... params);

    String getRandomWord(int length);

    boolean wordExists(String word);
}

