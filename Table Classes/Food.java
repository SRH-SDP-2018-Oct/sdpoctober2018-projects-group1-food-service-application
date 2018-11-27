import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Food {
	private char nameofmeal;
	private String foodname;
	private String foodtype;
	private String hotorcold;
	private Byte available;
	private Byte totalamount;
	private float price;
	private char deliveryoption;
	private char cash;
	private char onilne;
	private Date date;
	private Date dateofadding; // need change -> Datetime
	private long foodid;
	private String fasusername;
	
	public char getNameofmeal() {
		return nameofmeal;
	}
	public void setNameofmeal(String nameofmeal) {
		this.nameofmeal = nameofmeal.charAt(0);
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
	public char getDeliveryoption() {
		return deliveryoption;
	}
	public void setDeliveryoption(String deliveryoption) {
		this.deliveryoption = deliveryoption.charAt(0);
	}
	public char getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash.charAt(0);
	}
	public char getOnilne() {
		return onilne;
	}
	public void setOnilne(String onilne) {
		this.onilne = onilne.charAt(0);
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
