package report.dynamicJasper;

public interface ReportInterface {
	void generateQuery(String username);
	void generateReport(String query, String username);
}
