package com.leadiq.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadiq.model.MyData;
import com.leadiq.model.Transaction;
import com.leadiq.model.TransactionStats;
import com.leadiq.model.TransactionTypeIndex;


@Service
public class TransactionService {
	
	@Autowired
	MyData data;
	
	@Autowired
	TransactionTypeIndex typeIndex;
	
	public void addTransaction(Transaction t) {
		int id = t.getId();
		if(data.transactions.containsKey(id)) {
			System.out.println("Already Exists");
		} else {
			
			data.transactions.put(id, t);
			
			// update the sum
			int amount = t.getAmount();
			Transaction parent = data.transactions.get(t.getParent_id());
			TransactionStats  stats;
			while(parent!=null) {
				stats = parent.getStats();
				
				System.out.println("parent = " + parent.getId() + "  " + parent.getAmount() + " "+ stats.getSum());
			
				
				stats.setSum(stats.getSum() + amount);
				
				parent = data.transactions.get(parent.getParent_id());
			}
			
			// update the index
			ArrayList<Integer> al = typeIndex.getMap().get(t.getType());
			if(al == null) {
				al = new ArrayList<Integer>();
			}
			al.add(t.getId());
			
			typeIndex.getMap().put(t.getType(), al);
			
			
		}

	}
	
	public Transaction getTransaction(int transactionId) {
		return data.transactions.get(transactionId);
	}
	
	public ArrayList<Integer> getTransactionsOfType(String type) {
		return typeIndex.getMap().get(type);
		
	}
	
	

}
