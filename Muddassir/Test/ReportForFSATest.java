package com.srh_heidelberg.mealsanddeals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.Test;

public class ReportForFSATest {
	ReportForFSA report = new ReportForFSA();
	ArrayList<String> testQueryCustomer = new ArrayList<String>();
	ArrayList<String> testQuerystatus = new ArrayList<String>();
	ArrayList<String> testNameOfFood = new ArrayList<String>();
	ArrayList<String> testDeliveryType = new ArrayList<String>();
	String username= null;
	@Test
	public void testGenerateQuery() {

		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		username = "Alex123";
		try { 
			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement("select * from mealsanddeals.orders where fsausername='"+username+"'");
			rs = prStmt.executeQuery();
			
			report.generateQuery(username);
			while(rs.next()) {
				testQueryCustomer.add(rs.getString("fsausername"));
				testQuerystatus.add(rs.getString("status"));
				testNameOfFood.add(rs.getString("foodname"));
				testDeliveryType.add(rs.getString("deliveryType"));
			}
			
			System.out.println("Test customer: "+testQueryCustomer+"\nActual customer: "+ report.valCustomerUser);
			System.out.println("Test status: "+testQuerystatus+"\nActual status: "+ report.valStatusQuery);
			System.out.println("Test name "+testNameOfFood+"\nActual name: "+ report.valNameOfFood);
			System.out.println("Test delivery: "+testDeliveryType+"\nActual delivery: "+ report.valDeliveryType);
			
			assertThat(testQuerystatus, containsInAnyOrder(report.valStatusQuery.toArray()));
			assertThat(testNameOfFood, containsInAnyOrder(report.valNameOfFood.toArray()));
			assertThat(testDeliveryType, containsInAnyOrder(report.valDeliveryType.toArray()));
			
			//System.out.println();
			
		}catch(Exception ex){
			System.err.println("Got an exception while fetching the user by name!");
			ex.printStackTrace();
			System.err.println(ex.getMessage());
			System.exit(0); 
		}finally {
			MysqlCon.closeRs(rs);
			MysqlCon.closePrStmt(prStmt);
			MysqlCon.closeConn(conn);
		}
		
		
		//System.out.println("Customer user name is: "+report.valQuery);

	}

	@Test
	public void testGenerateReport() {
		//fail("Not yet implemented");
	}

}
