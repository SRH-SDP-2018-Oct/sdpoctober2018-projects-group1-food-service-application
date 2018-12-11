package com.srh_heidelberg.mealsanddeals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.Test;
import com.srh_heidelberg.mealsanddeals.MysqlCon;

public class ReportForCustomerTest {
	ReportForCustomer report = new ReportForCustomer();
	ArrayList<String> testQueryFsauser = new ArrayList<String>();
	ArrayList<String> testQuerystatus = new ArrayList<String>();
	ArrayList<String> testNameOfFood = new ArrayList<String>();
	ArrayList<String> testTypeOfFood = new ArrayList<String>();
	String username= null;
	@Test
	public void testGenerateQuery() {
		
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		username = "muddassir297";
		try { 
			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement("select * from mealsanddeals.orders where customerusername='"+username+"'");
			rs = prStmt.executeQuery();
			while(rs.next()) {
				testQueryFsauser.add(rs.getString("fsausername"));
				testQuerystatus.add(rs.getString("status"));
				testNameOfFood.add(rs.getString("foodname"));
				testTypeOfFood.add(rs.getString("nationality"));
			}
			report.generateQuery(username);
			
			assertThat(testQueryFsauser, containsInAnyOrder(report.valQueryfsaUser.toArray()));
			assertThat(testQuerystatus, containsInAnyOrder(report.valStatusQuery.toArray()));
			assertThat(testNameOfFood, containsInAnyOrder(report.valNameOfFood.toArray()));
			assertThat(testTypeOfFood, containsInAnyOrder(report.valTypeOfFood.toArray()));
			
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
