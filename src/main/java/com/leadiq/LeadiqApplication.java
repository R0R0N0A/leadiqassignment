package com.leadiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.leadiq.model.MyData;
import com.leadiq.model.TransactionStats;

@SpringBootApplication
public class LeadiqApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LeadiqApplication.class, args);
	}

	
	@Bean
	public MyData myData() {
		return new MyData();
	}

	@Bean
	public TransactionStats createTransactionStats() {
		return new TransactionStats();
	}
}
