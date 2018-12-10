
public class Offer {
	private long offerid;
	private long foodid;
	private Byte foodamount;
	private float discount;
	private String fsausername;
	
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
}
