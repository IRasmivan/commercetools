package com.rasmivan.commercetools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CommercetoolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommercetoolsApplication.class, args);
	}

}
