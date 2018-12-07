package mealsanddeals;

import java.util.Date;

public class Notification {
	int notificationid;
	String senderusername;
	String receiverusername;
	String subject;
	String content;
	Date senddate;
	Date receivedate;
	String status;
	MysqlCon newSql = new MysqlCon();
	
	public int getNotificationid() {
		return notificationid;
	}
	public void setNotificationid(int notificationid) {
		this.notificationid = notificationid;
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
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	public Date getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
