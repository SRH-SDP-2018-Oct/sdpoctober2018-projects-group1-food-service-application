package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
public class CustomerMain {
	private static Customer loggedInCust = new Customer();
	private static Order order = new Order();
	private static Ranking ranking = new Ranking();
	public static void customerLogin(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException {
		loggedInCust = loggedInCustomer;
		customerMainPage();
	}
	
	public static void customerMainPage() throws NoSuchAlgorithmException, ParseException, SQLException {
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Meals and Deals. For Navigation\n1: Calendar\n2: Report\n3: Menu");
		int customerInput = reader.nextInt();
		switchMenupage(customerInput);
		reader.close();	
	}
	
public static void switchMenupage(int input) throws NoSuchAlgorithmException, ParseException, SQLException{
	
	 int switchMenu = input;
	 String menuString;
	 CalendarPage page = new CalendarPage();
	 ReportPage repPage = new ReportPage();
				switch(switchMenu) {
				case 0: 
					customerMainPage();
					break;
				case 1:menuString = "Welcome to Calendar Page";
				System.out.println(menuString);
//				order.selectOrdersFromUser("jisu123"); //Make this general
//				ranking.rankOrders(order.CustomerUnrankedOrders);
				page.Overview();
				break;
				case 2: menuString = "Welcome to Report Page";
				System.out.println(menuString);
				repPage.Overview(loggedInCust);
				break;
				case 3: menuString = "Welcome to MenuPage";
				MenuPageCustomer.decisioner(loggedInCust);
				break;
				default: menuString = "Invalid Input";
				System.out.println(menuString);
				switchMenupage(0);
				break;
				}
}
public static void navigation(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException {
		clearScreen();
		customerMainPage();
}
public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  

}
