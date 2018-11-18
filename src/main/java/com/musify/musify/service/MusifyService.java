package com.musify.musify.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface MusifyService {
	
	void close(ResultSet resultSet);
	
	void close(PreparedStatement preparedStatement);

}
