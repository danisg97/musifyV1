package com.musify.musify.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;

@Entity
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int year;
	private long style;
	private long menbers;
	private long related;
	
	public Artist() {
	}
	
	public Artist(String name, int year) {
		this.name = name;
		this.year = year;
	}
	
	public Artist(String name, int year, long style, long menbers, long related) {
		this.name = name;
		this.year = year;
		this.style = style;
		this.menbers = menbers;
		this.related = related;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getStyle() {
		return style;
	}
	public void setStyle(long style) {
		this.style = style;
	}
	public long getMenbers() {
		return menbers;
	}
	public void setMenbers(long menbers) {
		this.menbers = menbers;
	}
	public long getRelated() {
		return related;
	}
	public void setRelated(long related) {
		this.related = related;
	}
	
}