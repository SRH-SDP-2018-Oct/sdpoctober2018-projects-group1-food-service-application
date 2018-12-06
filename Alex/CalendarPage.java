package mealsanddeals.foodservice;

import java.text.ParseException;
import java.util.Scanner;

import com.ibm.icu.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalendarPage extends CustomerPage {
	
	
private Ranking ranking = new Ranking();
private Cal calendar = new Cal();
private Food food = new Food();
private CustomerMain navi = new CustomerMain();
private Order order = new Order();


SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

private List<Food> showFoodList(Date selectedDate) {
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
	Filter filter = new Filter();
	Scanner reader = new Scanner(System.in);
	int index = 0;
	
	for( int i=0; i<filter.AvailableFilters.length;i++) {
		index++;
		System.out.println(filter.AvailableFilters[i]+"["+i+"]"+"\n");
		
		

	}
	System.out.println("----------------------------------");
	
	String customerInput = reader.nextLine();
	
	filter.checkCategory(customerInput);
	
	
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
		selectFilters();
		//showFilteredFoodList(foodFilters);
		//selectFood();
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
public void Overview() {
	System.out.println("\nPress [1] to Return to Homepage\nPress [2] to show the Calendar");
	Scanner reader = new Scanner(System.in);
	int customerInput = reader.nextInt();
	switch (customerInput) {
	case 1: navi.navigation();
	break;
	case 2:
	break;
	default: System.out.println("Invalid Input");
	Overview();
	break;
	}
	reader.close();
	
}
}
