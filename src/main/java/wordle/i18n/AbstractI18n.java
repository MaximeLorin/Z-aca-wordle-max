package wordle.i18n;

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
    public String getRandomWord() {
        Random random = new Random();
        int randomNumber = random.nextInt(dictionary.size())+1;
        return dictionary.stream().skip(randomNumber).findFirst().orElse(null);
    }

    @Override
    public boolean wordExists(String word) {
        return dictionary.stream().anyMatch((s) -> s.equalsIgnoreCase(word.toUpperCase()));
    }

    protected abstract void loadDictionnary() throws FileNotFoundException;
}

