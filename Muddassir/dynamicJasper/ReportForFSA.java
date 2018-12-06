package report.dynamicJasper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import org.srh.db.RootCon;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports; 
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;


public class ReportForFSA implements ReportInterface {
	
 
	@Override
	public String generateQuery() {
		String querString = "";		
		String status = null;
		String deliveryType = null;
		String orderTimeFrom = null;
		String orderTimeTo = null;
		String qGen = "";
		String table_1 = "orders";
		String table_2 = "food";
		String select = "select customerusername, foodname, status, deliveryType, ordertime, foodprice, totalamount,"
				+ " (totalamount * foodprice) as total from mealsanddeals.";
		HashMap<String, String> queryArray = new HashMap<String, String>();
			
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Order Status:  [Open/Closed]");
		status = scanner.nextLine().trim();
		if (!"".equalsIgnoreCase(status)) {
			queryArray.put("status",status);
		}		
		System.out.println("Enter Delivery Type:  [Home/Pick]");
		deliveryType = scanner.nextLine().trim();
		if (!"".equalsIgnoreCase(deliveryType)) {
			queryArray.put("deliveryType", deliveryType);
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
			querString = select + table_1+ " where " + qGen+ " order by ordertime desc";
		}else {
			querString = select + table_1+ " order by ordertime desc";
		}
		
		//System.out.println(querString);
        scanner.close();
		return querString;

	}

	@Override
	public void generateReport(String query) {
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		
		        
		try {
			conn = RootCon.createConn();
			prStmt = conn.prepareStatement(query);

			rs = prStmt.executeQuery();
			
			JasperReportBuilder report = DynamicReports.report();//a new report
			//DynamicReportBuilder drb = new DynamicReportBuilder();
			
			report
			  .columns(
					  
			      Columns.column("Customer Name", "customerusername", DataTypes.stringType()),
			      Columns.column("Food Name", "foodname", DataTypes.stringType()),
			      Columns.column("Status", "status", DataTypes.stringType()),
			      Columns.column("Delivery Type", "deliveryType", DataTypes.stringType()),
			      Columns.column("Order Time", "ordertime", DataTypes.stringType()),
			      Columns.column("Food Price", "foodprice", DataTypes.floatType())
			      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
			      Columns.column("Total Amount", "totalamount", DataTypes.floatType())
			      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
			      Columns.column("Income", "total", DataTypes.floatType())
			      .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
			      
			      ).highlightDetailEvenRows()
 			  .title(//title of the report
			      Components.text("Food Service Agent Report"))
				  .pageFooter(Components.pageXofY())//show page number on the page footer
				  .setDataSource(query, conn);	
			
			/*drb.addGlobalFooterVariable(, 
					DJCalculation.SUM);
			*/
			report.show();

						
			if (rs == null) {
				System.out.println("No result set found");
				throw new NullPointerException();
			}
			
			boolean isEmpty = true;

			// Will run the loop till the end of the record in the table
			while (rs.next()) {
				isEmpty = false;
				//System.out.println("Name:" + rs.getString("name") + " || Username:" + rs.getString("customerUsername"));
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

}
