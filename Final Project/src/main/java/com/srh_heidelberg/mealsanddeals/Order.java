package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Order {

	private long orderid;
	private Date ordertime;
	private String deliverytype;
	private float foodprice;
	private float deliverycharge;
	private short totalamount;
	private String orderlocation;
	private PaymentType paymenttype;
	private String fsausername;
	private String customerusername;
	private long foodid;
	private RankingStatus ranking;
	private Status status;
	private static Customer loggedInCust = new Customer();
	
	public Order(long orderid, Date ordertime, String deliverytype, float foodprice, float deliverycharge, short totalamount, String orderlocation, 
			PaymentType paymenttype, String fsausername, String customerusername, long foodid, RankingStatus ranking, Status status) {
		this.orderid = orderid;
		this.ordertime = ordertime;
		this.deliverytype = deliverytype;
		this.foodprice = foodprice;
		this.deliverycharge = deliverycharge;
		this.totalamount = totalamount;
		this.orderlocation = orderlocation;
		this.paymenttype = paymenttype;
		this.fsausername = fsausername;
		this.customerusername = customerusername;
		this.foodid = foodid;
		this.ranking = ranking;
		this.status = status;
	}
	
	public Order(long orderid, String fsausername, String customerusername, Date ordertime) {
		this.orderid = orderid;
		this.fsausername = fsausername;
		this.customerusername = customerusername;
		this.ordertime = ordertime;
		this.setRanking(RankingStatus.Unranked);
	}
	
	public Order() {
		
	}
	
	public Order(long orderid) {
		this.orderid = orderid;
	}

	
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getDeliverytype() {
		return deliverytype;
	}
	public void setDeliverytype(String deliverytype) {
		this.deliverytype = deliverytype;
	}
	public float getFoodprice() {
		return foodprice;
	}
	public void setFoodprice(float foodprice) {
		this.foodprice = foodprice;
	}
	public float getDeliverycharge() {
		return deliverycharge;
	}
	public void setDeliverycharge(float deliverycharge) {
		this.deliverycharge = deliverycharge;
	}
	public short getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(short totalamount) {
		this.totalamount = totalamount;
	}
	public String getOrderlocation() {
		return orderlocation;
	}
	public void setOrderlocation(String orderlocation) {
		this.orderlocation = orderlocation;
	}
	public PaymentType getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(PaymentType paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getFsausername() {
		return fsausername;
	}
	public void setFsausername(String fsausername) {
		this.fsausername = fsausername;
	}
	public String getCustomerusername() {
		return customerusername;
	}
	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}
	public long getFoodid() {
		return foodid;
	}
	public void setFoodid(long foodid) {
		this.foodid = foodid;
	}
	public RankingStatus getRanking() {
		return ranking;
	}
	public void setRanking(RankingStatus ranking) {
		this.ranking = ranking;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}	
	
	public ArrayList<Order> CustomerUnrankedOrders = new ArrayList();
	
	public String selectUnrankedOrdersFromUser(String customerusername) {
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
			
			CustomerUnrankedOrders = customerUnrankedOrders;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return query;
	}
	
	
	private ArrayList<Order> openOrders = new ArrayList();
	public boolean orderHandlerAddOrder(Customer loggedInCustomer, Food selectedFood, int selectedAmount, int paymentType) throws NoSuchAlgorithmException, SQLException {
		loggedInCust = loggedInCustomer;
		addOrder(selectedFood,selectedAmount,paymentType);
		return true;
	}
	
	private Order addOrder(Food selectedFood,int amount, int paymentType) throws NoSuchAlgorithmException, SQLException {
		
		ArrayList<String> detail = new ArrayList();
		PaymentType type;
		String status = "";
		if(paymentType == 0) {
			type  = PaymentType.Cash;
			status = "Closed";			
		}
		else {
			type  = PaymentType.Online;
			status = "Open";			
		}
		
		String query="'0'"+"'"+selectedFood.getDate()+"''"
		 +selectedFood.getDeliveryoption()+"''"+selectedFood.getTotalamount()+"''"+selectedFood.getPrice()
		 +"''"+selectedFood.getFasusername()+"''"+loggedInCust.getCustomerusername()+"''" +selectedFood.getFoodid()+"'";
		detail.add("0");
		detail.add(selectedFood.getDate());
		detail.add(selectedFood.getDeliveryoption());
		detail.add(Float.toString(selectedFood.getPrice()*amount));
		detail.add(Float.toString(((selectedFood.getPrice()*amount)*5)/100));
		detail.add(Integer.toString(amount));
		detail.add(loggedInCust.getAddress());
		detail.add(type.toString());
		detail.add(selectedFood.getFasusername());
		detail.add(loggedInCust.getCustomerusername());
		detail.add(Long.toString(selectedFood.getFoodid()));
		detail.add("Unranked");
		detail.add(status);
		detail.add(selectedFood.getFoodname());
		detail.add(selectedFood.getFoodtype());
		
	MysqlCon.insertToTable("orders", detail);
	
		decreaseAmount(amount,selectedFood.getTotalamount(),selectedFood.getFoodid());
		//TODO Select order parameters; Confirm order parameters; Take parameters to query(Add order to Datatable); Continue with Payment
		
		
		return new Order();
	}
	
	private boolean decreaseAmount(int amount,int totalAmount,long foodid) throws SQLException {
		
		ArrayList<String[]> detail = new ArrayList();
		ArrayList<String[]> condition = new ArrayList();
		int newAmount = totalAmount-amount;
		condition.add(new String[] {"foodid",Long.toString(foodid)});
		detail.add(new String[] {"available",Integer.toString(newAmount)});
		MysqlCon.updateTable("food", detail, condition);
		
		return true;
		
	}

	private boolean increaseAmount(int amount,int totalAmount,long foodid) throws SQLException {
		
		ArrayList<String[]> detail = new ArrayList();
		ArrayList<String[]> condition = new ArrayList();
		int newAmount = totalAmount+amount;
		condition.add(new String[] {"foodid",Long.toString(foodid)});
		detail.add(new String[] {"available",Integer.toString(newAmount)});
		MysqlCon.updateTable("food", detail, condition);
		
		return true;
		
	}

	public boolean editOrder(String customerusername,int id, int amount) throws SQLException {
		
		
		ArrayList<String[]> detail = new ArrayList();
		ArrayList<String[]> condition = new ArrayList();
		
		System.out.println(openOrders.get(id).getFoodprice());
		System.out.println(openOrders.get(id).getDeliverycharge());
		System.out.println(openOrders.get(id).getTotalamount());
		
		detail.add(new String[] {"foodprice",Float.toString(openOrders.get(id).getDeliverycharge()*amount/100*5)});
		detail.add(new String[] {"deliverycharge",Float.toString(openOrders.get(id).getDeliverycharge()*amount)});
		detail.add(new String[] {"totalamount",Integer.toString(amount)});
		condition.add(new String[]{"orderid",Integer.toString(id)});

		MysqlCon.updateTable("orders", detail, condition);
		showOpenOrders(customerusername);
		return true;
	}
	
	public boolean deleteOrder(String customername,int id) {
		
		
		ArrayList<String[]> detail = new ArrayList();
		
		detail.add(new String[]{"orderid",Integer.toString(id)});//<-- Make this general
		
		MysqlCon.deleteFromTable("orders", detail);
		openOrders.remove(id);
		showOpenOrders(customername);
		return true;
	}
	
	public ArrayList<Order> showOpenOrders(String customerusername) {
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		String query = "select * from orders inner join food on orders.foodid = food.foodid where customerusername ='"+ customerusername+"' and status ='"+Status.Open+"' and ranking ='"+RankingStatus.Unranked+"'";
		int i=1;
		try {
			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement(query);  
			rs = prStmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("Order id: " + rs.getLong("orderid") + "|| FSA Username: " + rs.getString("fsausername") + "|| Customerusername: " + rs.getString("customerusername") + "|| Date of ordering: " + rs.getDate("ordertime")
				+"|| Ordered amount: "+rs.getInt("totalamount")+"|| Name of meal:" +rs.getString("nameofmeal")+"|| Foodname: "+rs.getString("foodname")+"|| Foodtype: "+rs.getString("foodtype")+ " || For selection press: ["+i+"]");
				
				i++;
				openOrders.add(new Order(rs.getLong("orderid")));
			}	
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return openOrders;
	}
}
