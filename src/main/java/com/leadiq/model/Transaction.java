package com.leadiq.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
public class Transaction {
	private int id;
	private int amount;
	private String type;
	private int parent_id;
	
	@Autowired
	@JsonIgnore
	private TransactionStats stats;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public TransactionStats getStats() {
		return stats;
	}
	public void setStats(TransactionStats stats) {
		this.stats = stats;
	}

}
