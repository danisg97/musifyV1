package com.musify.musify.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.musify.musify.model.People;
import com.musify.musify.service.ConnectionManager;
import com.musify.musify.service.DBConnection;

public class PeopleRepository {

	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedStatement = null;
    
	private static final String DB_URL = "jdbc:h2:~/test";
	
    private static final String SQL_SEARCHPEOPLE = "SELECT NAME FROM PEOPLE WHERE NAME = '?'";
	
    // TODO Revisar y ejecutar bien este metodo.
	public Boolean existsPeople(String name){
		
		People peopleFromDB = null;
		ResultSet resultSet = null;
		conn = manager.open(DB_URL);
		
		boolean isE = false;
		
		try{	
			preparedStatement = conn.prepareStatement(SQL_SEARCHPEOPLE);
	        preparedStatement.setString(1, name);
	        resultSet = preparedStatement.executeQuery();
	        while(resultSet.next()){
				peopleFromDB = new People();
				peopleFromDB.setName(resultSet.getString(1));
	        }
	        
	        if(resultSet != null) {
	        	isE = true;
	        	System.out.println("El usuario Existe!");
	        }
	        
		}catch(SQLException e){
			System.out.println("SQLException EXIST PEOPLE method");
	        throw new RuntimeException(e);
		}finally{
	        close(preparedStatement);
	        close(resultSet);
		}
	        
		manager.close(conn);
		
		return isE;
	}
	
	public void close(PreparedStatement preparedStatement){
		try{
			preparedStatement.close();
		} catch (SQLException e) {
	        System.out.println("SQLException close PreparedStatement method");
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
