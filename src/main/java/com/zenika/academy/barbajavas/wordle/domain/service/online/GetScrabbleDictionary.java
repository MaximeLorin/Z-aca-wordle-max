package com.zenika.academy.barbajavas.wordle.domain.service.online;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class GetScrabbleDictionary {
    private final RestTemplate restTemplate;

    public GetScrabbleDictionary(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getHttpRequest(@Value("${wordle.language}") String lang,int length) throws JsonProcessingException {
        String url="https://scrabble-api.fly.dev/api/dictionaries/"+lang.toLowerCase()+"/randomWord?length="+length;
        String answer=this.restTemplate.getForObject(url,String.class);

        ObjectMapper mapper=new ObjectMapper();
        Word word= mapper.readValue(answer,Word.class);

        return word.word;
    }
}
