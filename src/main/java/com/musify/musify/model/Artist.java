package com.musify.musify.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class Artist {

	private long id;
	private String name;
	private int year;
	private Style style;
	private ArrayList<People> menbers;
	private Artist related;
	
	public Artist() {
	}
	
	public Artist(long id, String name, int year, Style style, ArrayList<People> menbers, Artist related) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.style = style;
		this.menbers = menbers;
		this.related = related;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public ArrayList<People> getMenbers() {
		return menbers;
	}
	public void setMenbers(ArrayList<People> menbers) {
		this.menbers = menbers;
	}
	public Artist getRelated() {
		return related;
	}
	public void setRelated(Artist related) {
		this.related = related;
	}
	
}