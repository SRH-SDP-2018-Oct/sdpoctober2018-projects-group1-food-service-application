package mealsanddeals.foodservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public boolean orderHandler(String customername, int userInput) {
		switch(userInput) {
		case 1:
			//Add order
			addOrder();
			break;
		case 2:
			//Edit order
			editOrder(customername);
			break;
		case 3: 
			//Delete order
			deleteOrder(customername);
			break;
			default:
				return true;
				
		}
		return true;
	}
	
	private Order addOrder() {
		//TODO Select order parameters; Confirm order parameters; Take parameters to query(Add order to Datatable); Continue with Payment
		/* Zur Liste hinzugefügt werden müssen:
		 * orderid, 
		 * ordertime, 
		 * deliverytype (food table), 
		 * foodprice = price, 
		 * deliverycharge, 
		 * totalamount, 
		 * orderlocation, 
		 * paymenttype (food table), 
		 * fsausername (food table), 
		 * customerusername, 
		 * foodid (food table), 
		 * ranking, 
		 * status
		 * 
		 * inner join von food table (FSA Username + food id)*/
		return new Order();
	}
	
	private ArrayList<Order> editOrder(String customerusername) {
		//TODO Show current open orders; Select order that should be edited; Change Parameters of Orders; Take Query to Update order on database
		showOpenOrders(customerusername);
		Connection conn = null;
		PreparedStatement prStmt = null;
		ResultSet rs = null;
		int inputValue = 1;
		
			String query = "select * from orders inner join food on orders.foodid = food.foodid where customerusername ='"+ customerusername+"' and status ='"+Status.Open+"' and ranking ='"+RankingStatus.Unranked+"' "
					+ "and orderid='"+ inputValue+"'";
			try {
				conn = MysqlCon.createConn();
				prStmt = conn.prepareStatement(query);  
				rs = prStmt.executeQuery();
				
				while(rs.next()) {
					System.out.println("Order id: " + rs.getLong("orderid") + "|| FSA Username: " + rs.getString("fsausername") + "|| Customerusername: " + rs.getString("customerusername") + "|| Date of ordering: " + rs.getDate("ordertime")
					+"|| Ordered amount: "+rs.getInt("totalamount")+"|| Name of meal:" +rs.getString("nameofmeal")+"|| Foodname: "+rs.getString("foodname")+"|| Foodtype: "+rs.getString("foodtype"));
				}	
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		return openOrders;
	}
	
	private List<Order> deleteOrder(String customername) {
		// TODO Show current open orders; Select order to delete; Confirm Delete; Take Query to Update ordertable
		//Select order by id
	
		showOpenOrders(customername);
		return openOrders;
	}
	
	//WIEDER PRIVATE MACHEN
	
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
				// Bestelltes Essen muss noch angezeigt werden 
			}	
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return openOrders;
	}
}
