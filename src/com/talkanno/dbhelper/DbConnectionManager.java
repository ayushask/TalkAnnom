/*package com.talkanno.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnectionManager {

	public static Connection getConnection() {
		Connection con= null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			 con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/annodb","root","root");
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("Can't connect to db");
		}
		return con;

	}
	public static void main(String[] args) {
		getConnection();
	}
}*/