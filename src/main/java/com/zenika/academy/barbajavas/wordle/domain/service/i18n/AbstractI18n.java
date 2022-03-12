package com.zenika.academy.barbajavas.wordle.domain.service.i18n;

import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.*;

public abstract class AbstractI18n implements I18n {

    protected final ResourceBundle resBundle;
    protected final Set<String> dictionary = new HashSet<>();

    public AbstractI18n(ResourceBundle res) throws FileNotFoundException {
        this.resBundle = res;
        this.loadDictionnary();
    }

    @Override
    public String getMessage(String key) {
        try {
            return resBundle.getString(key);
        } catch (MissingResourceException e) {
            return "[" + key + "]";
        }
    }

    @Override
    public String getMessage(String key, Object... params) {
        try {
            return MessageFormat.format(this.resBundle.getString(key), params);
        } catch (MissingResourceException e) {
            return "[" + key + "]";
        }
    }

    @Override
    public String getRandomWord(int length) {
        Random random = new Random();
        final List<String> wordsOfLength = dictionary.stream().filter(w -> w.length() == length).toList();
        int randomNumber = random.nextInt(wordsOfLength.size());
        return wordsOfLength.get(randomNumber);
    }

    @Override
    public boolean wordExists(String word) {
        return dictionary.contains(word.toUpperCase(Locale.ROOT));
    }

    protected abstract void loadDictionnary() throws FileNotFoundException;
}

