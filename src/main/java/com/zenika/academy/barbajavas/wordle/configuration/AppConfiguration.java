package com.zenika.academy.barbajavas.wordle.configuration;

import com.zenika.academy.barbajavas.wordle.domain.repository.GameRepository;
import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.service.displayer.console.color.ConsoleColorDisplayer;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18n;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18nFactory;

import com.zenika.academy.barbajavas.wordle.domain.service.online.GetScrabbleDictionary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfiguration {
    @Bean
    public ConsoleColorDisplayer consoleColorDisplayerConfig(){
        return new ConsoleColorDisplayer();
    }

    @Bean
    public I18n i18n(@Value("${wordle.language}") String lang) throws Exception{
        return I18nFactory.getI18n(lang);
    }

    @Bean
    public GameRepository gameRepository(){
        return new GameRepository();
    }

    @Bean
    public GameManager gameManagerConfig(GameRepository gameRepository, DictionaryService dictionaryService, GetScrabbleDictionary getScrabbleDictionary){
        return new GameManager(dictionaryService,gameRepository,getScrabbleDictionary);
    }
}
