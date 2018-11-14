package com.musify.musify.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.musify.musify.model.Artist;
import com.musify.musify.model.Related;
import com.musify.musify.service.ConnectionManager;
import com.musify.musify.service.DBConnection;

@Repository
public class RelatedRepository {

	private static final String DB_URL = "jdbc:h2:~/test";
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
	
    // List Artist Related.
 	public List<Related> getRelated() {
 		
 		return null;
 	}
    
}
