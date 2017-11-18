package com.leadiq;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.leadiq.model.Transaction;
import com.leadiq.model.request.TransactionRequest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LeadiqApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void test1PutTransaction() throws Exception {
		
		TransactionRequest tr = new TransactionRequest();
        tr.setAmount(5000);
        tr.setType("cars");
        int transactionId = 10;
        
		ResponseEntity<Transaction> entity = this.restTemplate.exchange("/transactionservice/transaction/"+transactionId,
				HttpMethod.PUT, new HttpEntity<TransactionRequest>(tr), Transaction.class);
		
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getAmount()).isEqualTo(tr.getAmount());
		assertThat(entity.getBody().getType()).isEqualTo(tr.getType());
		assertThat(entity.getBody().getId()).isEqualTo(transactionId);
		
		tr = new TransactionRequest();
        tr.setAmount(10000);
        tr.setType("shopping");
        transactionId = 11;
        
        int parent_id = 10;
        tr.setParent_id(parent_id);

        entity = this.restTemplate.exchange("/transactionservice/transaction/"+transactionId,
				HttpMethod.PUT, new HttpEntity<TransactionRequest>(tr), Transaction.class);
		
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getAmount()).isEqualTo(tr.getAmount());
		assertThat(entity.getBody().getType()).isEqualTo(tr.getType());
		assertThat(entity.getBody().getId()).isEqualTo(transactionId);
		assertThat(entity.getBody().getParent_id()).isEqualTo(parent_id);
	}
	
	
	@Test
	public void test2GetTransaction() throws Exception {
		int transactionId = 10;
		
		ResponseEntity<Transaction> entity = this.restTemplate.getForEntity("/transactionservice/transaction/"+transactionId, Transaction.class);
		
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

		
		assertThat(entity.getBody().getId()).isEqualTo(transactionId);
		assertThat(entity.getBody().getAmount()).isEqualTo(5000);
		
		transactionId = 11;
		entity = this.restTemplate.getForEntity("/transactionservice/transaction/"+transactionId, Transaction.class);
		
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getId()).isEqualTo(transactionId);
		assertThat(entity.getBody().getAmount()).isEqualTo(10000);
	}
	
	
	@Test
	public void test3GetTransactionTypes() throws Exception {
		
		String type = "cars";
		
		@SuppressWarnings("rawtypes")
		ResponseEntity<ArrayList> entity = this.restTemplate.getForEntity("/transactionservice/types/"+type, ArrayList.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().get(0)).isEqualTo(10);
		
		type = "shopping";
		entity = this.restTemplate.getForEntity("/transactionservice/types/"+type, ArrayList.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().get(0)).isEqualTo(11);

	}
	

	@Test
	public void test4GetTransactionSum() throws Exception {
		
		Integer transactionId = new Integer(10);
		
		ResponseEntity<Integer> entity = this.restTemplate.getForEntity("/transactionservice/sum/"+transactionId, Integer.class);
		
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo(15000);
		
		transactionId = 11;
		entity = this.restTemplate.getForEntity("/transactionservice/sum/"+transactionId, Integer.class);
		
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo(10000);
		

	}
	
	
}
