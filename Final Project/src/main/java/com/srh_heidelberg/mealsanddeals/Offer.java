package com.srh_heidelberg.mealsanddeals;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Offer {
	private long offerid;
	private long foodid;
	private byte foodamount;
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
	/*public void setOffer(ArrayList<String> offercolumn, ArrayList<String[]> offerinfo) {
		int i=0;
		offerinfo.add(new String[] { offercolumn.get(i++), String.valueOf(getFoodamount())});
		offerinfo.add(new String[] { offercolumn.get(i++), String.valueOf(getDiscount())});
		offerinfo.add(new String[] { offercolumn.get(i++), getDate()});
	}*/
	public Field[] getFields() throws ClassNotFoundException {
		Class food = this.getClass();
		Field[] fieldlist = food.getDeclaredFields();
		return fieldlist;
	}
	public void setoffer(int foodid, Date date, FSA loggedInfsa) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
		Scanner sc = new Scanner(System.in);
		Offer newOffer = new Offer();
		
		Field[] fieldlist;
		
		fieldlist = getFields();
		for(int i=0; i<fieldlist.length; i++) {
			if(fieldlist[i].getName().equals("date")) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fieldlist[i].set(this, dateFormat.format(date));
			} else if(fieldlist[i].getName().equals("foodid")) {
				fieldlist[i].set(this, foodid);
			} else if(fieldlist[i].getName().equals("offerid")) {
				fieldlist[i].set(this, 0);
			} else if(fieldlist[i].getName().equals("fsausername")) {
				fieldlist[i].set(this, loggedInfsa.getFsausername()); 
			}
			else {
				if(fieldlist[i].getType() == byte.class){
					byte input = 0;
					System.out.print("Enter "+ fieldlist[i].getName() +">> ");
					input = sc.nextByte();
					fieldlist[i].set(this, input);
				} else if(fieldlist[i].getType() == float.class){
					float input = 0;
					System.out.print("Enter "+ fieldlist[i].getName() +">> ");
					input = sc.nextFloat();
					fieldlist[i].set(this, input);
				}
				
			}	
		}
	}
	public ArrayList<String> makeQuery() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		ArrayList<String> detail = new ArrayList<String>();
		Field[] fieldlist;
	
		fieldlist = getFields();
		for(int i=0; i<fieldlist.length; i++) {
			detail.add(String.valueOf(fieldlist[i].get(this)));
		}
		return detail;
	}
	public ArrayList<Field> getOffer() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Field[] fieldlist = getFields();
		ArrayList<Field> column = new ArrayList<>(Arrays.asList(fieldlist));
		column.remove(0);
		column.remove(0);
		column.remove(2);	
		for(int i=0; i<column.size(); i++) {	
			System.out.print("[" + (i+1) +"]" + column.get(i).getName() +": "+ column.get(i).get(this) + "\t");
		}
		return column;
	}
}
