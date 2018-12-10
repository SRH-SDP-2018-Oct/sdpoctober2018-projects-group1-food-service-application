package mealsanddeals.foodservice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Ranking {
	private long rankingid;
	private String customerusername;
	private String fsausername;
	private float quality;
	private float price;
	private float time;
	private float service;
	private float behaviour;
	private float availablity;
	private float responsibility;
	private Order order;
	
	public Ranking() {
		
	}
	
	public Ranking(long rankingid, String customerUsername, String fsaUsername) {
		
		this.rankingid = rankingid;
		this.customerusername = customerUsername;
		this.fsausername = fsaUsername;
		
	}
	
	public Ranking(float quality, float price, float time, float service, float behaviour, float availability, float responsibility, String fsausername) {
		this.quality = quality;
		this.price = price;
		this.time = time;
		this.service = service;
		this.behaviour = behaviour;
		this.availablity = availability;
		this.responsibility = responsibility;
		this.fsausername = fsausername;
	}
	
	public long getRankingid() {
		return rankingid;
	}
	public void setRankingid(String rankingid) {
		this.rankingid = Long.valueOf(rankingid);
	}
	public String getCustomerusername() {
		return customerusername;
	}
	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}
	public String getFsausername() {
		return fsausername;
	}
	public void setFsausername(String fsausername) {
		this.fsausername = fsausername;
	}
	public float getQuality() {
		return quality;
	}
	public void setQuality(float quality) {
		this.quality = quality;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price =price;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public float getService() {
		return service;
	}
	public void setService(float service) {
		this.service = service;
	}
	public float getBehaviour() {
		return behaviour;
	}
	public void setBehaviour(float behaviour) {
		this.behaviour = behaviour;
	}
	public float getAvailablity() {
		return availablity;
	}
	public void setAvailablity(float availablity) {
		this.availablity = availablity;
	}
	public float getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(float responsibility) {
		this.responsibility = responsibility;
	}
	
	private float rankingSum;
	public ArrayList<Order> CustomerUnrankedOrders = new ArrayList();
	
	
	public boolean setupRanking(String customerusername) {
		ArrayList<Order>customerUnrankedOrders = new ArrayList();
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		
		String query = "select * from orders where (customerusername ='"+customerusername +"') and (ranking ='"+ RankingStatus.Unranked + "')and( status ='" + Status.Closed+"')";
		try {
			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement(query);  
			rs = prStmt.executeQuery();
			
			while(rs.next()) {
				customerUnrankedOrders.add(new Order(rs.getLong("orderid"),rs.getString("fsausername"),rs.getString("customerusername"),rs.getDate("ordertime")));
				System.out.println("Order id: " + rs.getLong("orderid") + "|| FSA Username: " + rs.getString("fsausername") + "|| Customerusername: " + rs.getString("customerusername") + "|| Date of ordering: " + rs.getDate("ordertime"));			
			}	
			
			for(int i = 0; i<customerUnrankedOrders.size();i++) {
				System.out.println(customerUnrankedOrders.get(i).getCustomerusername() + " You have not ranked your order from " + customerUnrankedOrders.get(i).getOrdertime() );
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		CustomerUnrankedOrders = customerUnrankedOrders;
		
		return true;
	}
	public boolean rankingHandler(List<Ranking> rankingValues,String customerUsername) {
	
		addRanking(rankingValues,customerUsername);
		for(int i=0;i<CustomerUnrankedOrders.size();i++ ) {
			changeUnrankedStatus(CustomerUnrankedOrders.get(i));
			
		}
		try {
			while(!CustomerUnrankedOrders.isEmpty()) {
				int i = CustomerUnrankedOrders.size();
				CustomerUnrankedOrders.remove(i);
				i-=CustomerUnrankedOrders.size();
			}
		}
		finally {
			
		}
		
		if(CustomerUnrankedOrders.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private void changeUnrankedStatus(Order unrankedCustomerOrder) {
		Connection conn = null;
		PreparedStatement prStmt = null;
		
		if(unrankedCustomerOrder.getRanking() == RankingStatus.Unranked) {
			unrankedCustomerOrder.setRanking(RankingStatus.Ranked);
			String query = "Update orders set ranking = '"+ unrankedCustomerOrder.getRanking()+"' where customerusername ='" + unrankedCustomerOrder.getCustomerusername()+"'";
			
			try {
				conn = MysqlCon.createConn();
				prStmt = conn.prepareStatement(query);  
				prStmt.executeUpdate();
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
			System.out.println("Order with ID " +unrankedCustomerOrder.getOrderid() + " changed from Unranked to Ranked");
		}
	}
	
	
	private ArrayList<Ranking> rankings = new ArrayList();
	/*private ArrayList<Ranking> storeFSARanking(ArrayList<Order> unrankedCustomerOrders ) {
		Scanner reader = new Scanner(System.in);
		float customerInput = reader.nextFloat();
		float[]rankingValues = new float[6];
		for(int i=0; i<unrankedCustomerOrders.size();i++) {
			System.out.println("Dear "+ unrankedCustomerOrders.get(i)+"you haven't ranked some orders yet. To proceed with a new order you have to rank the old ones first" );
			System.out.println("Rank quality from 1 to 5 (floating numbers are also possible");
		}
		for(int j = 0; j<rankingValues.length;j++) {
			customerInput = reader.nextFloat();
			
		}
		reader.close();
		return rankings;
	}*/
	private void addRanking(List<Ranking> rankingValues,String customername) {
		Connection conn = null;
		PreparedStatement prStmt = null;
		//insert into ranking values (null,'jisu123','ash3','2','2','2','2','2','3','4');
		for (int i=0;i<rankingValues.size();i++) {
			String query ="insert into ranking values("+"'0',"+"'"
		+customername+"','"+rankingValues.get(i).getFsausername()+"','"
		+rankingValues.get(i).getQuality()+"','"+rankingValues.get(i).getPrice()+"','"+rankingValues.get(i).getTime()
		+"','"+rankingValues.get(i).getService()+"','"
		+rankingValues.get(i).getBehaviour()+"','"
		+rankingValues.get(i).getAvailablity()+"','"
		+rankingValues.get(i).getResponsibility()+"')"; 
			try {
				conn = MysqlCon.createConn();
				prStmt = conn.prepareStatement(query);  
				prStmt.executeUpdate();
				}
			catch(Exception e) {
				System.out.println(e.toString());
			}
			System.out.println(query);
		}
	}
	
	
}
