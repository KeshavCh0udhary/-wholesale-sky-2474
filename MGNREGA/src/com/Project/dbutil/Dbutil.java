package com.Project.dbutil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutil {
	
	public static Connection provideConnection() {

        Connection conn=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:mysql://localhost:3306/NewProject";
		
		try {
			conn= DriverManager.getConnection(url,"root","@Keshav9#");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
