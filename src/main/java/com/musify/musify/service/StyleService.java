package com.musify.musify.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.musify.musify.model.Style;

@Service
public class StyleService {
	
	private static final String DB_URL = "jdbc:h2:~/test"; 
	private static final String SQL_STYLE = "SELECT ID, NAME FROM STYLE";

	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedstatement = null;
    
    public List<Style> allStyles() {
    	List<Style> lista = new ArrayList<Style>();
		 
	    conn = manager.open(DB_URL);
	        
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	        
	    try {
	    	preparedStatement = conn.prepareStatement(SQL_STYLE);
	        resultSet = preparedStatement.executeQuery();
	        while(resultSet.next()) {
	        	Style style = new Style(resultSet.getLong(1), resultSet.getString(2));
	            lista.add(style);
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
