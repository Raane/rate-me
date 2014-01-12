package com.rate_me;

import java.util.ArrayList;

public class User {
	private final int id;
	private ArrayList<Category> categories;
	
	public User(int id) {
		categories = new ArrayList<Category>(); 
		this.id = id;
	}
	
	public void addCategory(String category){
		//TODO add category
	}
	public void removeCategory(int categoryID){
		//TODO remove category 
	}
	
}
