package com.srh_heidelberg.mealsanddeals;

import java.util.Scanner;
public class CustomerMain {
	private static Customer loggedInCust = new Customer();
	public static void customerLogin(Customer loggedInCustomer) {
		loggedInCust = loggedInCustomer;
		customerMainPage();
	}
	
	public static void customerMainPage() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Meals and Deals. For Navigation\n1 - Calendar\n2 - Report\n3 - Menu");
		int customerInput = reader.nextInt();
		switchMenupage(customerInput);
		reader.close();	
	}
	
public static void switchMenupage(int input){
	
	 int switchMenu = input;
	 String menuString;
	 CalendarPage page = new CalendarPage();
	 ReportPage repPage = new ReportPage();
	 MenuPage menuPage = new MenuPage();
				switch(switchMenu) {
				case 0: 
					customerMainPage();
					break;
				case 1: menuString = "Welcome to Calendar Page";
					System.out.println(menuString);
					page.Overview(loggedInCust);
				break;
				case 2: menuString = "Welcome to Report Page";
				System.out.println(menuString);
				repPage.Overview(loggedInCust);
				break;
				case 3: menuString = "Welcome to MenuPage";
				menuPage.Overview(loggedInCust);
				break;
				default: menuString = "Invalid Input";
				System.out.println(menuString);
				switchMenupage(0);
				break;
				}
}
public void navigation(Customer loggedInCustomer) {
		clearScreen();
		customerMainPage();
}
public void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  

}
