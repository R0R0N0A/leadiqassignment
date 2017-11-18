package com.leadiq.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leadiq.model.Transaction;
import com.leadiq.model.request.TransactionRequest;
import com.leadiq.service.TransactionService;

@RestController
@Description("Controller for all transaction api requests")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	@Autowired
	ApplicationContext applicationContext;


	@RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.PUT)
	public @ResponseBody Transaction addTransaction(@PathVariable("transaction_id")int transactionId, @RequestBody TransactionRequest transactionRequest) {
		
		Transaction transaction = applicationContext.getBean(Transaction.class);
		
		transaction.setId(transactionId);
		transaction.setAmount(transactionRequest.getAmount());
		transaction.setType(transactionRequest.getType());
		transaction.setParent_id(transactionRequest.getParent_id());
		
		return transactionService.addTransaction(transaction);

	}
	
	
	@RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.GET)
	public @ResponseBody Transaction getTransaction(@PathVariable("transaction_id")int transactionId) {
		return transactionService.getTransaction(transactionId);
	}
	
	
	@RequestMapping(value = "/transactionservice/types/{type}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Integer> getTransactionsOfType(@PathVariable("type")String type) {
		return transactionService.getTransactionsOfType(type);
	}
	
	
	@RequestMapping(value = "/transactionservice/sum/{transaction_id}", method = RequestMethod.GET)
	public @ResponseBody Integer getTransactionSum(@PathVariable("transaction_id")Integer transactionId) {
		return transactionService.getTransactionSum(transactionId);
	}
	
	

}
