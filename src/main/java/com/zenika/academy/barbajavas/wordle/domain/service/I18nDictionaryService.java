package com.zenika.academy.barbajavas.wordle.domain.service;

import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class I18nDictionaryService implements DictionaryService {

    private final I18n i18n;
    @Autowired
    public I18nDictionaryService(I18n i18n) {
        this.i18n = i18n;
    }

    @Override
    public String getRandomWord(int length) {
        return i18n.getRandomWord(length);
    }

    @Override
    public boolean wordExists(String word) {
        return i18n.wordExists(word);
    }
}
