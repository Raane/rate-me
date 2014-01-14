package com.rate_me;

import java.util.ArrayList;

public class Category {
	private final int id;
	private ArrayList<Rateable> rateables;
	private String name;
	
	public Category (int id, String name){
		this.name = name;
		this.id = id;
		rateables = new ArrayList<Rateable>();
	}
	public void addRateable(Rateable rateable){
		//TODO adds rateable
	}
	public void removeRateable(){
		//TODO removes a rateable
	}
}
