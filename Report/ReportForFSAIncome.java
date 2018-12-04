package report.dynamicJasper;

import java.io.FileNotFoundException;


public class ReportForFSAIncome {
	
	public static void main(String[] args) {
		ReportForFSAIncome reportFSAIncome = new ReportForFSAIncome();
		String newQuery = reportFSAIncome.generateQuery();		
		reportFSAIncome.generateReport(newQuery);
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
			      Columns.column("Time Frame", "timeframe", DataTypes.stringType()),
			      Columns.column("Total Income", "totalincome", DataTypes.stringType()),			  
			      )
			  .title(//title of the report
			      Components.text("Food Service Agent Income Report"))
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
