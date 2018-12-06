package com.srh_heidelberg.mealsanddeals;

import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CalendarPage extends CustomerPage {
private Ranking ranking = new Ranking();
private Cal calendar = new Cal();
private Food food = new Food();
private CustomerMain navi = new CustomerMain();
private static Customer loggedInCust = new Customer();

private void Rank() {
}

private List<Food> showFoodList() {
	List<Food> availableFoods = new ArrayList();
	for (int i = 0; i<=10;i++) {
		availableFoods.add(food);
		System.out.println("Available foods:\n");
		System.out.println("Food Name:" + food.getFoodname()+"\n Food Type:" + food.getFoodtype()+"\nFSA Name:" +food.getFasusername());
	}
	//TODO: Database Connection with Food Table. Then Select all the available foods on that day

return availableFoods;
}

private void selectFilters() {
	System.out.println("Select filters for food");
}

private void showFilteredFoodList(String[] selectedFilters) {
	System.out.println("Food List got filtered. To continue press [1]");
	int i = 0;
	while(i<selectedFilters.length) {
		System.out.println("You selected" +selectedFilters[i].toString()+" as a Filter");	
		i++;
	}
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
public void Overview(Customer loggedInCustomer) {
	loggedInCust = loggedInCustomer;
	System.out.println("\n1 - to Return to Homepage\n2 - to show the Calendar");
	Scanner reader = new Scanner(System.in);
	int customerInput = reader.nextInt();
	switch (customerInput) {
	case 1: navi.navigation(loggedInCust);
	break;
	case 2: try {
			calendar.ShowCalendar();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			showFoodList();
			optionsSelectedDate();
	break;
	default: System.out.println("Invalid Input");
	Overview();
	break;
	}
	reader.close();
	
}

@Override
public void Overview() {
	// TODO Auto-generated method stub
	
}
}
