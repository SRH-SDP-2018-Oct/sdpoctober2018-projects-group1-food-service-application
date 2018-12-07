package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class ReportPage extends CustomerPage {
	@Override
	public void Overview() throws NoSuchAlgorithmException, ParseException, SQLException {
		System.out.println("\n1 - to Return to Homepage\n2 - to show the Paid orders\n3 - to show the Unpaid orders\n4 - to show the Detailed Report");
		Scanner reader = new Scanner(System.in);
		int customerInput = reader.nextInt();
		switch (customerInput) {
		case 1: 
			CustomerMain.customerMainPage();
			break;
		case 2: 
			System.out.println("Welcome to Paid Orders");
			break;
		case 3: 
			System.out.println("Welcome to Unpaid Orders");
			break;
		case 4: 
			System.out.println("Welcome to Detailed Report");
			break;
			
		default: System.out.println("Invalid Input");
		Overview();
		break;
		}
		reader.close();
		
	}

	@Override
	public void Overview(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		
	}
	
	

}
