package com.zenika.academy.barbajavas.wordle.domain.service.i18n;

import org.springframework.stereotype.Component;

@Component
public interface I18n {

    String getMessage(String key);

    String getMessage(String key, Object... params);

    String getRandomWord(int length);

    boolean wordExists(String word);
}

