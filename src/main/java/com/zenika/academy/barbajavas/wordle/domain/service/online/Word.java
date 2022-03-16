package com.zenika.academy.barbajavas.wordle.domain.service.online;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Word {
    boolean wordExists;
    String word;

    @JsonCreator
    public Word(@JsonProperty("wordExists")boolean wordExists, @JsonProperty("word")String word) {
        this.wordExists = wordExists;
        this.word = word;
    }

    public boolean getWordExists() {
        return wordExists;
    }

    public void setWordExists(boolean wordExists) {
        this.wordExists = wordExists;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
