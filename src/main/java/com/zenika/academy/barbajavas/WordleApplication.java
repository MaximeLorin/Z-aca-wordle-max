package com.zenika.academy.barbajavas;

import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18n;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18nFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class WordleApplication {
    
    @Bean
    public I18n i18n(@Value("${wordle.language}") String language) throws Exception {
        return I18nFactory.getI18n(language);
    }
    
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WordleApplication.class);
    }
}
