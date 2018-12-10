package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;
/*
 * Home Page for Food Service Application and some functional input
 * Also calling Methods from other classes in order to insert data to database and fetch data from database and Email Confirmation 
 * Also redirection to pages like Admin, Food Service Agent and Customer
 */
public class FoodFiltering {
   
	public static void main(String[] args) throws NoSuchAlgorithmException, SQLException{
		mainFoodFiltering();
	}
	
	public static void mainFoodFiltering() throws NoSuchAlgorithmException, SQLException{
    	
    	System.out.println(" 1: Food Type");
    	System.out.println(" 2: Breakfast");
    	System.out.println(" 3: Lunch");
    	System.out.println(" 4: Dinner");
    	System.out.println(" 5: Hot");
    	System.out.println(" 6: Cold");
    	System.out.println(" 7: With Delivery option");
    	System.out.println(" 8: Without Delivery option");
    	System.out.println(" 9: Cash With Online Payment option");
    	System.out.println("10: Cash Without Online Payment option");
    	System.out.println("11: Apply Filters");
    	System.out.println("12: Back");
    	foodFiltering();
    }
	public static ArrayList<String> foodtypeFilter = new ArrayList<String>();
	public static ArrayList<String> nameofmealFilter = new ArrayList<String>();
	public static ArrayList<String> hotorcoldFilter = new ArrayList<String>();
	public static ArrayList<String> deliveryFilter = new ArrayList<String>();
	public static ArrayList<String> onlineFilter = new ArrayList<String>();
	public static String foodtype = "foodtype";
	public static String nameofmeal = "nameofmeal";
	public static String hotorcold = "hotorcold";
	public static String delivery = "deliverytype";
	public static String online = "online";


	
    public static void foodFiltering() throws NoSuchAlgorithmException, SQLException {
    	Scanner input = new Scanner(System.in);
    	String option = null;
    	option = input.nextLine();
    	switch (option) {
    	case "1":{
    		FoodTypeFilter.foodTypeFilter();
    		break;
    	}
    	case "2":{
    		nameofmealFilter.add("Breakfast");
    		mainFoodFiltering();
    		break;
    	}
    	case "3":{
    		nameofmealFilter.add("Lunch");
    		mainFoodFiltering();
    		break;
    	}
    	case "4":{
    		nameofmealFilter.add("Dinner");
    		mainFoodFiltering();
    		break;
    	}
    	case "5":{
    		hotorcoldFilter.add("Hot");
    		mainFoodFiltering();
    		break;
    	}
    	case "6":{
    		hotorcoldFilter.add("Cold");
    		mainFoodFiltering();
    		break;
    	}
    	case "7":{
    		deliveryFilter.add("Yes");
    		mainFoodFiltering();
    		break;
    	}
    	case "8":{
    		deliveryFilter.add("No");
    		mainFoodFiltering();
    		break;
    	}
    	case "9":{
    		onlineFilter.add("Yes");
    		mainFoodFiltering();
    		break;
    	}
    	case "10":{
    		onlineFilter.add("No");
    		mainFoodFiltering();
    		break;
    	}
    	case "11":{
    		ArrayList<String> decision = new ArrayList<String>();
    		if(foodtypeFilter.size() != 0){
    			decision.add(foodtype);
    		}
    		if(nameofmealFilter.size() != 0){
    			decision.add(nameofmeal);
    		}
    		if(hotorcoldFilter.size() != 0){
    			decision.add(hotorcold);
    		}
    		if(deliveryFilter.size() != 0){
    			decision.add(delivery);
    		}
    		if(onlineFilter.size() != 0){
    			decision.add(online);
    		}
			
    		String mainQuery = "select * from food";
			String foodtypequery = "(";
			String nameofmealquery = "(";
			String hotorcoldquery = "(";
			String deliveryquery = "(";
			String onlinequery = "(";
    		int i = 0;
    		while(i < foodtypeFilter.size()) {
    			if ((i+1) == foodtypeFilter.size()) {
    				foodtypequery = foodtypequery + foodtype+"='"+foodtypeFilter.get(i)+"')";

    			}else
    				foodtypequery = foodtypequery + foodtype+"='"+foodtypeFilter.get(i)+"' or ";
    			i++;
    		}
    		i = 0;
    		while(i < nameofmealFilter.size()) {
    			if ((i+1) == nameofmealFilter.size()) {
    				nameofmealquery = nameofmealquery + nameofmeal+"='"+nameofmealFilter.get(i)+"')";

    			}else
    				nameofmealquery = nameofmealquery + nameofmeal+"='"+nameofmealFilter.get(i)+"' or ";
    			i++;
    		}
    		i = 0;
    		while(i < hotorcoldFilter.size()) {
    			if ((i+1) == hotorcoldFilter.size()) {
    				hotorcoldquery = hotorcoldquery + hotorcold+"='"+hotorcoldFilter.get(i)+"')";

    			}else
    				hotorcoldquery = hotorcoldquery + hotorcold+"='"+hotorcoldFilter.get(i)+"' or ";
    			i++;
    		}
    		i = 0;
    		while(i < deliveryFilter.size()) {
    			if ((i+1) == deliveryFilter.size()) {
    				deliveryquery = deliveryquery + delivery+"='"+deliveryFilter.get(i)+"')";

    			}else
    				deliveryquery = deliveryquery + delivery+"='"+deliveryFilter.get(i)+"' or ";
    			i++;
    		}
    		i = 0;
    		while(i < onlineFilter.size()) {
    			if ((i+1) == onlineFilter.size()) {
    				onlinequery = onlinequery + online+"='"+onlineFilter.get(i)+"')";

    			}else
    				onlinequery = onlinequery + online+"='"+onlineFilter.get(i)+"' or ";
    			i++;
    		}
    		
    		if (decision.size() != 0) {
    			mainQuery = mainQuery + " where (available != '0') and ";
	    		int j = 0;
	    		while(j < decision.size()) {
	    			if (decision.size() == (j+1)) {
		    			if(decision.get(j).equals(foodtype)) {
		    				mainQuery = mainQuery + foodtypequery;
		    			}
		    			else if(decision.get(j).equals(nameofmeal)) {
		    				mainQuery = mainQuery + nameofmealquery;
		    			}
		    			else if(decision.get(j).equals(hotorcold)) {
		    				mainQuery = mainQuery + hotorcoldquery;
		    			}
		    			else if(decision.get(j).equals(delivery)) {
		    				mainQuery = mainQuery + deliveryquery;
		    			}
		    			else if(decision.get(j).equals(online)) {
		    				mainQuery = mainQuery + onlinequery;
		    			}
	    			} else {
		    			if(decision.get(j).equals(foodtype)) {
		    				mainQuery = mainQuery + foodtypequery+" and ";
		    			}
		    			else if(decision.get(j).equals(nameofmeal)) {
		    				mainQuery = mainQuery + nameofmealquery+" and ";
		    			}
		    			else if(decision.get(j).equals(hotorcold)) {
		    				mainQuery = mainQuery + hotorcoldquery+" and ";
		    			}
		    			else if(decision.get(j).equals(delivery)) {
		    				mainQuery = mainQuery + deliveryquery+" and ";
		    			}
		    			else if(decision.get(j).equals(online)) {
		    				mainQuery = mainQuery + onlinequery+" and ";
		    			}
	    			}
	    			j++;
	    		}
    		}
    		System.out.println(mainQuery);
   		break;
    	}
    	case "12":{
    		// Back Option
    		break;
    	}
   	}
    	
    	// need to Create the query
    	input.close();
    }
	public static void foodtypefiltering(String column, ArrayList<String> foodtypefilter) throws NoSuchAlgorithmException, SQLException {
    	foodtypeFilter = foodtypefilter;
    	foodtype = column;
		mainFoodFiltering();
    }
}
