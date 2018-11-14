package com.musify.musify.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;

@Entity
public class Related {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long idArtist;
	private long idArtistRelated;
	
	public Related(long idArtist, long idArtistRelated) {
		this.idArtist = idArtist;
		this.idArtistRelated = idArtistRelated;
		
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

	public long getIdArtistRelated() {
		return idArtistRelated;
	}

	public void setIdArtistRelated(long idArtistRelated) {
		this.idArtistRelated = idArtistRelated;
	}

}
