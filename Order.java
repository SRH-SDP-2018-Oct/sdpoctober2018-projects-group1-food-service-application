package com.srh_heidelberg.mealsanddeals;

public class Order {

	private long orderid;
	private String ordertime;
	private String deliverytype;
	private float foodprice;
	private float deliverycharge;
	private short totalamount;
	private String orderlocation;
	private String paymenttype;
	private String fsausername;
	private String customerusername;
	private long foodid;
	private String[] ranking;
	private String[] status;

	
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
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
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
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
	public String[] getRanking() {
		return ranking;
	}
	public void setRanking(String[] ranking) {
		this.ranking = ranking;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	
}
