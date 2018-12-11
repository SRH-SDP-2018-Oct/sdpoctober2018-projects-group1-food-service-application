package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderPage extends CustomerPageNavigation {
	private FoodOption foodOption = new FoodOption();
	private Order order = new Order();
	private FoodFiltering filter = new FoodFiltering();
	private static Customer loggedInCust = new Customer();
	private ArrayList<Order> orderlist = new ArrayList<Order>();
	@Override
	public void Overview() {
		
		
		//reader.close();
		
	}
	
	public void Overview(Date selectedDate, Customer loggedInCustomer) throws NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, ParseException, SQLException {
		loggedInCust = loggedInCustomer;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String query = "select * from food where available!='0' and date='"+dateFormat.format(selectedDate)+"'";
		

		try {
			foodOption.PrintFoodListCustomer(query,selectedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("-------------\n1: Return to the Main Page\n2: Filter your food\n3: Add a New Order\n4: Select a Current Order");
		Scanner reader = new Scanner(System.in);
		
		int customerInput = reader.nextInt();
		switchMenupage(customerInput,selectedDate);
	}
	
	public void switchMenupage(int input, Date selectedDate) throws NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, ParseException, SQLException {
		int switchMenu = input;
		Scanner reader = new Scanner(System.in);
		switch(switchMenu) {
		case 1: CustomerMain custMain = new CustomerMain();
		custMain.customerLogin(loggedInCust);
		break;
		case 2:
			//filter.
			break;
		case 3:
			order.orderHandlerAddOrder(loggedInCust, selectedDate);
			Overview(selectedDate, loggedInCust);
			break;
		case 4:
			order.selectOrder(loggedInCust);
			Overview(selectedDate, loggedInCust);
			break;
		default:
		}
	}

	@Override
	public void switchMenupage(int input) {
		
		
	}

}
