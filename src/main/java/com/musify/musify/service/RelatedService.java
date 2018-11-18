package com.musify.musify.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.musify.musify.connection.ConnectionManager;
import com.musify.musify.connection.DBConnection;
import com.musify.musify.model.Related;

@Repository
public class RelatedService implements MusifyService{
	
	private static final String SQL_INSERTRELATED = "INSERT INTO RELATED(ID_ARTIST, ID_ARTIST_RELATED) values (?, ?)";
	private static final String SQL_DELETERELATED = "DELETE FROM RELATED WHERE ID_ARTIST = ? AND ID_ARTIST_RELATED = ?";
	private static final String DB_URL = "jdbc:h2:~/test";
	// private static final String SQL_UPDATERELATED = "UPDATE RELATED SET ID_ARTIST_RELATED = ? WHERE ID_ARTIST = ?";
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedstatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
	
    // Insert into Related Artist.
    public void addRelated(Related related) {
    	conn = manager.open(DB_URL);
    	
    	try{
			preparedstatement = conn.prepareStatement(SQL_INSERTRELATED);
	        preparedstatement.setLong(1, related.getIdArtist());
	        preparedstatement.setLong(2, related.getIdArtistRelated());
	        
	        preparedstatement.executeUpdate();
	        
		}catch (SQLException e) {
	        System.out.println("SQLException INSERT RELATED method");
	        throw new RuntimeException(e);
	        
		}finally{
	        close(preparedstatement);
		}
    	manager.close(conn);
    }
    
    public void deleteRelated(Related related) {
    	conn = manager.open(DB_URL);
    	
    	try{
			preparedstatement = conn.prepareStatement(SQL_DELETERELATED);
	        preparedstatement.setLong(1, related.getIdArtist());
	        preparedstatement.setLong(2, related.getIdArtistRelated());
	        
	        preparedstatement.executeUpdate();
	        
		}catch (SQLException e) {
	        System.out.println("SQLException DELETE RELATED method");
	        throw new RuntimeException(e);
	        
		}finally{
	        close(preparedstatement);
		}
    	manager.close(conn);
    }
    
    /*public void updateRelated(Related related) {
    	
    }*/
    
    @Override
    public void close(ResultSet resultSet){
		 try{
			 resultSet.close();
		 }catch (SQLException e) {
			 System.out.println("SQLException CLOSE RESULTSET method");
			 throw new RuntimeException(e);
		 }
	 }
	 
    @Override
	 public void close(PreparedStatement preparedStatement){
		 try{
			 preparedStatement.close();
		 }catch (SQLException e) {
			 System.out.println("SQLException CLOSE PREPAREDSTATEMENT method");
		     throw new RuntimeException(e);
		 }
	}

}
