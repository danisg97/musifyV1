package com.musify.musify.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;

@Entity
public class People {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int years;
	private long member;
	
	public People() {		
	}
	
	public People(String name) {
		this.name = name;
		
	}
	
	public People(String name, int years) {
		this.name = name;
		this.years = years;
		
	}
	
	public People(String name, int years, long member) {
		this.name = name;
		this.years = years;
		this.member = member;
		
	}
	
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
	public long getMember() {
		return member;
	}
	public void setMember(long member) {
		this.member = member;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	
}

