package com.musify.musify.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.musify.musify.model.Artist;
import com.musify.musify.model.Related;
import com.musify.musify.service.ConnectionManager;
import com.musify.musify.service.DBConnection;

@Repository
public class ArtistRepository {
	
	private static final String DB_URL = "jdbc:h2:~/test";
	private static final String SQL_ARTISTSTYLE = "SELECT ARTIST.NAME FROM ARTIST INNER JOIN STYLES ON STYLES.ID_STYLE = ? AND ARTIST.ID = STYLES.ID_ARTIST";
	private static final String SQL_ARTISTRELATED = "SELECT ARTIST.NAME FROM ARTIST INNER JOIN RELATED ON RELATED.ID_ARTIST_RELATED = ? AND ARTIST.ID = RELATED.ID_ARTIST";
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
        
    // List artist by style.
	public List<Artist> findArtistByStyle(long idStyle) {
		
		List<Artist> listByStyle = new ArrayList<>();
		
		conn = manager.open(DB_URL);
		resultSet = null;
		
		try {
			preparedStatement = conn.prepareStatement(SQL_ARTISTSTYLE);
			preparedStatement.setLong(1, idStyle);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Artist artistFromDB = new Artist();
				artistFromDB.setName(resultSet.getString(1));
				
				listByStyle.add(artistFromDB);
			}
		}catch (SQLException e) {
			System.out.println("SQLException FIND ARTIST BY STYLE method");
	        throw new RuntimeException(e);
		}finally{
	        close(preparedStatement);
	        close(resultSet);
		}
	        
		manager.close(conn);
		
		return listByStyle;
	}
	
	// TODO Get related artists
	public List<Related> findRelatedArtist(long idArtist) {
 		
		List<Artist> listRelated = new ArrayList<>();
		
		conn = manager.open(DB_URL);
		resultSet = null;
		
		try {
			
			preparedStatement = conn.prepareStatement(SQL_ARTISTSTYLE);
			preparedStatement.setLong(1, idArtist);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Artist artistFromDB = new Artist();
				artistFromDB.setName(resultSet.getString(1));
				
				listRelated.add(artistFromDB);
			}
		
		} catch (SQLException e) {
			
		}
		
 		return null;
 	}
	
	public void close(PreparedStatement preparedStatement){
		try{
			preparedStatement.close();
		} catch (SQLException e) {
	        System.out.println("SQLException close PreparedStatement method");
	        throw new RuntimeException(e);
		}
	}
	
	public void close(Statement statement){
		try{
			statement.close();
		} catch (SQLException e) {
	        System.out.println("SQLException close Statement method");
	        throw new RuntimeException(e);
		}
	}
		
	public void close(ResultSet resultSet){
		try{
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("SQLException ResultSet method");
	        throw new RuntimeException(e);
		}
	}
         
}
