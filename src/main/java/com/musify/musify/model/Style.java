package com.musify.musify.model;

import javax.validation.constraints.NotNull;

public class Style {
	
	private long id;
	private String name;
	
	public Style() {
	}
	
	public Style(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Style(String name) {
		this.name = name;
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
	
}
