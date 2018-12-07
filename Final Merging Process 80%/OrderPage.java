package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class OrderPage extends CustomerPage {
	private Order order = new Order();
	private static Customer loggedInCust = new Customer();

	@Override
	public void Overview() throws NoSuchAlgorithmException, ParseException, SQLException {
		
		System.out.println("Welcome to ----> Order Page <----\n1: Return to the Main Page\n2: Continue with your order");
		Scanner reader = new Scanner(System.in);
		int customerInput = reader.nextInt();
		switchMenupage(customerInput);
		reader.close();
		
	}

	@Override
	public void switchMenupage(int input) throws NoSuchAlgorithmException, ParseException, SQLException {
		int switchMenu = input;
		switch(switchMenu) {
		case 1: CustomerMain custMain = new CustomerMain();
		custMain.customerLogin(loggedInCust);
		break;
		case 2:
			order.showOpenOrders(loggedInCust.getCustomerusername());
		}
		
	}

	@Override
	public void Overview(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
