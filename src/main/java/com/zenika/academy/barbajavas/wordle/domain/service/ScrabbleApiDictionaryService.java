package com.zenika.academy.barbajavas.wordle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Component
public class ScrabbleApiDictionaryService implements DictionaryService {
    private final RestTemplate restTemplate;
    private final String language;

    @Autowired
    public ScrabbleApiDictionaryService(
            RestTemplateBuilder restTemplatebuilder,
            @Value("${wordle.scrabble-api-uri}") String scrabbleApiBaseUri,
            @Value("${wordle.language}") String language
    ) {
        this.restTemplate = restTemplatebuilder
                .rootUri(scrabbleApiBaseUri)
                .build();
        this.language = language.toLowerCase(Locale.ROOT);
    }

    @Override
    public String getRandomWord(int length) {
        final ScrabbleApiResponse response = restTemplate.getForObject("/dictionaries/" + language + "/randomWord?length="+length, ScrabbleApiResponse.class);
        return response.word;
    }

    @Override
    public boolean wordExists(String word) {
        try {
            final ResponseEntity<ScrabbleApiResponse> response = restTemplate.getForEntity("/dictionaries/" + language + "/words/"+ word, ScrabbleApiResponse.class);
            return response.getStatusCode().equals(HttpStatus.OK) && response.getBody().wordExists();
        }
        catch (HttpClientErrorException e) {
            return false;
        }
    }
    
    private record ScrabbleApiResponse(String word, boolean wordExists) {}
}
