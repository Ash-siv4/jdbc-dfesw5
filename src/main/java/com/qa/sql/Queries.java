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

	private Connection conn;
	private Statement stmt;

	// open connection in the constructor - initialise anything
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
		String createStmt = "INSERT INTO mammals(mammal_name, colour, origin) VALUES('" + mName + "','" + col + "','"
				+ orig + "');";
		try {
			stmt.executeUpdate(createStmt);
			System.out.println("Create statement executed");
		} catch (SQLException e) {
			System.out.println("Bad query");
			e.printStackTrace();
		}
	}

	// READ - SELECT .... -> returns info
	public void read() {
		String readStmt = "SELECT * FROM mammals;";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(readStmt);
			while (rs.next()) {
				// the "\n" creates a new line for the text which follows
				// the "\t" tabs the text
				System.out.println("ID:" + rs.getInt("id") 
				+ "\n | MammalName: " + rs.getString("mammal_name")
				+ "\n | Colour: " + rs.getString("colour") 
				+ "\n | Origin: " + rs.getString("origin"));
			}
		} catch (SQLException e) {
			System.out.println("Bad query");
			e.printStackTrace();
		}
	}

	// UPDATE - UPDATE .... -> returns nothing, just say "OK"
	public void update(String updateField, String updateValue, int uId) {
		String updateStmt = "UPDATE mammals SET " + updateField + "='" + updateValue + "' WHERE id=" + uId + ";";
		try {
			stmt.executeUpdate(updateStmt);
			System.out.println("Update statement executed");
		} catch (SQLException e) {
			System.out.println("Bad query");
			e.printStackTrace();
		}
	}

	// DELETE - DELETE .... -> returns nothing, just say "OK"
	public void delete(int id) {
		String delStmt = "DELETE FROM mammals WHERE id=" + id + ";";
		try {
			stmt.executeUpdate(delStmt);
			System.out.println("Delete statement executed");
		} catch (SQLException e) {
			System.out.println("Bad query");
			e.printStackTrace();
		}
	}

	// close the connection
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Closing connection...");
			e.printStackTrace();
		}
	}

}
