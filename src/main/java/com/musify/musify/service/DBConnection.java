package com.musify.musify.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.musify.musify.model.People;
import com.musify.musify.model.Style;
import com.musify.musify.repository.ArtistRepository;
import com.musify.musify.repository.PeopleRepositoryImpl;

public class DBConnection implements ConnectionManager{
	
	private static final String JDBC_DRIVER = "org.h2.Driver";   
	// TODO Check connection method.
	private static final String DB_URL = "jdbc:h2:~/test";  
	
	private static final String USERNAME = "sa"; 
	private static final String PASSWORD = "";
	
	// TODO Delete!
	public static void main(String[] args) {		
		PeopleRepositoryImpl repo = new PeopleRepositoryImpl();
		ArtistRepository repo2 = new ArtistRepository();
		System.out.println(repo2.findArtistByStyle(1));
	}
	
	// TODO Delete!
	public static void insertStyle(Style style){
		PreparedStatement preparedstatement = null;
		ConnectionManager manager = new DBConnection(); 

		Connection conn = manager.open(DB_URL);
		try{
	            preparedstatement = conn.prepareStatement("insert into style(name) values (?)");
	            // preparedstatement.setString(1, style.getName());
	            preparedstatement.setString(1, "Rock");
	            preparedstatement.executeUpdate();
		}catch (SQLException e) {
	            System.out.println("SQLException createTable method");
	            throw new RuntimeException(e);
		}finally{
	            // close(preparedstatement);
		}
	        manager.close(conn);
	    }
	
	@Override
	public Connection open(String jdbcUrl) {
		 Connection conn = null;
		 
         try {
             Class.forName(JDBC_DRIVER);
             conn = DriverManager.getConnection(jdbcUrl, USERNAME, PASSWORD);
         } catch (ClassNotFoundException | SQLException e) {
        	 throw new RuntimeException(e);
         }
         
	     return conn;
	}
	
	@Override
	public Connection executeSql(Connection conn, String sql) {
		PreparedStatement prepareStatement;
        
		try {
        	prepareStatement = conn.prepareStatement(sql);
			prepareStatement.execute(sql);
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
		
		return conn;
	}
	
	@Override
	public void close(Connection conn) {
		
		try {
			conn.close();
		} catch (SQLException e) {
	       throw new RuntimeException(e);
		}
		
	}

}
