package com.zenika.academy.barbajavas.wordle.domain.service.online;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetScrabbleDictionary {
    private final RestTemplate restTemplate;


    public GetScrabbleDictionary(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getHttpRequest(){
        String url="http://perdu.com";
        return this.restTemplate.getForObject(url,String.class);
    }
}
