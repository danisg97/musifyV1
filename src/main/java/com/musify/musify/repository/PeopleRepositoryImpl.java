package com.musify.musify.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.musify.musify.service.ConnectionManager;
import com.musify.musify.service.DBConnection;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository{

	ConnectionManager manager = new DBConnection();
    Connection conn = null;
    
    PreparedStatement preparedStatement = null;
    
	private static final String DB_URL = "jdbc:h2:~/test";
	
    private static final String SQL_SEARCHPEOPLE = "SELECT NAME FROM PEOPLE WHERE NAME = ?";
    private static final String SQL_INSERTPEOPLEARTISTS = "UPDATE PEOPLE SET MEMBER = ? WHERE ID = ?";
	
    // Check if people exits.
    @Override
	public boolean existsPeople(String name){
		
		ResultSet resultSet = null;
		conn = manager.open(DB_URL);
		
		boolean isE = false;
		
		try{	
			preparedStatement = conn.prepareStatement(SQL_SEARCHPEOPLE);
	        preparedStatement.setString(1, name);
	        resultSet = preparedStatement.executeQuery();
	        
	        if(resultSet.next()){
	        	isE = true;
		        System.out.println("El usuario existe!");
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
	
	// Add a new Member to an Artist.
    @Override
	public void addPeopleToAnArtist(long idPeople, long idMember) {
		
		conn = manager.open(DB_URL);
			
		try{
			preparedStatement = conn.prepareStatement(SQL_INSERTPEOPLEARTISTS);
		    preparedStatement.setLong(1, idMember);
		    preparedStatement.setLong(2, idPeople);
		    preparedStatement.executeUpdate();
		}catch (SQLException e) {
			System.out.println("SQLException ADDING NEW MEMBER method");
		    throw new RuntimeException(e);
		}finally{
			close(preparedStatement);
		}
			 
		manager.close(conn);
	
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
	public void close(ResultSet resultSet){
		try{
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("SQLException ResultSet method");
	        throw new RuntimeException(e);
		}
	}
	
}
