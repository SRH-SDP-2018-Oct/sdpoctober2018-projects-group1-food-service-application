package mealsanddeals.foodservice;

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
	
	public static void PrintFoodList(Date date) throws ParseException {
		System.out.print("\033[H\033[2J");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String[]> detail = new ArrayList<String[]>();
		
		foodlist = new ArrayList<Food>();
		detail.add(new String[] {"fsausername", "ash2"}); //should be changed
		detail.add(new String[] {"date", dateFormat.format(date)});
		db.selectFood("food", detail, foodlist);
		
		System.out.println("---FOOD LIST---");
		System.out.println("[id]foodname  nameofmeal  foodtype");
		
		for(int i=0; i<foodlist.size(); i++) {
			System.out.println("["+ foodlist.get(i).getFoodid() +"]" + foodlist.get(i).getFoodname() +"  "+ foodlist.get(i).getNameofmeal()  +"  "+ foodlist.get(i).getFoodtype());
		}
	}
	public static void AddFood(Date date) throws NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ArrayList<String> detail = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);	
		Food newFood = new Food();
		newFood.setFood(date);
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
			System.out.print("\nSelect number you want to edit(press 0 to exit)>>");
			num = sc.nextInt();
			if(num==0) break;
			else {
				System.out.print("Change information>>");
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
			System.out.println("WARNING : There is offer with the food you want to delete! Delete the offer first");
			System.out.print("1: delete offer\n2: back to food option\n>>");
 
			int n = sc.nextInt();
			System.out.println("offerid : " + offerid);
			switch(n) {
			case 1: OfferOption.DeleteOffer(offerid);
			case 2: FoodOption.ShowFoodOption(date);
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
		System.out.print("Enter Foodid>>");
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
		
		System.out.print("\033[H\033[2J");
		System.out.print("1: edit food\n2: delete food\n3: add offer\n4: back to food option\n5: quit program\n>>");	
		int n = sc.nextInt();
		
		switch(n) {
		case 1: EditFood(newFood);
				ShowFoodOption(date);
		case 2: DeleteFood(date, id);
				ShowFoodOption(date);
		case 3: OfferOption.AddOffer(date, id);
				OfferOption.ShowOfferOption(date);
		case 4: ShowFoodOption(date);
		case 5: System.exit(0);
		}
	}
	
	public Food SelectFoodCustomer(Date date)throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
		System.out.print("Enter Foodid>>");
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
	
	public static void ShowFoodOption(Date date) throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		//FsaDayPage daypage = new FsaDayPage();
		PrintFoodList(date);
		System.out.print("1: add food\n2: select existing food\n3: back to day\n4: quit program\n>>");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		switch(n) {
		case 1: AddFood(date);
				ShowFoodOption(date);
		case 2: SelectFood(date);
		//case 3: daypage.BacktoDay(date);
		case 4: System.exit(0);
		}
	}
	
}
