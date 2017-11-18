package com.leadiq.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadiq.database.InMemoryDatabase;
import com.leadiq.model.Transaction;


@Service
public class InMemoryDatabaseService {
	
	@Autowired
	InMemoryDatabase database;
	
	
	public boolean containsTransaction(Integer transactionId) {
		return database.getTransactions().containsKey(transactionId);
	}
	
	public void addTransaction(Transaction transaction) {
		database.getTransactions().put(transaction.getId(), transaction);
		
		// update the index
		ArrayList<Integer> al = getTransactionIdsForType(transaction.getType());
		if(al == null) {
			al = new ArrayList<Integer>();
		}
		al.add(transaction.getId());

		database.getTypeIndex().getMap().put(transaction.getType(), al);
					
	}
	
	public Transaction getTransaction(Integer transactionId) {
		return database.getTransactions().get(transactionId);
	}
	
	public ArrayList<Integer> getTransactionIdsForType(String type) {
		ArrayList<Integer> al = database.getTypeIndex().getMap().get(type);
		
		if(al == null) {
			al = new ArrayList<Integer>();
		}
		
		return al;
	}

	

}
