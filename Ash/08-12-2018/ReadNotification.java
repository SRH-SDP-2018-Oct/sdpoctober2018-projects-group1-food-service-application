package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;


//read notification 
//notification -> read-> show all -> select detail -> show detail
public class ReadNotification {
	
		private String tablename = "notification";
		
	    MysqlCon Sql = new MysqlCon();
	    ArrayList<Notification> NotificationList;
	    GetNotificationList GetList = new GetNotificationList();

	    //show all notification simple(id number,sendername,sendtime,status)  
	  	public void showAllNotification(String receivername) {
	  		NotificationList = new ArrayList<Notification>();
	  		GetList.GetAllNotificationList(NotificationList,receivername);
	  		
	  		System.out.println("\nNotification List");
	  		System.out.println("List \t Sender\t Send Time\t Subject\t Status");
	  		System.out.println("----------------------------------------------------------------");
	  		
	  		int i = 0;
	  		while(i<NotificationList.size()) {
	  			System.out.println(NotificationList.get(i).getNotificationid() +"\t"+ NotificationList.get(i).getSenderusername() +"\t"+
	  							   NotificationList.get(i).getSenddate() +"\t"+ NotificationList.get(i).getSubject() +"\t"+ NotificationList.get(i).getStatus());
	  			i++;
	  		}
	  	}
	 
	  	
	  	//select one notification and show detail content
	  	public void showDetailNotification(int id,String receivername) throws SQLException {
	  		NotificationList = new ArrayList<Notification>();
	  		ArrayList<String[]> conditionquery = new  ArrayList<String[]>();
	  		ArrayList<String[]> setquery = new  ArrayList<String[]>();
	  		GetList.GetAllNotificationList(NotificationList,receivername);
	  		
	  		int i = 0,index = 0;
	  		
	  		System.out.println("Notification :" + id);
	  		
	  		while(i<NotificationList.size()) {//find index that notificationid equal id
	  			if(NotificationList.get(i).getNotificationid() == id) {
		  			index = i;
		  		}
		  		i++;
		  	}
	  		String stringId = Integer.toString(id);
	  		setquery.add(new String [] {"Status", "read"});
	  		conditionquery.add( new String[]{"notificationid",stringId});
	  	
	  		Sql.updateTable(tablename,setquery,conditionquery);
			
	  		System.out.println("Sender\t Receiver\t Send Time\t Subject\t Content \tStatus");
	  		System.out.println("--------------------------------------------------------------------------------------------------------");
	  		System.out.println(NotificationList.get(index).getSenderusername() +"\t"+ NotificationList.get(index).getReceiverusername() +"\t"+
					   NotificationList.get(index).getSenddate() +"\t"+ NotificationList.get(index).getSubject() 
					   +"\t"+ NotificationList.get(index).getContent() + "\t"+"read");
	  	}
	  	
	  	
	  	
	    //delete notification by selecting notification id 
	  	public void deleteNotification(int id ) {
			ArrayList<String[]> queryarray = new  ArrayList<String[]>();
			queryarray.add(new String[] {"notificationid",String.valueOf(id)});
			
			Sql.deleteFromTable(tablename, queryarray);
		
	    }
		
		
}
