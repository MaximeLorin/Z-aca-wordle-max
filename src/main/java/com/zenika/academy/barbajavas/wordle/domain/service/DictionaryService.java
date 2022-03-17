package com.zenika.academy.barbajavas.wordle.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;



public interface DictionaryService {

    String getRandomWord(int length) throws JsonProcessingException;

    boolean wordExists(String word) throws JsonProcessingException;
}