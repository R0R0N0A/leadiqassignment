package com.leadiq.database;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("application")
public class TransactionTypeIndex {
	
	@Autowired
	private HashMap<String, ArrayList<Integer>> map;
	
	public HashMap<String, ArrayList<Integer>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, ArrayList<Integer>> map) {
		this.map = map;
	}
	
}