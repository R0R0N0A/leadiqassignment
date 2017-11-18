package com.leadiq.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadiq.model.Transaction;
import com.leadiq.model.TransactionStats;


@Service
public class TransactionService {
	
	@Autowired
	InMemoryDatabaseService databaseService;
	
	public Transaction addTransaction(Transaction transaction) {
		
		Integer id = transaction.getId();
		
		if(databaseService.containsTransaction(id)) {
			System.out.println("Already Exists");
			throw new IllegalArgumentException("Transaction with given id already exists");
		} else {
			
			databaseService.addTransaction(transaction);
		
			// update the TransactionStats for all the ancestors & itself for the newly added transaction
			Integer amount = transaction.getAmount();

			Transaction current = transaction;
			TransactionStats stats;

			while(current!=null) {
				
				stats = current.getStats();
				
				stats.setSum(stats.getSum() + amount);
				
				if(current.getParent_id()!=null) {
					current = databaseService.getTransaction(current.getParent_id());	
				} else {
					current = null;
				}
				
			}
			
		}
		
		return databaseService.getTransaction(id);
	}
	
	public Transaction getTransaction(Integer transactionId) {
		return databaseService.getTransaction(transactionId);
	}
	
	public ArrayList<Integer> getTransactionsOfType(String type) {
		return databaseService.getTransactionIdsForType(type);
	}

	public Integer getTransactionSum(Integer transactionId) {
		Transaction transaction = getTransaction(transactionId);
		
		if(transaction!=null) {
			return transaction.getStats().getSum();	
		} else {
			return -1;
		}

	}
	

}
