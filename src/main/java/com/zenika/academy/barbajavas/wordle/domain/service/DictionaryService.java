package com.zenika.academy.barbajavas.wordle.domain.service;

public interface DictionaryService {
    String getRandomWord(int length);
    boolean wordExists(String word);
}
