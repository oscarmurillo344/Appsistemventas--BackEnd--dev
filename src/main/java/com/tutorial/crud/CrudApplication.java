package com.tutorial.crud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class CrudApplication {
	@PostConstruct
	public void init(){ TimeZone.setDefault(TimeZone.getTimeZone("UTC-5")); }
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
}
