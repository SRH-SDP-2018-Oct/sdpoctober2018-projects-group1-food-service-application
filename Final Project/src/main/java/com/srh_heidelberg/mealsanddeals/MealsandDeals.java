package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

/*
 * Home Page for Food Service Application and some functional input
 * Also calling Methods from other classes in order to insert data to database and fetch data from database and Email Confirmation 
 * Also redirection to pages like Admin, Food Service Agent and Customer
 */
public class MealsandDeals {
	
    public static void main( String args[] ) throws NoSuchAlgorithmException, SQLException{
    	
    	System.out.println("        <-- Meals and Deals -->       ");
    	System.out.println("        -----------------------       ");
    	System.out.println("                                      ");
    	System.out.println("                                      ");
    	System.out.println(" Welcome to Infinite Ways of Eating!! ");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("1: Login ");
    	System.out.println("2: Registeration ");
    	System.out.println("3: Exit ");
    	
    	Scanner input = new Scanner(System.in);
    	String option = null;
    	option = input.nextLine();
    	switch (option) {
    	case "1":{
    		LoginAction.login();
    		break;
    	}
    	case "2":{
    		String emailId = null;
        	System.out.println("Please Enter Your Email Address :");
    		emailId = input.nextLine();
    		MysqlCon.registeringCheck(emailId);
    		break;
    	}
    	case "3":{
    		System.exit(0);
    		break;
    	}
    	}
    	input.close();
    }
}
