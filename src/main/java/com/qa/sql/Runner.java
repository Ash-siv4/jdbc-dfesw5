package com.qa.sql;

import java.util.Scanner;

public class Runner {

	// SQL -> Structured Query Language (mySQL workbench)
	// Connect to a database -> to interact with it (CRUD -> Create, Read, Update,
	// Delete)

	// JDBC -> Java DataBase Connectivity - is an API which allows us to connect to
	// a database from out Java application
	// - uses drivers to connect to the database and execute queries

	private static Scanner sc = new Scanner(System.in);

	private static String getInput() {
		System.out.println("Enter CRUD choice: ");
		return sc.nextLine();
	}

	public static void main(String[] args) {

//		//----------------------Exceptions
//		int[] arr = { 1, 2, 3 };
//
//		try {
//			System.out.println(arr[3]);
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println(arr[2]);
//		}

		// creating an instance of the Queries class and calling the constructor
		Queries q = new Queries();

		// Ask for user input on CRUD choice and store it in the getInput() method, and
		// now store it in the crud variable
		String crud = getInput();

		// try-finally -> try some code and once it has executed, carry out what the
		// finally block says to do (ideal for closing a connection once the user no
		// longer wants to continue with the application
		try {
			// do-while loop to start the loop
			do {
				// switch-case to match which CRUD to perform
				switch (crud.toLowerCase()) {
				case "create":
					System.out.println("Enter mammal name: ");
					String n = sc.nextLine();
					System.out.println("Enter colour: ");
					String c = sc.nextLine();
					System.out.println("Enter origin: ");
					String o = sc.nextLine();
					q.create(n, c, o);
					break;
				case "read":
					q.read();
					break;
				case "update":
					String update = "";
					System.out.println("Enter the id of the record you wish to update: ");
					int updateId = sc.nextInt();
					sc.nextLine();// to catch the enter key
					System.out.println(
							"Enter the number of the field you would like to update: \n 1 - MammalName \n 2 - Colour \n 3 - Origin");
					int uField = sc.nextInt();
					sc.nextLine();// to catch the enter key
					switch (uField) {
					case 1:
						update = "mammal_name";
						break;
					case 2:
						update = "colour";
						break;
					case 3:
						update = "origin";
						break;
					default:
						System.out.println(
								"Invalid number, please enter 1, 2 or 3 for the field you would like to update");

					}

					System.out.println("Please enter the new value: ");
					String uValue = sc.nextLine();
					q.update(update, uValue, updateId);
					break;
				case "delete":
					System.out.println("Enter id of the mammal you would like to delete: ");
					int delId = sc.nextInt();
					sc.nextLine();// to catch the enter key
					q.delete(delId);
					break;
				default:
					System.out.println("Invalid CRUD choice");
				}
				// Check if the user wants to continue or break out of the loop
				System.out.println("Would you like to continue (y/n)? ");
				String quit = sc.nextLine();
				if (quit.toLowerCase().equals("y")) {
					crud = getInput();
				}
				else if (quit.toLowerCase().equals("n")) {
					crud = "exit";
				} else {
					System.out.println("Please enter 'y' or 'n'");
				}
			} while (!crud.equals("exit"));
			System.out.println("BYEEE");
		} finally {
			q.close();
		}
	}
}
