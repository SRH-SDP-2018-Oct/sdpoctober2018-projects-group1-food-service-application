package com.srh_heidelberg.mealsanddeals;

import java.util.ArrayList;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//send notification 
public class SendNotification {
	MysqlCon Sql = new MysqlCon();
	Notification newNotification = new Notification();
	NotificationPage newpage = new NotificationPage();
	String tablename = "notification";
	ArrayList<String> sendqueryarray ;
	
	//enter content 
	public void enterSendContent(String sendername) throws NoSuchAlgorithmException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Scanner scanner7 = new Scanner(System.in);
		sendqueryarray = new  ArrayList<String>();
		//user input 
		System.out.println("\nEnter Message");
		while(true) {
			System.out.println("Receiver Name : ");
			//checking the receiver name in table 
			String receivername = scanner7.nextLine();
			String checkingcustomername = "customer";
			String checkingfsaname = "fsa";
			String enteredValue = receivername;
			String customercolumnLabel = "customerusername";
			String fsacolumnLabel = "fsausername";
			if(!Sql.checkingDups(checkingcustomername, enteredValue , customercolumnLabel).equals(enteredValue) || 
				!Sql.checkingDups(checkingcustomername, enteredValue , customercolumnLabel).equals(enteredValue)) {
					newNotification.setReceiverusername(receivername);
					break;
			}else {
				   System.out.println("Your Receiver name is not available.. \n");
			}
		}
		System.out.println("Subject : ");
		String line = scanner7.nextLine();
		newNotification.setSubject(line);
		System.out.println("Content : ");
		line = scanner7.nextLine();
		newNotification.setContent(line);

		long time = System.currentTimeMillis(); 
		DateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
			
		//system input auto 
		newNotification.setSenderusername(sendername);
		newNotification.setStatus("Unread");
	    
	    
		//make query and insert into DB
		sendqueryarray.add("0");
		sendqueryarray.add(newNotification.getSenderusername());
		sendqueryarray.add(newNotification.getReceiverusername());
		sendqueryarray.add(newNotification.getSubject());
		sendqueryarray.add(newNotification.getContent());
		sendqueryarray.add(dayTime.format(new Date(time)));
		sendqueryarray.add(newNotification.getStatus());
				
		
		Sql.insertToTable(tablename,sendqueryarray);
		
		System.out.println("Success!\n");
		
		//scanner7.close();
				
	}

}
