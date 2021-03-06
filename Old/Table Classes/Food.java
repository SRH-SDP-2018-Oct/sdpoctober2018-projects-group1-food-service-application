import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Food {
	private String nameofmeal;
	private String foodname;
	private String foodtype;
	private String hotorcold;
	private Byte available;
	private Byte totalamount;
	private float price;
	private String deliveryoption;
	private String cash;
	private String online;
	private Date date;
	private Date dateofadding; // need change -> Datetime
	private long foodid;
	private String fasusername;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		this.date = (Date) formatter.parse(date);
	}
	public Date getDateofadding() {
		return dateofadding;
	}
	public void setDateofadding(String dateofadding) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		this.dateofadding = (Date) formatter.parse(dateofadding);
	}
	public long getFoodid() {
		return foodid;
	}
	public void setFoodid(String foodid) {
		this.foodid = Long.valueOf(foodid);
	}
	public String getFasusername() {
		return fasusername;
	}
	public void setFasusername(String fasusername) {
		this.fasusername = fasusername;
	}
}
