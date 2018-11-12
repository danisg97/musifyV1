package com.musify.musify.model;

import javax.validation.constraints.NotNull;

public class People {

	private long id;
	private String name;
	private int years;
	
	public People() {		
	}
	
	public People(String name) {
		this.name = name;
		
	}
	
	public People(long id, String name, int years) {
		this.id = id;
		this.name = name;
		this.years = years;
		
	}
	
	@NotNull
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	
}

