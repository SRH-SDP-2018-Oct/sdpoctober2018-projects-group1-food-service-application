import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Notification {
	private long notificationid;
	private String senderusername;
	private String receiverusername;
	private String subject;
	private String content;
	private Date senddate;
	
	public long getNotificationid() {
		return notificationid;
	}
	public void setNotificationid(String notificationid) {
		this.notificationid = Long.valueOf(notificationid);
	}
	public String getSenderusername() {
		return senderusername;
	}
	public void setSenderusername(String senderusername) {
		this.senderusername = senderusername;
	}
	public String getReceiverusername() {
		return receiverusername;
	}
	public void setReceiverusername(String receiverusername) {
		this.receiverusername = receiverusername;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(String senddate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		this.senddate = (Date) formatter.parse(senddate);
	}
	
}
