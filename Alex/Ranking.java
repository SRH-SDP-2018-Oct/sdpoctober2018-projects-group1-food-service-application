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
	public float rankOrders(ArrayList<Order> unrankedCustomerOrders) {
		for(int i=0;i<unrankedCustomerOrders.size();i++ ) {
			//TODO: INSERT INTO Ranking Table + Calculate rankingSum
			System.out.println(unrankedCustomerOrders.get(i).getCustomerusername() + " You have not ranked your order from " + unrankedCustomerOrders.get(i).getOrdertime() );
			changeUnrankedStatus(unrankedCustomerOrders.get(i));
		}
		return rankingSum;
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
	private ArrayList<Ranking> storeFSARanking(ArrayList<Order> unrankedCustomerOrders ) {
		Scanner reader = new Scanner(System.in);
		float customerInput = reader.nextFloat();
		
		for(int i=0; i<unrankedCustomerOrders.size();i++) {
			System.out.println("Dear "+ unrankedCustomerOrders.get(i)+"you haven't ranked some orders yet. To proceed with a new order you have to rank the old ones first" );
			System.out.println("Rank quality from 1 to 5 (floating numbers are also possible");
			customerInput = reader.nextFloat();
			setQuality(customerInput);
			rankings.add(new Ranking(customerInput,customerInput,customerInput,customerInput,customerInput,customerInput,customerInput,unrankedCustomerOrders.get(i).getFsausername()));
			//TODO : Add Ranking parameters here 
		}
		return rankings;
	}
	
	
}
