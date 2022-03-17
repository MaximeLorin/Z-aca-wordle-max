package com.zenika.academy.barbajavas.wordle.domain.service.online;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(value="wordle.type", havingValue = "online")
public class GetScrabbleDictionary implements DictionaryService {
    private final RestTemplate restTemplate;
    private final String language;

    public GetScrabbleDictionary(RestTemplateBuilder restTemplateBuilder,@Value("${wordle.language}") String language) {
        this.restTemplate = restTemplateBuilder.build();
        this.language=language.toLowerCase();
    }


    @Override
    public String getRandomWord(int length) throws JsonProcessingException {
        String url="https://scrabble-api.fly.dev/api/dictionaries/"+language+"/randomWord?length="+length;
        String answer=this.restTemplate.getForObject(url,String.class);

        Word word=wordMap(answer);

        return word.word;
    }
    @Override
    public boolean wordExists( String word) throws JsonProcessingException {
        String url="https://scrabble-api.fly.dev/api/dictionaries/"+language+"/words/"+word;
        String answer=this.restTemplate.getForObject(url,String.class);
        Word exist=wordMap(answer);
        return exist.wordExists;
    }

    private Word wordMap(String json) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.readValue(json,Word.class);
    }
}
