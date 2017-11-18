package com.leadiq.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leadiq.model.Transaction;
import com.leadiq.model.TransactionStats;
import com.leadiq.service.TransactionService;

@RestController
@Description("A controller for handling requests for hello messages")
public class TransactionController {
	
	
	@Autowired
	TransactionService transactionService;


	@RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.PUT)
	public @ResponseBody String putData(@PathVariable("transaction_id")int transactionId, @RequestBody Transaction transaction) {
		
	System.out.println(transactionId);	
	System.out.println(transaction);
	
	transaction.setId(transactionId);
	
	
	transaction.setStats(new TransactionStats());
	TransactionStats stats = transaction.getStats();
	
	System.out.println("stats = " + stats);
	
	stats.setSum(transaction.getAmount());
	
	transactionService.addTransaction(transaction);
	
	
	return "ok";
		
	}
	
	
	@RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.GET)
	@ResponseBody
	public Transaction hello1(@PathVariable("transaction_id")int transactionId) {
		return transactionService.getTransaction(transactionId);
		
	}
	
	
	@RequestMapping(value = "/transactionservice/types/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Integer> hello2(@PathVariable("type")String type) {
		return transactionService.getTransactionsOfType(type);
	}
	
	
	@RequestMapping(value = "/transactionservice/sum/{transaction_id}", method = RequestMethod.GET)
	@ResponseBody
	public Integer hello3(@PathVariable("transaction_id")int transactionId) {
		Transaction t = transactionService.getTransaction(transactionId);
		
		if(t!=null) {
			return t.getStats().getSum();	
		} else {
			return 0;
		}
		
	}
	
	

}
