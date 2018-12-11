package com.srh_heidelberg.mealsanddeals;

import java.text.ParseException;
import java.util.Scanner;

import com.ibm.icu.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalendarPage extends CustomerPage {

	private static Customer loggedInCust = new Customer();


@Override
public void Overview(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
	loggedInCust = loggedInCustomer;
	Scanner reader = new Scanner(System.in);
	System.out.println("Welcome to ---->CALENDAR PAGE<----");
	System.out.println("\n1: Return to Homepage\n2: Order Food");
	
	int customerInput = reader.nextInt();
	switchMenupage(customerInput);
	
	//reader.close();
	
}

@Override
public void switchMenupage(int input) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
	
	int switchMenu = input;
	switch (switchMenu) {
	case 1: 
		CustomerMain.customerLogin(loggedInCust);
	break;
	case 2:
		Cal calendar = new Cal();

		try {
			calendar.ShowCalendar();
			RankingPage rankingPage = new RankingPage();
			rankingPage.Overview(loggedInCust);
			OrderPage orderPage = new OrderPage();
			orderPage.Overview(calendar.getDate(),loggedInCust);
		} catch (ParseException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	break;
	default: Overview();
	break;
	}
}

@Override
public void Overview() {
	// TODO Auto-generated method stub
	
}
}
