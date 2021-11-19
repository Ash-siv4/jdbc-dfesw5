package com.qa.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
	// To carry out the CRUD queries, we first need to access the database
	// Four main things to do:
	// - connection
	// - Write your query (create a statement)
	// - Execute query
	// - Close connection

	// Connect to database, we need: url, username, password
	// static - only one instance of it
	// final - fixed
//	public static final String URL = "jdbc:mysql://localhost:3306/animals";
//	public static final String USER = "root";
//	public static final String PASS = "root";

	private Connection conn;
	private Statement stmt;

	// open connection in the constructor - intialise anything
	public Queries() {
		// Exceptions -> Abnormal conditions which affect the flow of the application
		// but can be handled using try-catch blocks
		// Try-catch block -> try{code}catch(exception){do this instead}
		try {
			conn = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
			this.stmt = conn.createStatement(); // create a statement object to execute sql queries
			System.out.println("Connection Successful!!");
		} catch (SQLException e) {
			System.out.println("Incorrect credentials");
			e.printStackTrace();
		}
	}

	// Need a way to run queries - call these methods from the Statement class:
	// - executeQuery -> retrieving info -> READ
	// - executeUpdate -> passing info through, return nothing -> CREATE, UPDATE,
	// DELETE

	// CREATE - INSERT INTO .... -> returns nothing, just say "OK"
	public void create(String mName, String col, String orig) {
		String createStmt = "INSERT INTO mammals(mammal_name, colour, origin) VALUES('" + mName + "','" + col + "','"+ orig + "');";
		try {
			stmt.executeUpdate(createStmt);
			System.out.println("Statement executed");
		} catch (SQLException e) {
			System.out.println("Bad query");
			e.printStackTrace();
		}
	}
	
	// READ - SELECT .... -> returns info
		public void read() {
			String read = "SELECT * FROM mammals;";
			ResultSet rs;
			
			try {
				rs = stmt.executeQuery(read);
				while(rs.next()) {
					System.out.println("ID:" + rs.getInt("id") + ", Mammal Name: "+ rs.getString("mammal_name")+ ", Colour: "+ rs.getString("colour"));
				}
				
				
			} catch (SQLException e) {
				System.out.println("Bad query");
				e.printStackTrace();
			}
			
		}
	
	

}
