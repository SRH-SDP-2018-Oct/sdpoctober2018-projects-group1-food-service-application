package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
public class CustomerMain {
	private static Customer loggedInCust = new Customer();
	private static Order order = new Order();
	private static Ranking ranking = new Ranking();
	
	public static void customerLogin(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		loggedInCust = loggedInCustomer;
		System.out.println(loggedInCust.getName().toString());
		customerMainPage();
	}
	
	
	
	public static void customerMainPage() throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Meals and Deals. For Navigation\n1: Calendar\n2: Report\n3: Menu");
		int customerInput = reader.nextInt();
		switchMenupage(customerInput);
//		reader.close();	
	}
	
public static void switchMenupage(int input) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
	
	 int switchMenu = input;
	 CalendarPage calendarpage = new CalendarPage();
	 ReportPage reportPage = new ReportPage();
				switch(switchMenu) {
				case 0: 
					customerMainPage();
					break;
				case 1:
					System.out.println(loggedInCust.getName().toString());
					calendarpage.Overview(loggedInCust);
					
				break;
				case 2: 
					System.out.println(loggedInCust.getName().toString());
					reportPage.Overview(loggedInCust);
					
				break;
				case 3: 
					System.out.println(loggedInCust.getName().toString());
					MenuPageCustomer.decisioner(loggedInCust);
					
				break;
				default:
					switchMenupage(0);
				break;
				}
}
public static void navigation(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		clearScreen();
		customerMainPage();
}
public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  

}
