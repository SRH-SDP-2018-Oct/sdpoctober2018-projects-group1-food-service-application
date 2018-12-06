package mealsanddeals.foodservice;

import java.util.Vector;

public class RankingPage {
	
	public RankingPage(String cust, String[] orders) {
		Vector<String> urorders = new Vector();
		for (int i = 0; i<=orders.length;i++) {
			if(orders[i] =="nr") {
				urorders.add(orders[i]);
			}
		}
	}
	Ranking rankingAttributes = new Ranking();
	String[] orders = {"r","nr","nr","nr","r","r","r","nr","r"};
	String cust1 = "A";
	String cust2 ="B";
	
	public void checkReceivedOrders(Customer customer) {
		RankingPage page = new RankingPage(cust1,orders);
		
	}

}
