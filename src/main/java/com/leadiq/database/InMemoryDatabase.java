package com.leadiq.database;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leadiq.model.Transaction;

@Component
@Scope("application")
public class InMemoryDatabase {

	private HashMap<Integer, Transaction> transactions;
	
	@Autowired
	@JsonIgnore
	private TransactionTypeIndex typeIndex;

	public InMemoryDatabase() {
		setTransactions(new HashMap<Integer, Transaction>());
	}

	public HashMap<Integer, Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(HashMap<Integer, Transaction> transactions) {
		this.transactions = transactions;
	}
	

	public TransactionTypeIndex getTypeIndex() {
		return typeIndex;
	}

	public void setTypeIndex(TransactionTypeIndex typeIndex) {
		this.typeIndex = typeIndex;
	}
	
}
