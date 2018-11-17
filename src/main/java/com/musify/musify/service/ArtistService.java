package com.musify.musify.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.musify.musify.model.Artist;
import com.musify.musify.repository.ArtistRepository;

@Service
public class ArtistService {
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    @Autowired
    ArtistRepository artistRepo;
    
    PreparedStatement preparedstatement = null;
    
	private static final String DB_URL = "jdbc:h2:~/test";  
	
	// private static final String SQL_CREATEARTIST = "CREATE TABLE ARTIST (ID LONG NOT NULL AUTO_INCREMENT, NAME VARCHAR(40) NOT NULL, STYLE LONG, MEMBER LONG, RELATED LONG, PRIMARY KEY (ID), CONSTRAINT FK_STYLE FOREIGN KEY (STYLE) REFERENCES STYLE(ID), CONSTRAINT FK_MEMBER FOREIGN KEY (MEMBER) REFERENCES PEOPLE(ID))";
	private static final String SQL_INSERTARTIST = "INSERT INTO ARTIST(NAME, YEAR) values (?, ?)";
	private static final String SQL_ARTIST = "SELECT ID, NAME, YEAR FROM ARTIST";
	
	public void addArtist(Artist artist) {
		conn = manager.open(DB_URL);
		
		if(!artistRepo.existsArtist(artist.getName())) {
			try{
				preparedstatement = conn.prepareStatement(SQL_INSERTARTIST);
		        preparedstatement.setString(1, artist.getName());
		        preparedstatement.setInt(2, artist.getYear());
		        
		        preparedstatement.executeUpdate();
			}catch (SQLException e) {
		        System.out.println("SQLException INSERT ARTIST method");
		        throw new RuntimeException(e);
			}finally{
		        close(preparedstatement);
			}
		}
	        manager.close(conn);
	}

	public List<Artist> allArtist() {
		 List<Artist> lista = new ArrayList<Artist>();
	        conn = manager.open(DB_URL);
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        
	        try {
	            preparedStatement = conn.prepareStatement(SQL_ARTIST);
	            resultSet = preparedStatement.executeQuery();
	            while(resultSet.next()) {
	                // Artist artist = new Artist(resultSet.getString(1), resultSet.getInt(2));
	            	Artist artist = new Artist(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
	                lista.add(artist);
	                        
	            }
	        }catch ( SQLException e) {
	            System.out.println("SQLException LISTA ARTIST method");
	            throw new RuntimeException(e);
	        }finally {
	            close(preparedStatement);
	            close(resultSet);
	        }
	        manager.close(conn);
	        return lista;
	}
	
	 public void close(ResultSet resultSet){
		 try{
			 resultSet.close();
		 }catch (SQLException e) {
			 System.out.println("SQLException CLOSE method");
			 throw new RuntimeException(e);
		 }
	 }
	 
	 public void close(PreparedStatement preparedStatement){
		 try{
			 preparedStatement.close();
		 }catch (SQLException e) {
			 System.out.println("SQLException close CLOSE02 method");
		     throw new RuntimeException(e);
		 }
	}

}
