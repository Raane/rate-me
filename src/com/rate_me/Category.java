package com.rate_me;

import java.util.ArrayList;

public class Category {
	private ArrayList<Rateable> rateables;
	private String name;
	
	public Category (String name){
		this.name = name;
		rateables = new ArrayList<Rateable>();
		
	}
}
