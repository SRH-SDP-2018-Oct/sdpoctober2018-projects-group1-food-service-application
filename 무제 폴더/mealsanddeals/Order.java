package com.srh_heidelberg.mealsanddeals;

import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

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
	private String foodname;
	private String nationality;
	
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
	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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
	public void orderHandlerAddOrder(Customer loggedInCustomer, Date selectedDate) throws NoSuchAlgorithmException, SQLException {
		Scanner reader = new Scanner(System.in);
		System.out.println("For adding a new order please enter the food id");
		int customerInput = reader.nextInt();
		System.out.println("For adding a new order please enter the amount you want to order");
		int customerInputAmount = reader.nextInt();
		System.out.println("For adding a new order please enter the Payment type(0:cash 1:online)");
		int customerInputPaymentType = reader.nextInt();
		System.out.println("For adding a new order please enter the Delivery option(Yes/No)");
		String customerInputDeliveryOption = reader.next();
		
		ArrayList<Food> foodlist = new ArrayList<Food>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String[]> detail = new ArrayList<String[]>();
		detail.add(new String[] {"date", dateFormat.format(selectedDate)});
		detail.add(new String[] {"foodid", String.valueOf(customerInput)});
		MysqlCon.selectFood("food", detail, foodlist);
		addOrder(loggedInCustomer,foodlist.get(0),customerInputAmount,customerInputPaymentType, customerInputDeliveryOption);
	}
	
	private Order addOrder(Customer loggedInCust, Food selectedFood, int amount, int paymentType, String deliveryOption) throws NoSuchAlgorithmException, SQLException {
		
		ArrayList<String> detail = new ArrayList();
		PaymentType type;
		String status = "";
		if(paymentType == 0) {
			type  = PaymentType.Cash;
			status = "Open";				
		}
		else {	
			type  = PaymentType.Online;
			status = "Closed";
		}
		
		String query="'0'"+"'"+selectedFood.getDate()+"''"
		 +selectedFood.getDeliveryoption()+"''"+selectedFood.getTotalamount()+"''"+selectedFood.getPrice()
		 +"''"+selectedFood.getFasusername()+"''"+loggedInCust.getCustomerusername()+"''" +selectedFood.getFoodid()+"'";
		detail.add("0");
		detail.add(selectedFood.getDate());
		detail.add(deliveryOption);
		detail.add(Float.toString(selectedFood.getPrice()*amount));
		if(deliveryOption.equals("Yes"))
			detail.add(Float.toString(((selectedFood.getPrice()*amount)*5)/100));
		else
			detail.add("0");
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
	
		decreaseAmount(amount,selectedFood.getAvailable(),selectedFood.getFoodid());
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
	public int getAvailable(Order order) {
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		String query = "select available from food where foodid =" + order.getFoodid();
		
		int num=0;
		try {
			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement(query);  
			rs = prStmt.executeQuery();
			while(rs.next()) {
				num = rs.getInt("available");
				return num;
			}
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return num;
	}
	public void editOrder(Order order) throws SQLException {
		ArrayList<String[]> detail = new ArrayList();
		ArrayList<String[]> condition = new ArrayList();
	
		ArrayList<String[]> column = new ArrayList();
		column.add(new String[] {"Food Amount", String.valueOf(order.getTotalamount())});
		column.add(new String[] {"Delivery Option", order.getDeliverytype()});
		column.add(new String[] {"Payment Type", String.valueOf(order.getPaymenttype())});
		
		for(int i=0; i<column.size(); i++) {
			System.out.print("[" + (i+1) +"]" + column.get(i)[0] +": "+ column.get(i)[1] + "\t");
			if((i+1)%5==0)
				System.out.println("");		
		}
		int num = 0;
		
		int avail = getAvailable(order);
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("\nChange Amount of Food : ");
			num = sc.nextInt();
			if(avail+Integer.valueOf(order.getTotalamount())-num>0) {
				int foodprice = (int) (order.getFoodprice()/order.getTotalamount() * num);
				detail.add(new String[] {"totalamount", String.valueOf(num)});
				detail.add(new String[] {"foodprice", String.valueOf(foodprice)});
				
				increaseAmount(Integer.valueOf(order.getTotalamount())-num, avail, order.getFoodid());
				break;
			}
			else System.out.println("WARNING! The Food Amount is NOT Available!");
		}
		
		String editinfo="";
		Scanner sc2 = new Scanner(System.in);
		System.out.print("\nChange Delivery Option(Yes/No) : ");
		editinfo = sc2.nextLine();
		if(editinfo.equals("No"))
			detail.add(new String[] {"deliverycharge", "0"});
		else
			detail.add(new String[] {"deliverycharge", String.valueOf(Float.valueOf(foodprice)/100*5)});
		detail.add(new String[] {"deliverytype", editinfo});
		
		String editinfo2="";
		Scanner sc3 = new Scanner(System.in);
		System.out.print("\nChange Payment Type(Cash/Online) : ");
		editinfo2 = sc3.nextLine();
		detail.add(new String[] {"paymenttype", editinfo2});
		if(editinfo2.equals("Cash"))
			detail.add(new String[] {"status", "Open"});
		else
			detail.add(new String[] {"status", "Closed"});
		
		condition.add(new String[]{"orderid", String.valueOf(order.getOrderid())});

		MysqlCon.updateTable("orders", detail, condition);
		
		showOpenOrders(loggedInCust);
	}
	
	public void deleteOrder(Order order) throws SQLException {
		Scanner reader = new Scanner(System.in);
		ArrayList<String[]> detail = new ArrayList();
		detail.add(new String[]{"orderid",String.valueOf(order.getOrderid())});//<-- Make this general
		MysqlCon.deleteFromTable("orders", detail);
		int avail = getAvailable(order);
		increaseAmount(order.getTotalamount(), avail, order.getFoodid());
		showOpenOrders(loggedInCust);
	}
	public void selectOrder(Customer loggedInCustomer) throws SQLException{
		loggedInCust = loggedInCustomer;
		showOpenOrders(loggedInCust);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Order ID : ");
		int id = sc.nextInt();
		
		Order ordernode = new Order();
		
		int i=0;
		while(i<openOrders.size()) {
			if(openOrders.get(i).getOrderid()==id) {
				ordernode = openOrders.get(i);
				break;
			}
			else {
				i++;
			}
		}
		System.out.println(ordernode.getFoodname());
		System.out.println("-------------\n1: Edit a Current Order\n2: Delete a Current Order\n3: Add a New Order");
		int n = sc.nextInt();
	
		switch(n) {
		case 1: editOrder(ordernode);
				showOpenOrders(loggedInCust);
		case 2: deleteOrder(ordernode);
				showOpenOrders(loggedInCust);
		}
	}

	
	public void showOpenOrders(Customer loggedInCustomer) {
		openOrders = new ArrayList<Order>();
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		String query = "select * from orders inner join food on orders.foodid = food.foodid where customerusername ='"+ loggedInCustomer.getCustomerusername()+"' and status ='"+Status.Open+"' and ranking ='"+RankingStatus.Unranked+"'";
		
		int k=1;
		try {
			conn = MysqlCon.createConn();
			prStmt = conn.prepareStatement(query);  
			rs = prStmt.executeQuery();
			
			while(rs.next()) {
				Order newOrder = new Order();
				k=1;
				newOrder.setOrderid(rs.getLong(k++));
				newOrder.setOrdertime(rs.getDate(k++));
				newOrder.setDeliverytype(rs.getString(k++));
				newOrder.setFoodprice(rs.getFloat(k++));
				newOrder.setDeliverycharge(rs.getFloat(k++));
				newOrder.setTotalamount(rs.getShort(k++));
				newOrder.setOrderlocation(rs.getString(k++));
				String payment = rs.getString(k++);
				if(payment.equals("Cash"))
					newOrder.setPaymenttype(PaymentType.Cash);
				else
					newOrder.setPaymenttype(PaymentType.Online);
				newOrder.setFsausername(rs.getString(k++));
				newOrder.setCustomerusername(rs.getString(k++));
				newOrder.setFoodid(rs.getLong(k++));
				String ranking = rs.getString(k++);
				newOrder.setRanking(RankingStatus.Unranked);
				String status = rs.getString(k++);
				newOrder.setStatus(Status.Open);
				newOrder.setFoodname(rs.getString(k++));
				newOrder.setNationality(rs.getString(k++));
				openOrders.add(newOrder);
				
				//i++;
				//openOrders.add(new Order(rs.getLong("orderid")));
			}	
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		for(int i=0; i<openOrders.size(); i++) {
			System.out.println("---ORDER LIST---");
			System.out.println("[Order id : " + openOrders.get(i).getOrderid() + "] || FSA Username: " + openOrders.get(i).getFsausername() + "|| Customerusername: " + openOrders.get(i).getCustomerusername() + "|| Date of ordering: " + openOrders.get(i).getOrdertime()
			+"|| Ordered amount: "+openOrders.get(i).getTotalamount()+"|| Foodname: "+openOrders.get(i).getFoodname()+"|| Foodtype: "+openOrders.get(i).getNationality());
		}
		//return openOrders;
	}

}
