package com.musify.musify.connection;

import java.sql.Connection;

public interface ConnectionManager {
	
	public Connection open(String jdbcUrl);
	
	// public Connection executeSql(Connection conn, String sql);
	
	public void close(Connection conn);

}
