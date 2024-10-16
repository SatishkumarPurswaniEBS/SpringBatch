package com.gog.learnspringbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.gog")
@EnableJpaRepositories(basePackages = "com.gog.repository")
@EntityScan(basePackages = "com.gog.model")
@EnableScheduling
public class LearnspringbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnspringbatchApplication.class, args);
	}

}
