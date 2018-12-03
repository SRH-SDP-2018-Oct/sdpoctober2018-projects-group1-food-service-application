package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FoodTypeFilter {

	public static ArrayList<String> foodtypeFilter = new ArrayList<String>();
	public static ArrayList<String> foodtypeFilterMenu = new ArrayList<String>();
	public static String foodtype = "foodtype";
	
	
	public static void foodTypeFilter() {

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		ArrayList<String> userlist = new ArrayList<String>();
		System.out.println("Select Food Type :");
		System.out.println("");
		
		try{  

			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement("select foodtype from food");  
			rs = prStmt.executeQuery();
//			int j = 1;
			while(rs.next()) {
				int i = 1;
				while (i <= rs.getRow()) {
					if(userlist.indexOf(rs.getString("foodtype")) == -1) {
						
//						System.out.println(j+" - "+rs.getString("foodtype")+" ["+j+++"]");
						userlist.add(rs.getString("foodtype"));
						foodtypeFilterMenu = userlist;
//						j++;
					}
						i++;
				}
			}
			foodtypelist();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		} 
		finally 
		{
			MysqlCon.closeRs(rs);
			MysqlCon.closePrStmt(prStmt);
			MysqlCon.closeConn(conn);
			
		}
	}
	
	public static void foodtypelist() throws NoSuchAlgorithmException, SQLException {

		
		int k = 0;
		while (k < foodtypeFilterMenu.size()) {
			int cnt = k +1;
			System.out.println(cnt +" - "+foodtypeFilterMenu.get(k)+" ["+ cnt +"]");
			k++;
		}
		k++;
		System.out.println(k+" - Apply ["+k+++"]");
		System.out.println(k+" - Back [0]");
		System.out.println("");
		System.out.println("Select Food Type number for Filter and after finishing Select Apply or Press 0 to go back");
//		System.out.println(userlist.size());
    	Scanner input = new Scanner(System.in);
    	String option = null;
		option = input.nextLine();

		int choise = Integer.parseInt(option);
		
		if(choise > 0) {
			if(choise == (k-1) ) {
				FoodFiltering.foodtypefiltering(foodtype, foodtypeFilter);
			}
			else if(choise < (k-1)) {
				foodtypeFilter.add(foodtypeFilterMenu.get((choise-1)));
				foodtypeFilterMenu.remove((choise-1));
				foodtypelist();
			}
		} 
		else if(choise == 0) {
			FoodFiltering.main(null);
		}

		
		
    	input.close();

	}

}
