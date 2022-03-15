package com.zenika.academy.barbajavas.wordle.domain.service.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class En extends AbstractI18n {

    public En() throws FileNotFoundException {
        super(ResourceBundle.getBundle("messages", new Locale("en", "US")));
    }

    @Override
    protected void loadDictionnary() throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String path = Objects.requireNonNull(classLoader.getResource("dico_en.txt")).getPath();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            String line = scanner.next();
            if (line.length() > 3 && !line.contains("-") && line.length() < 11) {
                this.dictionary.add(line.toUpperCase());
            }
        }
    }
}
