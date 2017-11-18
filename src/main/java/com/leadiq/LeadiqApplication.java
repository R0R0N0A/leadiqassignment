package com.leadiq;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LeadiqApplication {

	@Bean
	public HashMap<String, ArrayList<Integer>> createHashMapOfArrayLists() {
		return new HashMap<String, ArrayList<Integer>>();
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LeadiqApplication.class, args);
	}

}
