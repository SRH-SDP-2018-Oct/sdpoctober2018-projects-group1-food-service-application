package com.srh_heidelberg.mealsanddeals;

import java.util.ArrayList;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

//send notification 
public class SendNotification {
	MysqlCon Sql = new MysqlCon();
	Notification newNotification = new Notification();
	String tablename = "notification";
	ArrayList<String> sendqueryarray = new  ArrayList<String>();
	
	//enter content 
	public void enterSendContent() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date send = new Date(2018-11-11);
		Date receive = new Date(2018-11-12);
		
		//user input 
		System.out.println("Enter Message\n");
		System.out.println("Receiver Name:");
		//�޴� ��� �����ϴ� ������� ���� 
		newNotification.setReceiverusername(scanner.nextLine());
		
		System.out.println("Subject:");
		newNotification.setSubject(scanner.nextLine());
		System.out.println("Content:");
		newNotification.setContent(scanner.nextLine());
		
		//system input auto 
		newNotification.setNotificationid(1);
		newNotification.setSenderusername("AA");
		newNotification.setSenddate(send);
		newNotification.setReceivedate(receive);
		newNotification.setStatus("Unread");
		
		//make query and insert into DB
		sendqueryarray.add("0");
		sendqueryarray.add(newNotification.getSenderusername());
		sendqueryarray.add(newNotification.getReceiverusername());
		sendqueryarray.add(newNotification.getSubject());
		sendqueryarray.add(newNotification.getContent());
		sendqueryarray.add(transFormat.format(newNotification.getSenddate()));
		sendqueryarray.add(transFormat.format(newNotification.getReceivedate()));
		sendqueryarray.add(newNotification.getStatus());
		
		try {
			Sql.insertToTable(tablename,sendqueryarray);
			System.out.println("Success! ");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scanner.close();
		
	}

}
