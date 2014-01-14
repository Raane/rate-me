package com.rate_me;

import EloRating.EloRating;

public class Rateable {
	private final int id;
	private String name;
	private int elo;
	
	public Rateable(int id, String name, int elo) {
		this.name = name;
		this.id = id;
		this.elo = elo;
	}
	
	public String getName() {
		return name;
	}
	
	public int getElo() {
		return elo;
	}
	
	public int getId() {
		return id;
	}
	
}
