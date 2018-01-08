package org.bookpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.test.bookpubstarter.dbcount.EnableDbCounting;
import org.test.bookpubstarter.dbcount.UsedForTesting;

// This @SpringBootApplication can be replaced with all 3 annotation below, we need exclude filter so we use this config
@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters=@ComponentScan.Filter(UsedForTesting.class))
@EnableDbCounting
public class BookpubApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BookpubApplication.class, args);
	}
	
	@Bean
	public StartupRunner scheduleRunner() {
		return new StartupRunner();
	}
}

