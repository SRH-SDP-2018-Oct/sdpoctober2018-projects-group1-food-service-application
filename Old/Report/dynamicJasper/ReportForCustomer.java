package report.dynamicJasper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;




import org.srh.db.RootCon;

public class ReportForCustomer {
	
	public static void main(String[] args) {
		ReportForCustomer reportCust = new ReportForCustomer();
		String newQuery = reportCust.generateQuery();		
		reportCust.generateReport(newQuery);
		//System.out.println(reportCust.generateQuery());
	}
	
	public String generateQuery () {		
		String querString = "";
		String enteredCode = null;
		String paymentTypeCode = null;
		String deliveryTypeCode = null;
		String backInput = null;		
		
			
		Scanner scanner = new Scanner(System.in);		
		System.out.println("Enter Payment Type:  [o/c]");
		paymentTypeCode = scanner.nextLine().trim();
		
		System.out.println("Enter DeliveryType:  [y/n]");
		deliveryTypeCode = scanner.nextLine().trim();
				

    	if (!"".equalsIgnoreCase(paymentTypeCode) && !"".equalsIgnoreCase(deliveryTypeCode)) {
    		querString += "select * from mealsanddeals.order where paymenttype='"+paymentTypeCode+"' and deliverytype='"+deliveryTypeCode+"'";
    	}else if (!"".equalsIgnoreCase(paymentTypeCode)) {
    		querString += "select * from mealsanddeals.order where paymenttype='"+paymentTypeCode+"'";
    	}else if (!"".equalsIgnoreCase(deliveryTypeCode)) {
    		querString += "select * from mealsanddeals.order where deliverytype='"+deliveryTypeCode+"'";
    	} else {
    		querString += "select * from mealsanddeals.order";
    	}
		
        scanner.close();
		return querString;
		
	}
	
	public void generateReport(String query) {
						
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		
		        
		try {
			conn = RootCon.createConn();
			prStmt = conn.prepareStatement(query);

			rs = prStmt.executeQuery();
			
			JasperReportBuilder report = DynamicReports.report();//a new report
			
			report
			  .columns(
			      Columns.column("Order Id", "orderid", DataTypes.stringType()),
			      Columns.column("Order Time", "ordertime", DataTypes.stringType()),
			      Columns.column("Delivery Type", "deliverytype", DataTypes.stringType()),
			      Columns.column("Food Price", "foodprice", DataTypes.stringType()),
			      Columns.column("Delivery Charge", "deliverycharge", DataTypes.stringType()),
			      Columns.column("Total Quantity", "totalamount", DataTypes.stringType()),
			      Columns.column("Order Location", "orderlocation", DataTypes.stringType()),
			      Columns.column("Payment Type", "paymenttype", DataTypes.stringType()),
			      Columns.column("FSA Username", "fsausername", DataTypes.stringType()),
			      Columns.column("Customer Username", "customerusername", DataTypes.stringType()),
			      Columns.column("Food Id", "foodid", DataTypes.stringType())
			      )
			  .title(//title of the report
			      Components.text("Customer Report"))
				  .pageFooter(Components.pageXofY())//show page number on the page footer
				  .setDataSource(query, conn);
			
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
