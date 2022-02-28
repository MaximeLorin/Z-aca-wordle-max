package wordle.i18n;

public interface I18n {

    String getMessage(String key);

    String getMessage(String key, Object... params);

    String getRandomWord();

    boolean wordExists(String word);
}

