//package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

//import com.srh_heidelberg.mealsanddeals.AdminHomePage;

/*
 * All database interactions are taking place here
 */

	// Database connection information method
public class MysqlCon{
 
	private static final String URL = "jdbc:mysql://localhost:3306/mealsanddeals?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "wltn79768976";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	
    static {
        try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
    }

    	// Database Connection method
	public static Connection createConn() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
		// Database connection closing
	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				if(!conn.isClosed())
					conn.close();
			} catch (SQLException ex) {
				System.out.println("Connection is null!");
				ex.printStackTrace();
			}
		}
	}

		// Statement closing 
	public static void closePrStmt(PreparedStatement prStmt) {
		if(prStmt!=null) {
			try {
				if(!prStmt.isClosed())
					prStmt.close();
			} catch (SQLException ex) {
				System.out.println("PreparedStatement is null!");
				ex.printStackTrace();
			}
		}
	}

		// Result Set closing
	public static void closeRs(ResultSet rs) {
		if(rs!=null) {
			try {
				if(!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet is null!");
				e.printStackTrace();
			}
		}
	}
	
	public static void selectTable(String tablename, ArrayList<String[]> detail) {
		
		int i = 0;
		String query = "";
		
		while(i < detail.size()) {
			if(i < detail.size()-1) {
				query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "' and ";
			} else if (i == detail.size() - 1) {
				query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "'";
			}
			i++;
		}
		
		try {
			Connection conn = createConn();
			Statement stmt = (Statement) conn.createStatement();
			String selectq = "select * from "+ tablename + " where "+ query;
			ResultSet rs = stmt.executeQuery(selectq);
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			int k=1;
			int cn = md.getColumnCount();
			//int cn=2;
			while(rs.next()) {
					System.out.print("["+k+"]");
					for(int j=1 ; j<=cn; j++) 
						System.out.print(rs.getString(j) + " ");
					System.out.println("");
					k++;
				}
				conn.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteFromTable(String tablename, ArrayList<String[]> detail) {
		int i = 0;
		String query = "";
	
		while(i < detail.size()) {
			if(i < detail.size()-1) {
				query = query + detail.get(i)[0] + "=" + detail.get(i)[1] + " and ";
			} else if (i == detail.size() - 1) {
				query = query + detail.get(i)[0] + "=" + detail.get(i)[1] + "";
			}
			i++;
		}
		try {
			Connection conn = createConn();
			Statement stmt = (Statement) conn.createStatement();
			String deleteq = "delete from "+ tablename + " where "+ query;	
			int r = stmt.executeUpdate(deleteq);
			
			if(r==0)
				System.out.println("cannot delete data");
			else
				System.out.println("successfully deleted data");
			conn.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateTable(String tablename, ArrayList<String[]> detail, ArrayList<String[]> condition) {
		int i = 0;
		String query = "";
	
		while(i < detail.size()) {
			if(i < detail.size()-1) {
				query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "' and '";
			} else if (i == detail.size() - 1) {
				query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "'";
			} 
			
			i++;
		}
		try {
			Connection conn = createConn();
			Statement stmt = (Statement) conn.createStatement();
			String updateq = "update "+ tablename + " set " + query +" where " + condition.get(0)[0] + "=" + condition.get(0)[1];	
			
			System.out.println(updateq);
			int r = stmt.executeUpdate(updateq);
			
			if(r==0)
				System.out.println("cannot update data");
			else
				System.out.println("successfully updated data");
			conn.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		// Method for inserting Data to database which take parameters from respective classes and make the query 
	public static void insertToTable(String tablename, ArrayList<String> detail) throws NoSuchAlgorithmException {
		
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;

		int i = 0;
		
		String query = "";
		
		while(i < detail.size()) {
			if(i < detail.size()-1) {
				query = query + "'" + detail.get(i) + "', ";
			} else if (i == detail.size() - 1) {
				query = query + "'" + detail.get(i) + "'";
			}
			i++;
		}
		
		try {
			conn = createConn();
			prStmt = conn.prepareStatement("insert into "+ tablename +" values ("+query+")");
			prStmt.executeUpdate();
			//MealsandDeals.main(null);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		} finally {
			closeRs(rs);
			closePrStmt(prStmt);
			closeConn(conn);
		}
	}

	public static void getColumnName(String tablename, ArrayList<String> columnname) {
		
		try {
			Connection conn = createConn();
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from " + tablename);
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			
			for(int i=1; i<=md.getColumnCount(); i++)
				columnname.add(md.getColumnName(i));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// Login method for fetching Data from database from user input 
	// Also checking validation of Food Service Agents
	// Method is general for all logins
	public static void login(String tablename , ArrayList<String> detail){

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		String tablename1 = null;
		tablename1 = tablename.toLowerCase();
		String query = null;
		String username = null;

		if(tablename1.equals("admin")) 
		{
			query = "select * from "+tablename1+" where username='"+detail.get(0)+"' and password='"+detail.get(1)+"'";
			username = "username";
		} else if(tablename1.equals("fsa")) {
			query = "select * from "+tablename1+" where fsausername='"+detail.get(0)+"' and password='"+detail.get(1)+"'";
			username = "fsausername";
		} else if(tablename1.equals("customer")) {
			query = "select * from "+tablename1+" where customerusername='"+detail.get(0)+"' and password='"+detail.get(1)+"'";
			username = "customerusername";
		}

		try{  

			conn = createConn();
			prStmt = conn.prepareStatement(query);  
			rs = prStmt.executeQuery();

			if(rs.next()) {
				if(tablename1.equals("fsa")) 
				{		
					if(rs.getString("validation").equals("Active")) 
					{
						System.out.println(tablename1.toUpperCase()+"  "+rs.getString(username)+" Logged IN");
					} 
					else
					{
						System.out.println("Your Account is not Active yet..");
						//MealsandDeals.main(null);
					}
				} 
				else if(tablename1.equals("customer")) 
				{
					System.out.println(tablename1.toUpperCase()+"  "+rs.getString(username)+" Logged IN");
				} 
				else if(tablename1.equals("admin"))
				{
					
					//AdminHomePage.homePage();
				}
			}
			else if(!rs.next()) 
			{
				System.out.print("Username or Password is not Correct");
				//MealsandDeals.main(null);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		} 
		finally 
		{
			closeRs(rs);
			closePrStmt(prStmt);
			closeConn(conn);
		}
	}
		// In order to keep the list of inactive Food Service Agents' Username for updating query
	public static ArrayList<String> userslist = new ArrayList<String>();

		// Method for fetching Inactive Food Service Agents with both certificate validated
	public static void inactiveUsers() {

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		ArrayList<String> userlist = new ArrayList<String>();
		
		try{  

			conn = createConn();
			prStmt = conn.prepareStatement("select * from fsa where cookingcertificate='Yes' and businesscertificate='Yes' and validation='Inactive'");  
			rs = prStmt.executeQuery();
        	Scanner input = new Scanner(System.in);
			int j = 0;
			int i = 1;
			while(rs.next()) {
				while (i <= rs.getRow()) {
					System.out.println(rs.getRow() +" - "+rs.getString("fsausername")+"    Both Certificates Validated ["+rs.getRow()+"]");
					System.out.println("-----------------------------------------------------");
					userlist.add(rs.getString("fsausername"));
					j = rs.getRow();
					i++;
				}
			}
			userslist = userlist;
			int k = 0;
			if (k < j) {
				System.out.println("Back [0]");
	        	System.out.println("Select Food Service Agent's number to activate or Select 0 to go back...");
	        	String option = null;
	        	option = input.nextLine();
	        	int rowNumber = Integer.parseInt(option);
	        	if(rowNumber == 0) {
	            	//AdminHomePage.homePage();
	        	} else if(rowNumber != 0) {
	            	userActivation(rowNumber);
	        	}
			} else if(k == j) {
				System.out.println("There is no Inactive Food Service Agent!!! Press 0 to go to Admin Home Page...");
				System.out.println("Back [0]");
	        	String option = null;
	        	option = input.nextLine();
	        	int rowNumber = Integer.parseInt(option);
	        	if(rowNumber == 0) {
	            	//AdminHomePage.homePage();
	        	} else if(rowNumber != 0) {
	            	userActivation(rowNumber);
	        	}	
			}
        	input.close();
		}
		catch(Exception ex)
		{
			System.out.println("There is no Inactive Food Service Agent");
			System.out.println(ex);
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		} 
		finally 
		{
			closeRs(rs);
			closePrStmt(prStmt);
			closeConn(conn);
			
		}
	}

		// Updating Food Service Agent table in database for activating Food Service Agents
	public static void userActivation(int rowNumber) {

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
				
		try{  

			conn = createConn();
			prStmt = conn.prepareStatement("update fsa set validation = 'Active' where fsausername='"+userslist.get(rowNumber-1)+"'");  
			prStmt.executeUpdate();
			inactiveUsers();
		}
		catch(Exception ex)
		{
			System.out.println("There is no Inactive Food Service Agent");
			System.out.println(ex);
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		} 
		finally 
		{
			closeRs(rs);
			closePrStmt(prStmt);
			closeConn(conn);
		}

	}
	
	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

