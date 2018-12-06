package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class MenuPage {
	public static void main() {
	}
	private CustomerMain navi = new CustomerMain();

	public void Overview(Customer loggedInCustomer){
		System.out.println("\n1 - to Return to Homepage\n2 - Profile\n3 - Messages\n4 - Logout");
		Scanner reader = new Scanner(System.in);
		int customerInput = reader.nextInt();
		switch (customerInput) {
		case 1: navi.navigation(loggedInCustomer);
		break;
		case 2: 
//				profile();
		break;
		case 3: //Messages();
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
		Overview(loggedInCustomer);
		break;
		}
		reader.close();
		

	
}
}
