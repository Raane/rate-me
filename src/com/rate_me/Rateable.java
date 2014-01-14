package com.rate_me;

import EloRating.EloRating;

public class Rateable {
	private final int id;
	private String name;
	private int elo;
	
	public Rateable(int id, String name) {
		this.name = name;
		this.id = id;
		elo = EloRating.DEFAULT_ELO;
	}
	
	public String getName() {
		return name;
	}
	
	public String getElo() {
		return String.valueOf(elo);
	}
	
	public int getId() {
		return id;
	}
	
}
