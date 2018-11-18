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
import com.musify.musify.model.People;
import com.musify.musify.repository.PeopleRepositoryImpl;

@Service
public class PeopleService implements MusifyService{
	
	private static final String DB_URL = "jdbc:h2:~/test";
	private static final String SQL_INSERTPEOPLE = "INSERT INTO PEOPLE(NAME, YEARS) values (?, ?)";
	private static final String SQL_PEOPLE = "SELECT ID, NAME, YEARS FROM PEOPLE";
	
	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedstatement = null;
	
    @Autowired
	PeopleRepositoryImpl peopleRepo;
	
	public void addPeople(People people) {
		conn = manager.open(DB_URL);
				
		// Comprobamos si la persona existe.
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
	
	// Listado de Personas.
	public List<People> allPeople() {
		List<People> lista = new ArrayList<People>();
	    conn = manager.open(DB_URL);
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	        
	    try {
	    	preparedStatement = conn.prepareStatement(SQL_PEOPLE);
	        resultSet = preparedStatement.executeQuery();
	        
	        while(resultSet.next()) {
	        	People people = new People(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
	            lista.add(people);
	                        
	        }
	    }catch (SQLException e) {
	    	System.out.println("SQLException LISTA PEOPLE method");
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
