package com.musify.musify.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements ConnectionManager{
	
	private static final String JDBC_DRIVER = "org.h2.Driver";   
	private static final String USERNAME = "sa"; 
	private static final String PASSWORD = "";

	// private static final String DB_URL = "jdbc:h2:~/test";  
	
	// Conectamos con la DB.
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
	
	//  Cerramos la conexión con la DB.
	@Override
	public void close(Connection conn) {
		try {
			conn.close();
			
		} catch (SQLException e) {
	       throw new RuntimeException(e);
		}
		
	}

}
