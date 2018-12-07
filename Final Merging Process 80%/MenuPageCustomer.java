package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class MenuPageCustomer {
	private static Customer loggedInCust = new Customer();
	public static void decisioner(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException {
		loggedInCust = loggedInCustomer;
		Overview();
	}
	

	public static void Overview() throws NoSuchAlgorithmException, ParseException, SQLException{
		System.out.println("\n1: to Return to Homepage\n2: Profile\n3: Messages\n4: Logout");
		Scanner reader = new Scanner(System.in);
		int customerInput = reader.nextInt();
		switch (customerInput) {
		case 1: CustomerMain.navigation(loggedInCust);
		break;
		case 2: 
			ProfileCustomer.decisioner(loggedInCust);
		break;
		case 3: 
			NotificationPage.notificationPageCustomer(loggedInCust);
		break;
		case 4: try {
				MealsandDeals.main(null);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		break;
		default: System.out.println("Invalid Input");
		Overview();
		break;
		}
		reader.close();
		

	
}
}
