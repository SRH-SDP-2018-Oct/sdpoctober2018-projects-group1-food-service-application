package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
import com.srh_heidelberg.mealsanddeals.MysqlCon;

/*
 * Home page for Admin in order to validate Food Service Agents account 
 * Calling Methods to fetch Data from database
 * Logout 
 */
public class AdminHomePage {
    public static void homePage() throws NoSuchAlgorithmException, SQLException{
    	
    	System.out.println("1 - Validation [1]");
    	System.out.println("2 - Logout [0]");
    	Scanner input = new Scanner(System.in);
    	String option = input.nextLine();
    	switch (option) {

    	case "1" : {
    		MysqlCon.inactiveUsers();
    		break;
    	}
 
    	case "0" : {
    		MealsandDeals.main(null);
    		break;
    	}
    	}
    	
    	
    	
    	
    	input.close();
    }
}
