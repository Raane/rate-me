package com.rate_me;

import EloRating.EloRating;

public class Rateable {
	private String name;
	private int elo;
	
	public Rateable(String name) {
		this.name = name;
		elo = EloRating.DEFAULT_ELO;
		
	}
	
}
