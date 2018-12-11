package com.srh_heidelberg.mealsanddeals;

import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FoodOption {
	private static MysqlCon db;
	private static ArrayList<Food> foodlist;
	private static Food newFood;
	private static int offerid;
	private static FSA loggedInfsa = new FSA();
	
	public static void PrintFoodList(Date date) throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String[]> detail = new ArrayList<String[]>();
		
		foodlist = new ArrayList<Food>();
		detail.add(new String[] {"fsausername", loggedInfsa.getFsausername()}); //should be changed
		detail.add(new String[] {"date", dateFormat.format(date)});
		db.selectFood("food", detail, foodlist);
		
		System.out.println("---FOOD LIST---");
		System.out.println("[id] || Foodname || Food Type || Name Of Meal || Hot or Cold || Price || Delivery Option || Payment Option || Available Amount");
		
		for(int i=0; i<foodlist.size(); i++) {
			String payment = "";
			if (foodlist.get(i).getOnline().equals("Yes")) {
				payment = "Cash an Online";
			} else {
				payment = "Only Cash";
			}
			System.out.println("["+ foodlist.get(i).getFoodid() +"] || " + foodlist.get(i).getFoodname()+" || "+ foodlist.get(i).getFoodtype()+" || "+ foodlist.get(i).getNameofmeal()+" || "+ foodlist.get(i).getHotorcold()+" || "+ foodlist.get(i).getPrice()+" € || "+ foodlist.get(i).getDeliveryoption()+" || "+payment+" || "+ foodlist.get(i).getAvailable());
		}
	}
	public static void PrintFoodListCustomer(String query, Date date) throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String[]> detail = new ArrayList<String[]>();
		
		foodlist = new ArrayList<Food>();
//		detail.add(new String[] {"available", loggedInfsa.getFsausername()}); //should be changed
		detail.add(new String[] {"date", dateFormat.format(date)});
		db.selectFood("food", detail, foodlist);
		
		System.out.println("---FOOD LIST---");
		System.out.println("[id] || Foodname || Food Type || Name Of Meal || Hot or Cold || Price || Delivery Option || Payment Option || Available Amount || Agent Name");
		
		for(int i=0; i<foodlist.size(); i++) {
			String payment = "";
			if (foodlist.get(i).getOnline().equals("Yes")) {
				payment = "Cash an Online";
			} else {
				payment = "Only Cash";
			}
			System.out.println("["+ foodlist.get(i).getFoodid() +"] || " + foodlist.get(i).getFoodname() +" || "+ foodlist.get(i).getFoodtype()  +" || "+ foodlist.get(i).getFoodtype()+" || "+ foodlist.get(i).getNameofmeal()+" || "+ foodlist.get(i).getHotorcold()+" || "+ foodlist.get(i).getPrice()+" € || "+ foodlist.get(i).getDeliveryoption()+" || "+payment+" || "+ foodlist.get(i).getAvailable()+" || "+ foodlist.get(i).getFasusername());
		}
/*
		System.out.println("---FOOD LIST---");
		System.out.println("[id] || Foodname || Food Type || Name Of Meal || Hot or Cold || Price || Delivery Option || Payment Option || Available Amount || Agent Name");
		db.foodList(query,(java.util.Date) date);*/
		
	}
	public static void AddFood(Date date) throws NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ArrayList<String> detail = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);	
		Food newFood = new Food();
		newFood.setFood(date,loggedInfsa);
		detail = newFood.makeQuery();
		
		db.insertToTable("food", detail);
	}
	public static void EditFood(Food newFood) throws SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ArrayList<String[]> detail = new ArrayList<String[]>();
		ArrayList<String[]> condition = new ArrayList<String[]>();
		
		ArrayList<Field> foodcolumn = newFood.getFood();
		
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		int num = 1;
		String editinfo;
		
		while(num!=0) {
			System.out.print("\nSelect Number You Want To Edit(press 0 to exit) : ");
			num = sc.nextInt();
			if(num==0) break;
			else {
				System.out.print("Change Information :");
				editinfo = sc2.nextLine();
				detail.add(new String[] {foodcolumn.get(num-1).getName(), editinfo});
			}
		}
		
		condition.add(new String[] {"foodid", String.valueOf(newFood.getFoodid())}); //primary key -> need to be changed
		db.updateTable("food", detail, condition);
	}
	public static void DeleteFood(Date date, int foodid) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ArrayList<String[]> detail = new ArrayList<String[]>();
		detail.add(new String[] {"foodid", String.valueOf(foodid)});
		Scanner sc = new Scanner(System.in);
		
		boolean check = checkOffer(date, foodid);
		if(check==true) {
			System.out.println("WARNING : There is an Offer with the Food you want to Delete! Delete the Offer first");
			System.out.print("1: Delete Offer\n2: Back\n");
 
			int n = sc.nextInt();
			System.out.println("offerid : " + offerid);
			switch(n) {
			case 1: OfferOption.DeleteOffer(offerid);
			case 2: FoodOption.ShowFoodOption(date,loggedInfsa);
			}
		}
		else 
			db.deleteFromTable("food", detail);
		
	}
	public static boolean checkOffer(Date date, int foodid) throws ParseException {
		try {
			Connection conn = db.createConn();
			Statement stmt = (Statement) conn.createStatement();
			String selectq = "select offerid from offer where foodid = "+ foodid;
			ResultSet rs = stmt.executeQuery(selectq);
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			
			while(rs.next()) {
				offerid = rs.getInt("offerid");
				conn.close();
				return true;
			}
			conn.close();
			return false;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void SelectFood(Date date) throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		System.out.print("Enter Food ID : ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		
		newFood = new Food();
		
		int i=0;
		while(i<foodlist.size()) {
			if(foodlist.get(i).getFoodid()==id) {
				newFood = foodlist.get(i);
				break;
			}
			else {
				i++;
			}
		}
		
		System.out.print("1: Edit Food\n2: Delete Food\n3: Add Offer\n4: Back\n");	
		int n = sc.nextInt();
		
		switch(n) {
		case 1: EditFood(newFood);
				ShowFoodOption(date,loggedInfsa);
		case 2: DeleteFood(date, id);
				ShowFoodOption(date,loggedInfsa);
		case 3: OfferOption.AddOffer(date, id,loggedInfsa);
				OfferOption.ShowOfferOption(date,loggedInfsa);
		case 4: ShowFoodOption(date,loggedInfsa);
		case 5: System.exit(0);
		}
	}
	
	public Food SelectFoodCustomer(Date date)throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
		System.out.print("Enter Foodid : ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		
		newFood = new Food();
		
		int i=0;
		while(i<foodlist.size()) {
			if(foodlist.get(i).getFoodid()==id) {
				newFood = foodlist.get(i);
				break;
			}
			else {
				i++;
			}
		}
		
		return newFood;
	}
	
	public static void ShowFoodOption(Date date, FSA loggedInFsa) throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		loggedInfsa = loggedInFsa;
		FsaDayPage daypage = new FsaDayPage();
		PrintFoodList(date);
		System.out.print("1: Add Food\n2: Select Existing Food\n3: Back\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		switch(n) {
		case 1: AddFood(date);
				ShowFoodOption(date,loggedInfsa);
		case 2: SelectFood(date);
		case 3: daypage.BacktoDay(date);
		}
	}
	
}
