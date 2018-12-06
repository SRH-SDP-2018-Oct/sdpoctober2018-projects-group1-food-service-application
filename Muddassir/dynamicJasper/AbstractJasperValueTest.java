package report.dynamicJasper;

public class AbstractJasperValueTest {
	public static void main(String[] args) {
		ReportInterface reportForCustomer = new ReportForCustomer();
		String newQuery = "";
		
		newQuery = reportForCustomer.generateQuery();
		reportForCustomer.generateReport(newQuery);
	}
}


