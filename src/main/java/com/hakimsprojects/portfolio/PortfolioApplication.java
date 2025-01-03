package com.hakimsprojects.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.hakimsprojects.portfolio.security.config.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class PortfolioApplication {

	public static void main(String[] args) {	        
		SpringApplication.run(PortfolioApplication.class, args);
	}

}
