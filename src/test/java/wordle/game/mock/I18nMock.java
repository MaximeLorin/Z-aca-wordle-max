package wordle.game.mock;

import wordle.i18n.I18n;

import java.util.Arrays;
import java.util.List;

public class I18nMock implements I18n {

    private String randomWord;
    private List<String> dictionary;

    public I18nMock(String randomWord, List<String> dictionary){
        this.randomWord = randomWord;
        this.dictionary = dictionary;
    }

    @Override
    public String getMessage(String key) {
        return "Message";
    }

    @Override
    public String getMessage(String key, Object... params) {
        return "Message"+ Arrays.toString(params);
    }

    @Override
    public String getRandomWord() {
        return this.randomWord;
    }

    @Override
    public boolean wordExists(String word) {
        return dictionary.stream().anyMatch(s -> s.equalsIgnoreCase(word));
    }
}
