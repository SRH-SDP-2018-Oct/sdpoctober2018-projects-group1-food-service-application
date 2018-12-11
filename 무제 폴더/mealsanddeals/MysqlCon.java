package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.srh_heidelberg.mealsanddeals.AdminHomePage;

/*
 * All database interactions are taking place here
 */

	// Database connection information method
public class MysqlCon{
 
	private static final String URL = "jdbc:mysql://localhost:3306/mealsanddeals?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "wltn79768976";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver"; 
	
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

		// Method for inserting Data to database which take parameters from respective classes and make the query 
	public static void insertToTable(String tablename , ArrayList<String> detail) throws NoSuchAlgorithmException {
		
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
						FSA loggedInFsa = new FSA();
						loggedInFsa.setName(rs.getString("name"));
						loggedInFsa.setSex(rs.getString("sex"));
						loggedInFsa.setBirthday(rs.getString("Birthday"));
						loggedInFsa.setAddress(rs.getString("address"));
						loggedInFsa.setFsausername(rs.getString("fsausername"));
						loggedInFsa.setEmail(rs.getString("email"));
						loggedInFsa.setNationality(rs.getString("nationality"));
						loggedInFsa.setPhonenumber(rs.getString("phonenumber"));
						FsaHomepage.fsaMain(loggedInFsa);
					} 
					else
					{
						System.out.println("Your Account is not Active yet..");
						ProfileUpdate.inactiveFsaprofileUpdate(rs.getString(username));
						MealsandDeals.main(null);
					}
				} 
				else if(tablename1.equals("customer")) 
				{
					Customer loggedInCustomer = new Customer();
					loggedInCustomer.setName(rs.getString("name"));
					loggedInCustomer.setSex(rs.getString("sex"));
					loggedInCustomer.setBirthday(rs.getString("Birthday"));
					loggedInCustomer.setAddress(rs.getString("address"));
					loggedInCustomer.setCustomerusername(rs.getString("customerusername"));
					loggedInCustomer.setEmail(rs.getString("email"));
					loggedInCustomer.setNationality(rs.getString("nationality"));
					loggedInCustomer.setPhonenumber(rs.getString("phonenumber"));
					CustomerMain.customerLogin(loggedInCustomer);
				} 
				else if(tablename1.equals("admin"))
				{
					
					AdminHomePage.homePage();
				}
			}
			else if(!rs.next()) 
			{
				System.out.print("Username or Password is not Correct");
				MealsandDeals.main(null);
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
	public static ArrayList<String> userslistWithBothCertificates = new ArrayList<String>();

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
					System.out.println(rs.getRow() +": "+rs.getString("fsausername")+"    Both Certificates Validated");
					System.out.println("-----------------------------------------------------");
					userlist.add(rs.getString("fsausername"));
					j = rs.getRow();
					i++;
				}
			}
			userslistWithBothCertificates = userlist;
			userslistDelete = userlist;
			decisionMaker = "bothcertificate";
			int k = 0;
			if (k < j) {
				System.out.println("0: Back");
	        	System.out.println("Select Food Service Agent's number to see options or Select 0 to go back...");
	        	String option = null;
	        	option = input.nextLine();
	        	int rowNumber = Integer.parseInt(option);
	        	if(rowNumber == 0) {
	            	AdminHomePage.homePage();
	        	} else if(rowNumber != 0) {
	            	adminDecisionValidation(rowNumber);
	        	}
			} else if(k == j) {
				System.out.println("There is no Inactive Food Service Agent!!! Press 0 to go to Admin Home Page...");
				System.out.println("0: Back");
	        	String option = null;
	        	option = input.nextLine();
	        	int rowNumber = Integer.parseInt(option);
	        	if(rowNumber == 0) {
	            	AdminHomePage.homePage();
	        	} else if(rowNumber != 0) {
	        		adminDecisionValidation(rowNumber);
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
			prStmt = conn.prepareStatement("update fsa set validation = 'Active' where fsausername='"+userslistWithBothCertificates.get(rowNumber-1)+"'");  
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
	


public static void registeringCheck(String emailId){

	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;
	PreparedStatement prStmt1 = null;
	try{  

		conn = createConn();
		prStmt = conn.prepareStatement("select * from emailconfirmation where email='"+emailId+"'");  
		rs = prStmt.executeQuery();
		while(rs.next()) {
			System.out.println("You have Registered already with this E-Mail address");
			MealsandDeals.main(null);
		}

		if(!rs.next()) {
			prStmt1 = conn.prepareStatement("insert into emailconfirmation values ('"+emailId+"')");  
			prStmt1.executeUpdate();
    		EmailConfirmation.main(null, emailId);

			}
	}
	catch(Exception ex)
	{
//		System.out.println(ex);
//		ex.printStackTrace();
//		System.err.println(ex.getMessage());
	} 
	finally 
	{
		closePrStmt(prStmt1);
		closeRs(rs);
		closePrStmt(prStmt);
		closeConn(conn);
	}
}


public static String checkingDups(String tablename , String enteredValue , String columnLabel){

	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;
	PreparedStatement prStmt1 = null;
	try{  

		conn = createConn();
		prStmt = conn.prepareStatement("select * from "+tablename+" where "+columnLabel+"='"+enteredValue+"'");  
		rs = prStmt.executeQuery();
		while(rs.next()) {
			enteredValue = "";
		}

/*		if(!rs.next()) {
			enteredValue = "";
		}*/
	}
	catch(Exception ex)
	{
		System.out.println(ex);
		ex.printStackTrace();
		System.err.println(ex.getMessage());
	} 
	finally 
	{
		closePrStmt(prStmt1);
		closeRs(rs);
		closePrStmt(prStmt);
		closeConn(conn);
	}
	return enteredValue;
}

public static ArrayList<String> userslistWithoutCookingCert = new ArrayList<String>();
public static ArrayList<String> userslistWithoutBusinessCert = new ArrayList<String>();
public static ArrayList<String> userslistWithoutCertificate = new ArrayList<String>();
public static ArrayList<String> userslistDelete = new ArrayList<String>();
public static String decisionMaker = null;
public static String emailId = null;
public static void inactiveUsersWithoutCookingCert() {

	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;
	ArrayList<String> userlist = new ArrayList<String>();
	
	try{  

		conn = createConn();
		prStmt = conn.prepareStatement("select * from fsa where cookingcertificate='No' and businesscertificate='Yes' and validation='Inactive'");  
		rs = prStmt.executeQuery();
    	Scanner input = new Scanner(System.in);
		int j = 0;
		int i = 1;
		while(rs.next()) {
			while (i <= rs.getRow()) {
				System.out.println(rs.getRow() +": "+rs.getString("fsausername")+"    Without Cooking Certificate ");
				System.out.println("-----------------------------------------------------");
				userlist.add(rs.getString("fsausername"));
				emailId = rs.getString("email");
				j = rs.getRow();
				i++;
			}
		}
		decisionMaker = "nocooking";
		userslistWithoutCookingCert = userlist;
		userslistDelete = userlist;
		int k = 0;
		if (k < j) {
			System.out.println("0: Back");
        	System.out.println("Select Food Service Agent's number to see the option or Select 0 to go back...");
        	String option = null;
        	option = input.nextLine();
        	int rowNumber = Integer.parseInt(option);
        	if(rowNumber == 0) {
            	AdminHomePage.homePage();
        	} else if(rowNumber != 0) {
            	adminDecision(rowNumber);
        	}
		} else if(k == j) {
			System.out.println("There is no Inactive Food Service Agent Without Cooking Certificate !!! Press 0 to go to Admin Home Page...");
			System.out.println("0: Back");
        	String option = null;
        	option = input.nextLine();
        	int rowNumber = Integer.parseInt(option);
        	if(rowNumber == 0) {
            	AdminHomePage.homePage();
        	} else if(rowNumber != 0) {
        		adminDecision(rowNumber);
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

public static void inactiveUsersWithoutBusinessCert() {

	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;
	ArrayList<String> userlist = new ArrayList<String>();
	
	try{  

		conn = createConn();
		prStmt = conn.prepareStatement("select * from fsa where cookingcertificate='Yes' and businesscertificate='No' and validation='Inactive'");  
		rs = prStmt.executeQuery();
    	Scanner input = new Scanner(System.in);
		int j = 0;
		int i = 1;
		while(rs.next()) {
			while (i <= rs.getRow()) {
				System.out.println(rs.getRow() +": "+rs.getString("fsausername")+"    Without Business Certificate ");
				System.out.println("-----------------------------------------------------");
				userlist.add(rs.getString("fsausername"));
				j = rs.getRow();
				emailId = rs.getString("email");
				i++;
			}
		}
		decisionMaker = "nobusiness";
		userslistWithoutBusinessCert = userlist;
		userslistDelete = userlist;
		int k = 0;
		if (k < j) {
			System.out.println("0: Back");
        	System.out.println("Select Food Service Agent's number to see the option or Select 0 to go back...");
        	String option = null;
        	option = input.nextLine();
        	int rowNumber = Integer.parseInt(option);
        	if(rowNumber == 0) {
            	AdminHomePage.homePage();
        	} else if(rowNumber != 0) {
            	adminDecision(rowNumber);
        	}
		} else if(k == j) {
			System.out.println("There is no Inactive Food Service Agent Without Business Certificate !!! Press 0 to go to Admin Home Page...");
			System.out.println("0: Back");
        	String option = null;
        	option = input.nextLine();
        	int rowNumber = Integer.parseInt(option);
        	if(rowNumber == 0) {
            	AdminHomePage.homePage();
        	} else if(rowNumber != 0) {
        		adminDecision(rowNumber);
        	}	
		}
    	input.close();
	}
	catch(Exception ex)
	{
		System.out.println("There is no Inactive Food Service Agent without Business Certificate");
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


public static void inactiveUsersWithoutCertificate() {

	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;
	ArrayList<String> userlist = new ArrayList<String>();
	
	try{  

		conn = createConn();
		prStmt = conn.prepareStatement("select * from fsa where cookingcertificate='No' and businesscertificate='No' and validation='Inactive'");  
		rs = prStmt.executeQuery();
    	Scanner input = new Scanner(System.in);
		int j = 0;
		int i = 1;
		while(rs.next()) {
			while (i <= rs.getRow()) {
				System.out.println(rs.getRow() +": "+rs.getString("fsausername")+"    Without any Certificate ");
				System.out.println("-----------------------------------------------------");
				userlist.add(rs.getString("fsausername"));
				emailId = rs.getString("email");
				j = rs.getRow();
				i++;
			}
		}
		decisionMaker = "nocertificate";
		userslistWithoutCertificate = userlist;
		userslistDelete = userlist;
		int k = 0;
		if (k < j) {
			System.out.println("0: Back");
        	System.out.println("Select Food Service Agent's number to see the option or Select 0 to go back...");
        	String option = null;
        	option = input.nextLine();
        	int rowNumber = Integer.parseInt(option);
        	if(rowNumber == 0) {
            	AdminHomePage.homePage();
        	} else if(rowNumber != 0) {
            	adminDecision(rowNumber);
        	}
		} else if(k == j) {
			System.out.println("There is no Inactive Food Service Agent Without Business Certificate !!! Press 0 to go to Admin Home Page...");
			System.out.println("0: Back");
        	String option = null;
        	option = input.nextLine();
        	int rowNumber = Integer.parseInt(option);
        	if(rowNumber == 0) {
            	AdminHomePage.homePage();
        	} else if(rowNumber != 0) {
        		adminDecision(rowNumber);
        	}	
		}
    	input.close();
	}
	catch(Exception ex)
	{
		System.out.println("There is no Inactive Food Service Agent without Business Certificate");
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


public static void adminDecision(int rowNumber) {
	Scanner input = new Scanner(System.in);
	System.out.println("1: Send Email reminder");
	System.out.println("2: Delete Agent");
	System.out.println("3: Back");
	String option = null;
	option = input.nextLine();
	switch (option) {

	case "1" : {
		EmailConfirmation.warningEmail(decisionMaker,emailId);
		break;
	}

	case "2" : {
		inactiveUserDelete(rowNumber);
		break;
	}

	case "3" : {
		if(decisionMaker.equals("nocooking")) {
			inactiveUsersWithoutCookingCert();
		}	
		else if(decisionMaker.equals("nobusiness")) {
			inactiveUsersWithoutBusinessCert();
		}	
		else if(decisionMaker.equals("bothcertificate")) {
			inactiveUsers();
		}	
		else if(decisionMaker.equals("nocertificate")) {
			inactiveUsersWithoutCertificate();
		}	
		break;
	}
	
	}
	input.close();
}

public static void adminDecisionValidation(int rowNumber) {
	Scanner input = new Scanner(System.in);
	System.out.println("1: Activate Agent");
	System.out.println("2: Delete Agent");
	System.out.println("3: Back");
	String option = null;
	option = input.nextLine();
	switch (option) {

	case "1" : {
		userActivation(rowNumber);
		break;
	}

	case "2" : {
		inactiveUserDelete(rowNumber);
		break;
	}

	case "3" : {
		inactiveUsers();
		break;
	}
	
	}
	input.close();
}


private static void inactiveUserDelete(int rowNumber) {

	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;
			
	try{  

		conn = createConn();
		prStmt = conn.prepareStatement("delete from fsa where fsausername='"+userslistDelete.get(rowNumber-1)+"'");  
		prStmt.executeUpdate();
		if(decisionMaker.equals("nocooking")) {
			inactiveUsersWithoutCookingCert();
		}	
		else if(decisionMaker.equals("nobusiness")) {
			inactiveUsersWithoutBusinessCert();
		}	
		else if(decisionMaker.equals("bothcertificate")) {
			inactiveUsers();
		}	
		else if(decisionMaker.equals("nocertificate")) {
			inactiveUsersWithoutCertificate();
		}	
	}
	catch(Exception ex)
	{
		System.out.println("There is no Inactive Food Service Agent without Cooking Certificte");
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

public static String oldValueShow(String tablename , String enteredValue , String columnLabel , String checkingColumnLabel){

	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;
	PreparedStatement prStmt1 = null;
	try{  

		conn = createConn();
		prStmt = conn.prepareStatement("select * from "+tablename+" where "+checkingColumnLabel+"='"+enteredValue+"'");  
		rs = prStmt.executeQuery();
		while(rs.next()) {
			enteredValue = rs.getString(columnLabel);
			
		}

/*		if(!rs.next()) {
			enteredValue = "";
		}*/
	}
	catch(Exception ex)
	{
		System.out.println(ex);
		ex.printStackTrace();
		System.err.println(ex.getMessage());
	} 
	finally 
	{
		closePrStmt(prStmt1);
		closeRs(rs);
		closePrStmt(prStmt);
		closeConn(conn);
	}
	return enteredValue;
}



public static void updateTable(String tablename, ArrayList<String[]> detail, ArrayList<String[]> condition) throws SQLException {
	int i = 0;
	String query = "";
	Connection conn = null;
	PreparedStatement prStmt = null;

	while(i < detail.size()) {
		if(i < detail.size()-1) {
			query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "', ";
		} else if (i == detail.size() - 1) {
			query = query + detail.get(i)[0] + "='" + detail.get(i)[1] + "'";
		} 
		
		i++;
	}
	try {
		conn = createConn();
		prStmt = conn.prepareStatement("update "+ tablename + " set " + query +" where " + condition.get(0)[0] + "='" + condition.get(0)[1]+"'");
		prStmt.executeUpdate();
		
	} catch (SQLException ex) {
		System.out.println(ex);
		ex.printStackTrace();
		System.err.println(ex.getMessage());
	}
	finally 
	{
		closePrStmt(prStmt);
		closeConn(conn);
	}
}

public static void selectFood(String tablename, ArrayList<String[]> detail, ArrayList<Food> foodlist){	
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
		int k=1;
		
		while(rs.next()) {
			Food newFood = new Food();
			k=1;
			newFood.setNameofmeal(rs.getString(k++));
			newFood.setFoodname(rs.getString(k++));
			newFood.setFoodtype(rs.getString(k++));
			newFood.setHotorcold(rs.getString(k++));
			newFood.setAvailable(rs.getString(k++));
			newFood.setTotalamount(rs.getString(k++));
			newFood.setPrice(rs.getString(k++));
			newFood.setDeliveryoption(rs.getString(k++));
			newFood.setCash(rs.getString(k++));
			newFood.setOnline(rs.getString(k++));
			newFood.setDate(rs.getString(k++));
			newFood.setDateofadding(rs.getString(k++));
			newFood.setFoodid(rs.getString(k++));
			newFood.setFasusername(rs.getString(k++));
			foodlist.add(newFood);
		}
		conn.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public static void selectOffer(String tablename, ArrayList<String[]> detail, ArrayList<Offer> offerlist) {	
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
		int k=1;
		
		while(rs.next()) {
			Offer newOffer = new Offer();
			k=1;
			newOffer.setOfferid(rs.getString(k++));
			newOffer.setFoodid(rs.getString(k++));
			newOffer.setFoodamount(rs.getString(k++));
			newOffer.setDiscount(rs.getString(k++));
			newOffer.setFsausername(rs.getString(k++));
			newOffer.setDate(rs.getString(k++));
			offerlist.add(newOffer);
		}
		conn.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
/*public static void selectOrder(String tablename, ArrayList<String[]> detail, ArrayList<Order> orderlist) {	
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
		int k=1;
		
		while(rs.next()) {
			Order newOrder = new Order();
			k=1;
			newOrder.setOrderid(Long.valueOf(rs.getString(k++)));
			newOrder.setFoodid(rs.getString(k++));
			newOrder.setFoodamount(rs.getString(k++));
			newOrder.setDiscount(rs.getString(k++));
			newOrder.setFsausername(rs.getString(k++));
			newOrder.setDate(rs.getString(k++));
			orderlist.add(newOrder);
		}
		conn.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
}*/

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
			System.out.println("Cannot Delete Data");
		else
			System.out.println("Successfully Deleted Data");
		conn.close();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
/*public static void foodList(String query,java.util.Date date){
	
	Connection conn = null;
	PreparedStatement prStmt = null;
	ResultSet rs = null;

	
		try {
			conn = createConn();
			prStmt = conn.prepareStatement(query);
			rs = prStmt.executeQuery();
			String paymentOption = "";
			int k = 1;
			while(rs.next()) {
				if(rs.getString("online").equals("Yes")) {
					paymentOption = "Cash and Online";
				} else {
					paymentOption = "Cash";
				}
				System.out.println("["+rs.getLong("foodid")+"] || "+rs.getString("foodname")+" || "+rs.getString("nameofmeal")+" || "+rs.getString("hotorcold")+" || "+rs.getFloat("price")+" || "+rs.getString("deliverytype")+" || "+paymentOption+" || "+rs.getString("available")+" || "+rs.getString("fsausername"));
				
				Food newFood = new Food();
				k=1;
				newFood.setNameofmeal(rs.getString(k++));
				newFood.setFoodname(rs.getString(k++));
				newFood.setFoodtype(rs.getString(k++));
				newFood.setHotorcold(rs.getString(k++));
				newFood.setAvailable(rs.getString(k++));
				newFood.setTotalamount(rs.getString(k++));
				newFood.setPrice(rs.getString(k++));
				newFood.setDeliveryoption(rs.getString(k++));
				newFood.setCash(rs.getString(k++));
				newFood.setOnline(rs.getString(k++));
				newFood.setDate(rs.getString(k++));
				newFood.setDateofadding(rs.getString(k++));
				newFood.setFoodid(rs.getString(k++));
				newFood.setFasusername(rs.getString(k++));
				foodlist.add(newFood);

			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		} finally {
			closeRs(rs);
			closePrStmt(prStmt);
			closeConn(conn);
		}

}*/
}


