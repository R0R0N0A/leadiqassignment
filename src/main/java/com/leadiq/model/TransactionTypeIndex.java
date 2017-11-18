package com.leadiq.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("application")
public class TransactionTypeIndex {
	private HashMap<String, ArrayList<Integer>> map;

	public HashMap<String, ArrayList<Integer>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, ArrayList<Integer>> map) {
		this.map = map;
	}
	
	public TransactionTypeIndex() {
		map = new HashMap<String, ArrayList<Integer>>();
	}
	
	
}