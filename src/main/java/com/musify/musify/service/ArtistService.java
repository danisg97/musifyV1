package com.musify.musify.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musify.musify.connection.ConnectionManager;
import com.musify.musify.connection.DBConnection;
import com.musify.musify.model.Artist;
import com.musify.musify.repository.ArtistRepositoryImpl;

@Service
public class ArtistService implements MusifyService{
	
	private static final String DB_URL = "jdbc:h2:~/test";  
	private static final String SQL_INSERTARTIST = "INSERT INTO ARTIST(NAME, YEAR) values (?, ?)";
	private static final String SQL_ARTIST = "SELECT ID, NAME, YEAR FROM ARTIST";
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    PreparedStatement preparedstatement = null;
    
    @Autowired
    ArtistRepositoryImpl artistRepo;
	
	public void addArtist(Artist artist) {
		conn = manager.open(DB_URL);
		
		// Comprobamos que el artista no exita en la DB.
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
		}else {
			System.out.println("El artista ya existe en la DB!");
		}
	        manager.close(conn);
	}

	// Listado de artistas.
	public List<Artist> allArtist() {
		 List<Artist> lista = new ArrayList<Artist>();
	        conn = manager.open(DB_URL);
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        
	        try {
	            preparedStatement = conn.prepareStatement(SQL_ARTIST);
	            resultSet = preparedStatement.executeQuery();
	            
	            while(resultSet.next()) {
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
	
	// Cerramos el ResultSet.
	@Override
	public void close(ResultSet resultSet){
		try{
			resultSet.close();
		}catch (SQLException e) {
			System.out.println("SQLException CLOSE RESULTSET method");
			throw new RuntimeException(e);
		}
	 }
	 
	// Cerramos el PreparedStatement.
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
