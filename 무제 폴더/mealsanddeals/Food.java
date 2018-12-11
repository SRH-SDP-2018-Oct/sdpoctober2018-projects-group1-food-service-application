package com.srh_heidelberg.mealsanddeals;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;

public class Food {
	private String nameofmeal;
	private String foodname;
	private String foodtype;
	private String hotorcold;
	private byte available;
	private byte totalamount;
	private float price;
	private String deliveryoption;
	private String cash;
	private String online;
	private String date;
	private String dateofadding;
	private long foodid;
	private String fsausername;
	
	public String getNameofmeal() {
		return nameofmeal;
	}
	public void setNameofmeal(String nameofmeal) {
		this.nameofmeal = nameofmeal;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public String getFoodtype() {
		return foodtype;
	}
	public void setFoodtype(String foodtype) {
		this.foodtype = foodtype;
	}
	public String getHotorcold() {
		return hotorcold;
	}
	public void setHotorcold(String hotorcold) {
		this.hotorcold = hotorcold;
	}
	public Byte getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = Byte.valueOf(available);
	}
	public Byte getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = Byte.valueOf(totalamount);
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = Float.valueOf(price);
	}
	public String getDeliveryoption() {
		return deliveryoption;
	}
	public void setDeliveryoption(String deliveryoption) {
		this.deliveryoption = deliveryoption;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date)  {
		this.date = date;
	}
	public String getDateofadding() {
		return dateofadding;
	}
	public void setDateofadding(String dateofadding) {
		this.dateofadding = dateofadding;
	}
	public long getFoodid() {
		return foodid;
	}
	public void setFoodid(String foodid) {
		this.foodid = Long.valueOf(foodid);
	}
	public String getFasusername() {
		return fsausername;
	}
	public void setFasusername(String fasusername) {
		this.fsausername = fasusername;
	}
	public Field[] getFields() throws ClassNotFoundException {
		Class food = this.getClass();
		Field[] fieldlist = food.getDeclaredFields();
		return fieldlist;
	}
	public void setFood(Date date, FSA loggedInFsa) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Scanner sc = new Scanner(System.in);
		Food newFood = new Food();
		
		Field[] fieldlist;
		
		fieldlist = getFields();
		for(int i=0; i<fieldlist.length; i++) {
			if(fieldlist[i].getName().equals("date")) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fieldlist[i].set(this, dateFormat.format(date));
			} else if(fieldlist[i].getName().equals("dateofadding")){
				long time = System.currentTimeMillis(); 
				DateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				fieldlist[i].set(this, dayTime.format(new Date(time)));
			} else if(fieldlist[i].getName().equals("fsausername")) {
				fieldlist[i].set(this, loggedInFsa.getFsausername()); 
			} else if(fieldlist[i].getName().equals("foodid")) {
				fieldlist[i].set(this, 0);//auto-increment
			} else if(fieldlist[i].getName().equals("totalamount")) {
				int j=i;
				fieldlist[i].set(this, this.getAvailable());
			}
			else {
				String printstr="";
				if(fieldlist[i].getName().equals("nameofmeal")) {
					System.out.println("Enter Name of Meal (Breakfast/Lunch/Dinner) : ");
					String input = "";
					input = sc.next();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("foodname")) {
					System.out.println("Enter Name of Food : ");
					String input = "";
					input = sc.next();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("foodtype")) {
					System.out.println("Enter Type of Food(ex. German, French, Italian) : ");
					String input = "";
					input = sc.next();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("hotorcold")) {
					System.out.println("Enter Hot or Cold(Hot/Cold) : ");
					String input = "";
					input = sc.next();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("available")) {
					System.out.println("Enter Available Amount of Food : ");
					byte input = 0;	
					input = sc.nextByte();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("price")) {
					System.out.println("Enter Price of Food : ");
					float input = 0;
					input = sc.nextFloat();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("deliveryoption")) {
					System.out.println("Enter Delivery Type(Yes/No) : ");
					String input = "";
					input = sc.next();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("cash")) {
					System.out.println("Enter Payment with Cash(Yes/No) : ");
					String input = "";
					input = sc.next();
					fieldlist[i].set(this, input);
				}
				else if(fieldlist[i].getName().equals("online")) {
					System.out.println("Enter Payment with Online(Yes/No) : ");
					String input = "";
					input = sc.next();
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
	public ArrayList<Field> getFood() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Field[] fieldlist = getFields();
		ArrayList<Field> column = new ArrayList<>(Arrays.asList(fieldlist));
		column.remove(column.size()-1);
		column.remove(column.size()-1);
		column.remove(column.size()-1);
		for(int i=0; i<column.size(); i++) {
			System.out.print("[" + (i+1) +"]" + column.get(i).getName() +": "+ column.get(i).get(this) + "\t");
			if((i+1)%5==0)
				System.out.println("");		
		}
		return column;
	}
}
