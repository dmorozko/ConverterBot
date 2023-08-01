package com.MorozovStudio.ConverterBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:D:\\JavaProject\\ConverterBot_v1.0\\application.properties")
public class ConverterBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterBotApplication.class, args);
	}

}
