package ru.learnup.learnup.spring.mvc.homework34;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.learnup.spring.mvc.homework34.services.MessageSender;

@SpringBootApplication
public class Homework34Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Homework34Application.class, args);
    }
}
