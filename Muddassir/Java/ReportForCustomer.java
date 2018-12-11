package com.srh_heidelberg.mealsanddeals;

/*import java.io.FileNotFoundException;
import java.io.FileOutputStream;*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import com.srh_heidelberg.mealsanddeals.MysqlCon;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;



public class ReportForCustomer implements ReportInterface{
	String querString = "";
	String status = "";
	String fsausername = null;
	String orderTimeFrom = null;
	String orderTimeTo = null;
	String nationality = "";
	String foodname = "";
	String qGen = "";
	String custUserName = "";
	String table_1 = "";
	String table_2 = "";
	String select = "";
	ArrayList<String> valQueryfsaUser = new ArrayList<String>();
	ArrayList<String> valStatusQuery = new ArrayList<String>();
	ArrayList<String> valNameOfFood = new ArrayList<String>();
	ArrayList<String> valTypeOfFood = new ArrayList<String>();
	@Override
	
	public void generateQuery(String username) {
		custUserName = username;
		table_1 = "orders";
		table_2 = "food";
		select = "select status, fsausername, deliverycharge, nationality, foodname, totalamount, foodprice, ordertime,"
				+ " ((totalamount * foodprice)+deliverycharge) AS total from mealsanddeals.";
		HashMap<String, String> queryArray = new HashMap<String, String>();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Status:  [Open/Closed]");
		status = scanner.nextLine().trim();
		if (!"".equalsIgnoreCase(status)) {
			queryArray.put("status",status);
		}		
		System.out.println("Enter Food name:  [Food Name]");
		foodname = scanner.nextLine().trim();
		if (!"".equalsIgnoreCase(foodname)) {
			queryArray.put("foodname", foodname);
		}
		
		System.out.println("Enter FSA name:  [FSA Name]");
		fsausername = scanner.nextLine().trim();
		if (!"".equalsIgnoreCase(fsausername)) {
			queryArray.put("fsausername", fsausername);
		}
		System.out.println("Enter Nationlality:  [Country name]");
		nationality = scanner.nextLine().trim();
		if (!"".equalsIgnoreCase(nationality)) {
			queryArray.put("nationality", nationality);
		}
		
		System.out.println("Enter Order Time From:  [YYYY-MM-DD]");
		orderTimeFrom = scanner.nextLine().trim();
		if (!"".equalsIgnoreCase(orderTimeFrom)) {
			queryArray.put("ordertime>=",orderTimeFrom);
		}
		
		System.out.println("Enter Order Time to:  [YYYY-MM-DD]");
		orderTimeTo = scanner.nextLine().trim();				
		if (!"".equalsIgnoreCase(orderTimeTo)) {
			queryArray.put("ordertime<=",orderTimeTo);
		}		
		
		int i = 1;
		for (Map.Entry<String, String> me : queryArray.entrySet()) {
				String v = me.getValue();
				String k = me.getKey(); 
				String andCondition = "";
				String equals = "";
				
				if (1 == i) {
					andCondition = "";
				}else { 
					andCondition = " and ";
				}
				 if (!"".equals(orderTimeTo ) && !"".equals(orderTimeFrom)) {
					 equals = "=";
					 if (k.equals("ordertime<=")) {
						 k = "ordertime<";
						 //System.out.println(k);
					 }
					 if (k.equals("ordertime>=")) {
						 k = "ordertime>";
						 //System.out.println(k);
					 }
				 }else if (v.equals(orderTimeTo)){
					 if (k.contains("<")) {
						 k = "ordertime=";
					 }					 
				 }else if (v.equals(orderTimeFrom)){
					 if (k.contains(">=")) {
						 k = "ordertime=";
					 }
				 }else {
					 equals = "=";
				 }
				 
				 if (k.equals("ordertime<==")) {
					 k = "ordertime<=";
					 //System.out.println(k);
				 }
				 if (k.equals("ordertime>==")) {
					 k = "ordertime>=";
					 //System.out.println(k);
				 }
				
								 
	          if (!"".equalsIgnoreCase(v) && v != null) { 
	        	  qGen += andCondition+k+ equals+ "'"+v+"'";
	        	  
	          } 
	          	         
	          i++;
	          
	        }
		
		if (queryArray.size() >=1) {
			querString = select + table_1+ " where customerusername='"+custUserName+"' and " + qGen+ " order by ordertime desc";
		}else {
			querString = select + table_1+" where customerusername='"+custUserName+"'" +" order by ordertime desc";
		}
		
		//System.out.println(querString);
		generateReport(querString, custUserName);
        //scanner.close();
		//System.out.println(querString);
		//return querString;
}	
	@Override
	
	public void generateReport(String query, String custUserName) {
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
				
		try {
			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement(query);

			rs = prStmt.executeQuery();
						 
			JasperReportBuilder report = DynamicReports.report();//a new report	
			
			report
			
			  .columns(
					  
			      Columns.column("Food Service Agent", "fsausername", DataTypes.stringType()),
			      Columns.column("Nationality", "nationality", DataTypes.stringType()),
			      Columns.column("Food Name", "foodname", DataTypes.stringType()),
			      Columns.column("Status", "status", DataTypes.stringType()),			      
			      Columns.column("Order Time", "ordertime", DataTypes.stringType()),
			      Columns.column("Food Price", "foodprice", DataTypes.floatType())
			      .setPattern("\u20ac 0.00")
			      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
			      Columns.column("Total Amount", "totalamount", DataTypes.integerType())
			      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
			      Columns.column("Delivery Charge", "deliverycharge", DataTypes.floatType())
			      .setPattern("\u20ac 0.00")
			      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
			      Columns.column("Bill", "total", DataTypes.floatType())
			      .setPattern("\u20ac 0.00")
			      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)			      
			      ).highlightDetailEvenRows()
 			  .title(//title of the report
			      Components.text("Customer Report "+custUserName))
				  .pageFooter(Components.pageXofY())//show page number on the page footer
				  .setDataSource(query, conn);
				
			
			report.show();
			
			
			if (rs == null) {
				System.out.println("No result set found");
				throw new NullPointerException();
			}
			
			boolean isEmpty = true;
			while (rs.next()) {
				isEmpty = false;
				valQueryfsaUser.add(rs.getString("fsausername"));
				valStatusQuery.add(rs.getString("status"));
				valNameOfFood.add(rs.getString("foodname"));
				valTypeOfFood.add(rs.getString("nationality"));
				
				//System.out.println("Name:" + rs.getString("name") + " || Username:" + rs.getString("customerUsername"));
			}
			
			generateQuery(custUserName); //Comment during unit testing
			
			
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
			
			MysqlCon.closeRs(rs);
			MysqlCon.closePrStmt(prStmt);
			MysqlCon.closeConn(conn);
		}
		
		
	}

}
