package com.musify.musify.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface PeopleRepository {

	boolean existsPeople(String name);
	
	void addPeopleToAnArtist(long idPeople, long idMember);
	
	void close(PreparedStatement preparedStatement);
	 
	void close(ResultSet resultSet);
	
}
