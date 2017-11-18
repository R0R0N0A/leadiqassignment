package com.leadiq.model;

import java.util.HashMap;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("application")
public class MyData {
	public LinkedList<String> data;
	public HashMap<Integer, Transaction> transactions;
	
	@Autowired
	@JsonIgnore
	private TransactionTypeIndex typeIndex;
	
	public MyData() {
		data = new LinkedList<String>();
		transactions = new HashMap<Integer, Transaction>();
	}
	
	
}
