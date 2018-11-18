package com.musify.musify.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.musify.musify.model.Artist;

public interface ArtistRepository {
	
	 boolean existsArtist(String name);
	 
	 List<Artist> findArtistByStyle(long idStyle);
	 
	 List<Artist> findRelatedArtist(long idArtist);
	 
	 void close(PreparedStatement preparedStatement);
	 
	 void close(Statement statement);
	 
	 void close(ResultSet resultSet);

}
