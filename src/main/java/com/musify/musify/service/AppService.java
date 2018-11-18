package com.musify.musify.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.musify.musify.connection.ConnectionManager;
import com.musify.musify.connection.DBConnection;

public class AppService {
	
	private static final String DB_URL = "jdbc:h2:~/test"; 
	
	ConnectionManager manager = new DBConnection();
	Connection conn = null;
	
	// Método para crear las tablas, si estas no existen.
	public void createTable(String query){
		Statement statement = null;
		
		try{
			Connection conn = manager.open(DB_URL);
	        statement = conn.createStatement();
	        statement.execute(query);
	        
		}catch (SQLException e) {
	        System.out.println("SQLException createTable method");
	        
		}finally{
	        if(statement != null){
	        	try{
	        		statement.close();
	        		
	        	}catch (SQLException e) {
	                System.out.println("SQLException createTable method");
	                
	        	}
	        }
	        
	        if(conn != null){
	        	try{
	        		conn.close();
	        		
	        	}catch (SQLException e) {
	        		System.out.println("SQLException createTable method");
	        	}
	        }
		}
	}
}
