package com.musify.musify.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;

@Entity
public class Styles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long idArtist;
	private long idStyle;
	
	public Styles(long id, long idArtist, long idStyle) {
		this.id = id;
		this.idArtist = idArtist;
		this.idStyle = idStyle;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdArtist() {
		return idArtist;
	}

	public void setIdArtist(long idArtist) {
		this.idArtist = idArtist;
	}

	public long getIdStyle() {
		return idStyle;
	}

	public void setIdStyle(long idStyle) {
		this.idStyle = idStyle;
	}
	
}
