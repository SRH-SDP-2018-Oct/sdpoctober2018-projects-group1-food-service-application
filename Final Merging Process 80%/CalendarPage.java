package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CalendarPage extends CustomerPage {
private Food food = new Food();
private static Customer loggedInCust = new Customer();

private List<Food> showFoodList() {
	List<Food> availableFoods = new ArrayList<Food>();
	for (int i = 0; i<=10;i++) {
		availableFoods.add(food);
		System.out.println("Available foods:\n");
		System.out.println("Food Name:" + food.getFoodname()+"\n Food Type:" + food.getFoodtype()+"\nFSA Name:" +food.getFasusername());
	}
	//TODO: Database Connection with Food Table. Then Select all the available foods on that day

return availableFoods;
}

private void optionsSelectedDate() {
	System.out.println("If you filter the available food press [1]\n if you want to select another date press [2]");
	Scanner reader = new Scanner(System.in);
	int customerInput = reader.nextInt();
	
	switch(customerInput) {
	case 1:
		//selectFilters();
		//showFilteredFoodList(foodFilters);
		selectFood();
		break;
	case 2:
		Overview();
		break;
	}
	
	reader.close();
}

private void selectFood() {
	System.out.println("If you want to select food just press the fitting number for it");
}

@Override
public void Overview(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException {
	loggedInCust = loggedInCustomer;
	System.out.println("\n1 - to Return to Homepage\n2 - to show the Calendar");
	Scanner reader = new Scanner(System.in);
	int customerInput = reader.nextInt();
	switch (customerInput) {
	case 1: CustomerMain.navigation(loggedInCust);
	break;
	case 2: try {
			Cal.ShowCalendar();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			showFoodList();
			optionsSelectedDate();
	break;
	default: System.out.println("Invalid Input");
	Overview();
	break;
	}
	
}

@Override
public void Overview() {
	
}
}
