package Notification;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import Notification.MysqlCon;


//read notification 
//notification -> read-> show all -> select detail -> show detail
public class ReadNotification {
	
		private String tablename = "notification";
		
	    MysqlCon Sql = new MysqlCon();
	    ArrayList<Notification> NotificationList;
	    GetNotificationList GetList = new GetNotificationList();

	    //show all notification simple(id number,sendername,sendtime,status)  
	  	public void showAllNotification() {
	  		NotificationList = new ArrayList<Notification>();
	  		GetList.GetAllNotificationList(NotificationList);
	  		
	  		System.out.println("\nNotification List");
	  		System.out.println("List \t Sender\t Send Time\t Subject\t Status");
	  		System.out.println("----------------------------------------------------------------\n");
	  		
	  		int i = 0;
	  		while(i<NotificationList.size()) {
	  			System.out.println(NotificationList.get(i).getNotificationid() +"\t"+ NotificationList.get(i).getSenderusername() +"\t"+
	  							   NotificationList.get(i).getSenddate() +"\t"+ NotificationList.get(i).getSubject() +"\t"+ NotificationList.get(i).getStatus());
	  			i++;
	  		}
	  	}
	 
	  	
	  	//select one notification and show detail content
	  	public void showDetailNotification(int id) {
	  		NotificationList = new ArrayList<Notification>();
	  		ArrayList<String[]> conditionquery = new  ArrayList<String[]>();
	  		ArrayList<String[]> setquery = new  ArrayList<String[]>();
	  		GetList.GetAllNotificationList(NotificationList);
	  		
	  		int i = 0,index = 0;
	  		
	  		System.out.println("\nNotification :" + id);
	  		
	  		while(i<NotificationList.size()) {//find index that notificationid equal id
	  			if(NotificationList.get(i).getNotificationid() == id) {
		  			index = i;
		  		}
		  		i++;
		  	}
	  		String stringId = Integer.toString(id);
	  		setquery.add(new String [] {"Status", "read"});
	  		conditionquery.add( new String[]{"notificationid",stringId});
	  	
	  		
			try {
				Sql.updateTable(tablename,setquery,conditionquery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	  		System.out.println("Sender\t Receiver\t Send Time\t Subject\t Content \tStatus");
	  		System.out.println("--------------------------------------------------------------------------------------------------------\n");
	  		System.out.println(NotificationList.get(index).getSenderusername() +"\t"+ NotificationList.get(index).getReceiverusername() 
	  				          +"\t"+ NotificationList.get(index).getSenddate() +"\t"+ NotificationList.get(index).getSubject() 
					          +"\t"+ NotificationList.get(index).getContent() + "\t"+"read");
	  	}
	  	
	  	
	  	
	    //delete notification by selecting notification id 
		public void deleteNotification(int id ) {
			ArrayList<String[]> queryarray = new  ArrayList<String[]>();
			queryarray.add(new String[] {"notificationid",String.valueOf(id)});
			
			Sql.deleteFromTable(tablename, queryarray);
		
	    }
		
		
}
