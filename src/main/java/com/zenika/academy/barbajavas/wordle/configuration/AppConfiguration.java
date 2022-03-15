package com.zenika.academy.barbajavas.wordle.configuration;

import com.zenika.academy.barbajavas.wordle.domain.model.Game;
import com.zenika.academy.barbajavas.wordle.domain.repository.GameRepository;
import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.DictionaryService;
import com.zenika.academy.barbajavas.wordle.application.GameManager;
import com.zenika.academy.barbajavas.wordle.domain.service.I18nDictionaryService;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import com.zenika.academy.barbajavas.wordle.domain.service.displayer.console.color.ConsoleColorDisplayer;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18n;
import com.zenika.academy.barbajavas.wordle.domain.service.i18n.I18nFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfiguration {
    @Bean
    public ConsoleColorDisplayer consoleColorDisplayerConfig(){
        return new ConsoleColorDisplayer();

    }

//    @Bean
//    public I18nFactory factoryConfig{
//        return new I18nFactory;
//    }
//
//    @Bean
//    public DictionaryService dictionaryServiceConfig(){
//        return new I18nDictionaryService(i18n);
//    }

    @Bean
    public GameRepository gameRepository(){
        return new GameRepository();
    }

    @Bean
    public GameManager gameManagerConfig(GameRepository gameRepository,DictionaryService dictionaryService){
        return new GameManager(dictionaryService,gameRepository);
    }
}
