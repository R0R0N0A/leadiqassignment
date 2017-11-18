package com.leadiq.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
public class Transaction {
	private Integer id;
	private Integer amount;
	private String type;
	private Integer parent_id;
	
	@Autowired
	@JsonIgnore
	private TransactionStats stats;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public TransactionStats getStats() {
		return stats;
	}
	public void setStats(TransactionStats stats) {
		this.stats = stats;
	}

	public String toString() {
		return "id="+id+" amount"+amount+" type"+type+" parent"+parent_id;
	}
}
