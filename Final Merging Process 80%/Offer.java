package com.srh_heidelberg.mealsanddeals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Offer {
	private long offerid;
	private long foodid;
	private Byte foodamount;
	private float discount;
	private String fsausername;
	private String date;
	
	public long getOfferid() {
		return offerid;
	}
	public void setOfferid(String offerid) {
		this.offerid = Long.valueOf(offerid);
	}
	public long getFoodid() {
		return foodid;
	}
	public void setFoodid(String foodid) {
		this.foodid = Long.valueOf(foodid);
	}
	public Byte getFoodamount() {
		return foodamount;
	}
	public void setFoodamount(String foodamount) {
		this.foodamount = Byte.valueOf(foodamount);
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = Float.valueOf(discount);
	}
	public String getFsausername() {
		return fsausername;
	}
	public void setFsausername(String fsausername) {
		this.fsausername = fsausername;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setOffer(ArrayList<String> offercolumn, ArrayList<String[]> offerinfo) {
		int i=0;
		offerinfo.add(new String[] { offercolumn.get(i++), String.valueOf(getFoodamount())});
		offerinfo.add(new String[] { offercolumn.get(i++), String.valueOf(getDiscount())});
		offerinfo.add(new String[] { offercolumn.get(i++), getDate()});
	}
}
