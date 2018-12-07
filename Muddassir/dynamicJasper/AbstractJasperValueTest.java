package report.dynamicJasper;

public class AbstractJasperValueTest {
	public static void main(String[] args) {
		ReportInterface reportForCustomer = new ReportForCustomer();
		//String newQuery = "";		
		reportForCustomer.generateQuery("muddassir297");
		//reportForCustomer.generateReport(newQuery);
	}
}


