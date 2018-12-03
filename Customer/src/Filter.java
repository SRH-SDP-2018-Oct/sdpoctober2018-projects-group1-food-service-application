import java.sql.*;
public class Filter {
	public Filter() {	
		try {
			MysqlCon.createConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Filter(String[] cuisineFilter, String[] foodTypeFilter, String[] servedTypeFilter, String[]paymentTypeFilter, String[] deliveryTypeFilter) {
		this.cuisineFilter = cuisineFilter;
		this.foodTypeFilter = foodTypeFilter;
		this.servedTypeFilter = servedTypeFilter;
		this.paymentTypeFilter = paymentTypeFilter;
		this.deliveryTypeFilter = deliveryTypeFilter;
		
		//setFilterQuery(Filter filter);
	}
	public String[] cuisineFilter = {"GERMAN","ENGLISH","IRANIAN","INDIAN","SOUTHKOREAN"};
	public String[] foodTypeFilter = {"BREAKFAST","LUNCH","DINNER"};
	public String[] servedTypeFilter = {"HOT","COLD"};
	public String[] paymentTypeFilter = {"CASH", "ONLINE"};
	public String[] deliveryTypeFilter = {"NODELIVERY", "HOMEDELIVERY"};
	
	private String setQuery(/*Filter filter*/) {
		String query = "SELECT * FROM food" ;
		return query;
	}
	
	
	
}
