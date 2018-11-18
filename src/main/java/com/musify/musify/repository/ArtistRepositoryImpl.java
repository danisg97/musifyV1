package com.musify.musify.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.musify.musify.connection.ConnectionManager;
import com.musify.musify.connection.DBConnection;
import com.musify.musify.model.Artist;
import com.musify.musify.model.Related;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository{
	
	private static final String DB_URL = "jdbc:h2:~/test";
	private static final String SQL_ARTISTSTYLE = "SELECT ARTIST.NAME, ARTIST.YEAR FROM ARTIST INNER JOIN STYLES ON STYLES.ID_STYLE = ? AND ARTIST.ID = STYLES.ID_ARTIST";
	private static final String SQL_ARTISTRELATED = "SELECT ARTIST.ID, ARTIST.NAME FROM ARTIST INNER JOIN RELATED ON RELATED.ID_ARTIST = ? AND ARTIST.ID = RELATED.ID_ARTIST_RELATED";
	private static final String SQL_SEARCHARTIST = "SELECT NAME FROM ARTIST WHERE NAME = ?";
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    @Override
    public boolean existsArtist(String name){
		
		ResultSet resultSet = null;
		conn = manager.open(DB_URL);
		
		boolean isE = false;
		
		try{	
			preparedStatement = conn.prepareStatement(SQL_SEARCHARTIST);
	        preparedStatement.setString(1, name);
	        resultSet = preparedStatement.executeQuery();
	        
	        if(resultSet.next()){
	        	isE = true;
		        System.out.println("El artista existe!");
	        }
	        
		}catch(SQLException e){
			System.out.println("SQLException EXIST ARTIST method");
	        throw new RuntimeException(e);
		}finally{
	        close(preparedStatement);
	        close(resultSet);
		}
	        
		manager.close(conn);
		
		return isE;
	}
        
    // List artist by style.
    @Override
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
				artistFromDB.setYear(resultSet.getInt(2));
				
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
	
    @Override
	public List<Artist> findRelatedArtist(long idArtist) {
 		
		List<Artist> listRelated = new ArrayList<>();
		
		conn = manager.open(DB_URL);
		resultSet = null;
		
		try {
			
			preparedStatement = conn.prepareStatement(SQL_ARTISTRELATED);
			preparedStatement.setLong(1, idArtist);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Artist artistFromDB = new Artist();
				artistFromDB.setId(Long.parseLong(resultSet.getString(1)));
				artistFromDB.setName(resultSet.getString(2));
				
				listRelated.add(artistFromDB);
			}
		
		} catch (SQLException e) {
			System.out.println("SQLException FIND RELATED ARTIST method");
	        throw new RuntimeException(e);
		}finally{
	        close(preparedStatement);
	        close(resultSet);
		}
	        
		manager.close(conn);
		
 		return listRelated;
 	}
	
    @Override
	public void close(PreparedStatement preparedStatement){
		try{
			preparedStatement.close();
		} catch (SQLException e) {
	        System.out.println("SQLException close PreparedStatement method");
	        throw new RuntimeException(e);
		}
	}
	
    @Override
	public void close(Statement statement){
		try{
			statement.close();
		} catch (SQLException e) {
	        System.out.println("SQLException close Statement method");
	        throw new RuntimeException(e);
		}
	}
		
    @Override
	public void close(ResultSet resultSet){
		try{
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("SQLException ResultSet method");
	        throw new RuntimeException(e);
		}
	}
         
}
