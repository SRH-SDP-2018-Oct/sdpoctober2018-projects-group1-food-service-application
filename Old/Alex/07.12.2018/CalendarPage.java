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

public class CalendarPage extends CustomerPageNavigation {


private void optionsSelectedDate() {
	System.out.println("If you filter the available food press [1]\n if you want to select another date press [2]");
	Scanner reader = new Scanner(System.in);
	int customerInput = reader.nextInt();
	
	switch(customerInput) {
	case 1:
		break;
	case 2:
		Overview();
		break;
	}
	
	//reader.close();
}

@Override
public void Overview() {
	Scanner reader = new Scanner(System.in);
	System.out.println("Welcome to ---->CALENDAR PAGE<----");
	System.out.println("\nPress [0] to Return to Homepage\nPress [1] if you want to order food");
	
	int customerInput = reader.nextInt();
	switchMenupage(customerInput);
	
	//reader.close();
	
}

@Override
public void switchMenupage(int input) {
	
	int switchMenu = input;
	switch (switchMenu) {
	case 0: 
		CustomerMain mainpage = new CustomerMain();
		mainpage.main();
	break;
	case 1:
		Cal calendar = new Cal();
		
		
		
		try {
			calendar.ShowCalendar();
			RankingPage rankingPage = new RankingPage();
			rankingPage.Overview();
			OrderPage orderPage = new OrderPage();
			orderPage.Overview();
		} catch (ParseException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	break;
	default: Overview();
	break;
	}
}
}
