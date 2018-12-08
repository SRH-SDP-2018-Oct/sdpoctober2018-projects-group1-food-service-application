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
    	
    	System.out.println("1: Agents that have Both Certificates validated");
    	System.out.println("2: Agents without Cooking Certificate");
    	System.out.println("3: Agents without Business Certificate");
    	System.out.println("4: Agents that have no Certificate");
    	System.out.println("5: Logout");
    	Scanner input = new Scanner(System.in);
    	String option = input.nextLine();
    	switch (option) {

    	case "1" : {
    		MysqlCon.inactiveUsers();
    		break;
    	}
 
    	case "2" : {
    		MysqlCon.inactiveUsersWithoutCookingCert();
    		break;
    	}
 
    	case "3" : {
    		MysqlCon.inactiveUsersWithoutBusinessCert();
    		break;
    	}
 
    	case "4" : {
    		MysqlCon.inactiveUsersWithoutCertificate();
    		break;
    	}
 
    	case "5" : {
    		MealsandDeals.main(null);
    		break;
    	}
    	}
    	
    	
    	
    	
    	input.close();
    }
}
