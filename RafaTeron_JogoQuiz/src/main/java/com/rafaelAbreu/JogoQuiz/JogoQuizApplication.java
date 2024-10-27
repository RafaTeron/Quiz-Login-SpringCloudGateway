package com.rafaelAbreu.JogoQuiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JogoQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(JogoQuizApplication.class, args);
	}

}
