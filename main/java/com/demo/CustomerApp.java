package com.demo;

import java.sql.*;
import java.util.*;

import org.srh.db.RootCon;

public class CustomerApp {

	public static void main(String[] args) {
		CustomerApp app = new CustomerApp();
		//String name = "James Bondy";
		//String userName = "007";
		//app.addUser(name, userName);
		app.getInputFromUser();
		//app.printUserByName(name);		
		//app.printAllCustomer();
		//app.flushAllCustomerData();
		
	}
	
	public void getInputFromUser() {
		Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Enter Customer name : ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Customer username : ");
            String customerusername = scanner.nextLine();
            System.out.print("Want to exit (y/n): ");
            String wantexit = scanner.nextLine();
            addUser(name, customerusername);
            
            if ("y".equals(wantexit)) {
                System.out.println("Exit!");
                printAllCustomer();                
                break;
            }
            
        }

        scanner.close();
	}
	
	
	public void flushAllCustomerData() {
		String query = "delete from customer";
		
		Connection conn = null;
		PreparedStatement prStmt = null;
		 try {
			conn = RootCon.createConn();
			prStmt = conn.prepareStatement(query);			
			
			prStmt.executeUpdate();
			System.out.println("Data flushed");
		} catch (Exception ex) {
			System.err.println("Got an exception while connection");
			ex.printStackTrace();
			System.err.println(ex.getMessage());
			System.exit(0);
		}finally {
			//RootCon.closeRs(rs);
			RootCon.closePrStmt(prStmt);
			RootCon.closeConn(conn);
		}
	}


	public void printAllCustomer() {
		String query = "select name, customerUsername from customer ";

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;

		try {
			conn = RootCon.createConn();
			prStmt = conn.prepareStatement(query);

			rs = prStmt.executeQuery();

			if (rs == null) {
				System.out.println("No result set found");
				throw new NullPointerException();
			}
			
			boolean isEmpty = true;

			// Will run the loop till the end of the record in the table
			while (rs.next()) {
				isEmpty = false;
				System.out.println("Name:" + rs.getString("name") + " || Username:" + rs.getString("customerUsername"));
			}
			
			// Print this if the table is empty
			if(isEmpty) {
				System.out.println("Table is empty");
			}
		}
		catch (Exception ex) {
			System.err.println("Got an exception while fetching the user by name!");
			ex.printStackTrace();
			System.err.println(ex.getMessage());
			System.exit(0);
		}
		finally {
			RootCon.closeRs(rs);
			RootCon.closePrStmt(prStmt);
			RootCon.closeConn(conn);
		}
	}


	public void printUserByName(String name) {
		String query = "select name, customerUsername from customer where name=?";

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;

		try {
			conn = RootCon.createConn();
			prStmt = conn.prepareStatement(query);
			
			// Adding Condition [name=?]
			prStmt.setString(1, name);

			rs = prStmt.executeQuery();

			if (rs == null) {
				System.out.println("No result set found");
				throw new NullPointerException();
			}
			if (rs.next()) {
				System.out.println("Name:" + rs.getString("name"));
				System.out.println("Username:" + rs.getString("customerUsername"));
			}
			else {
				System.out.println("Record does not exist!");
			}
		}
		catch (Exception ex) {
			System.err.println("Got an exception while fetching the user by name!");
			ex.printStackTrace();
			System.err.println(ex.getMessage());
			System.exit(0);
		}
		finally {
			RootCon.closeRs(rs);
			RootCon.closePrStmt(prStmt);
			RootCon.closeConn(conn);
		}
	}


	public void addUser(String name, String customerUsername) {
		String query = "INSERT INTO customer (name, customerUsername)  VALUES (?,?)";

		Connection conn = null;
		PreparedStatement prStmt = null;
		
		try {
			conn = RootCon.createConn();
			prStmt = conn.prepareStatement(query);

			// Assign values/parameters to the Insert query
			prStmt.setString(1, name);
			prStmt.setString(2, customerUsername);

			prStmt.executeUpdate();
		}
		catch (Exception ex) {
			System.err.println("Got an exception while inserting the customer!");
			ex.printStackTrace();
			System.err.println(ex.getMessage());
			System.exit(0);
		}
		finally {
			RootCon.closePrStmt(prStmt);
			RootCon.closeConn(conn);
		}
	}
}
