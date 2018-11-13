package com.musify.musify.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.musify.musify.model.People;
import com.musify.musify.repository.PeopleRepository;

public class PeopleService {
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedstatement = null;
    
	private static final String DB_URL = "jdbc:h2:~/test";
	
	// private static final String SQL_CREATEPEOPLE = "CREATE TABLE PEOPLE (ID INTEGER NOT NULL AUTO_INCREMENT, NAME VARCHAR(40) NOT NULL, YEARS INTEGER, PRIMARY KEY (ID))";
	private static final String SQL_INSERTPEOPLE = "INSERT INTO PEOPLE(NAME, YEARS) values (?, ?)";
	
	private PeopleRepository peopleRepo = new PeopleRepository();
	
	public void addPeople(People people) {
		
		conn = manager.open(DB_URL);
		
		// Comprobamos que el usuario no existe en la DB.
		if(!peopleRepo.existsPeople(people.getName())) {
			
			try{
				preparedstatement = conn.prepareStatement(SQL_INSERTPEOPLE);
		        preparedstatement.setString(1, people.getName());
		        preparedstatement.setInt(2, people.getYears());
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
