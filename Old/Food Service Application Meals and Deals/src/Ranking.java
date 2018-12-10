
public class Ranking {
	private long rankingid;
	private String customerusername;
	private String fsausername;
	private float quality;
	private float price;
	private float time;
	private float service;
	private float behaviour;
	private float availablity;
	private float responsibility;
	
	public long getRankingid() {
		return rankingid;
	}
	public void setRankingid(String rankingid) {
		this.rankingid = Long.valueOf(rankingid);
	}
	public String getCustomerusername() {
		return customerusername;
	}
	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}
	public String getFsausername() {
		return fsausername;
	}
	public void setFsausername(String fsausername) {
		this.fsausername = fsausername;
	}
	public float getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = Float.valueOf(quality);
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = Float.valueOf(price);
	}
	public float getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = Float.valueOf(time);
	}
	public float getService() {
		return service;
	}
	public void setService(String service) {
		this.service = Float.valueOf(service);
	}
	public float getBehaviour() {
		return behaviour;
	}
	public void setBehaviour(String behaviour) {
		this.behaviour = Float.valueOf(behaviour);
	}
	public float getAvailablity() {
		return availablity;
	}
	public void setAvailablity(String availablity) {
		this.availablity = Float.valueOf(availablity);
	}
	public float getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = Float.valueOf(responsibility);
	}
	
}
