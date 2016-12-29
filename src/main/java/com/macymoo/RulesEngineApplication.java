package com.macymoo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RulesEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RulesEngineApplication.class, args);
	}

    @Value("${rulesEngineVersion}")
    private String rulesEngineVersion;


	@RequestMapping("/version")
	public String version( ){
		return this.rulesEngineVersion;
	}
}
