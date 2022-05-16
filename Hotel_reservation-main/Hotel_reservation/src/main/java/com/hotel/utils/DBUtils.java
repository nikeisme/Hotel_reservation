package com.hotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	//MySQL 서버 URL 및 메타데이터 
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE_NAME = "hotel_db";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";
	
	//JAVA와 MySQL 연결
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASSWORD);
		return connection;
	}
}
