package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

//Page class of Notification (main)
//Menu->Notification -> send 
//Menu->Notification->read 
//Menu->notification->read->select detail
//Menu->Notification -> delete
public class NotificationPage {
	
	private static ReadNotification Read = new ReadNotification();
	private static SendNotification Send = new SendNotification();
	private static NotificationPage Page = new NotificationPage();
	private static MenuPageFsa MenuFsa = new MenuPageFsa();
	private static MenuPageCustomer MenuCustomer = new MenuPageCustomer();
	
	public static void notificationPageFSA(FSA loggedInfsa) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		int page;
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.println("Notification");
		System.out.println("1: Send \n2: Read \n3: Delete \n4: Back To Menu");
		System.out.println("Select Number: ");
		page = scanner.nextInt();
				
		switch(page) {
		case 1://send Notification
			Send.enterSendContent(loggedInfsa.getFsausername());
			notificationPageFSA(loggedInfsa);
			break;
		case 2://read all Notification -> read detail notification
			Read.showAllNotification(loggedInfsa.getFsausername());
			readFSANotificationDetail(loggedInfsa.getFsausername(),loggedInfsa);
			break;
		case 3://delete notification 
			Read.showAllNotification(loggedInfsa.getFsausername());
			System.out.println("Select Delete Notification Number: ");
			int id = scanner2.nextInt();
			Read.deleteNotification(id);
			backToFSANotification(loggedInfsa);
			break;
		case 4://back to menu
			backToFSAMenu();
			break;
			
		}
		scanner.close();
		scanner2.close();
	}
	
	public static void notificationPageCustomer(Customer loggedInCustomer) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		int page;
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.println("Notification");
		System.out.println("1: Send \n2: Read \n3: Delete \n4: Back To Menu");
		System.out.println("Select Number: ");
		page = scanner.nextInt();
				
		switch(page) {
		case 1://send Notification
			Send.enterSendContent(loggedInCustomer.getCustomerusername());
			notificationPageCustomer(loggedInCustomer);
			break;
		case 2://read all Notification -> read detail notification
			Read.showAllNotification(loggedInCustomer.getCustomerusername());
			readCustomerNotificationDetail(loggedInCustomer.getCustomerusername(),loggedInCustomer);
			break;
		case 3://delete notification 
			Read.showAllNotification(loggedInCustomer.getCustomerusername());
			System.out.println("Select Delete Notification Number: ");
			int id = scanner2.nextInt();
			Read.deleteNotification(id);
			backToCustomerNotification(loggedInCustomer);
			break;
		case 4://back to menu
			backToCustomerMenu();
			break;
			
		}
		scanner.close();
		scanner2.close();
	}
	
	public static void readFSANotificationDetail(String receivername,FSA loggedInfsa) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		
		int selectDetailNotification;
		int selectpage;
		Scanner scanner3 = new Scanner(System.in);
		Scanner scanner4 = new Scanner(System.in);
		System.out.println("\n1: Select Notificaion \n2: Back To Notification");
		System.out.println("Select Number: ");
		selectpage = scanner3.nextInt();
		
		switch(selectpage) {
			case 1:
				System.out.println("Select Notification: ");
				selectDetailNotification = scanner4.nextInt();
				Read.showDetailNotification(selectDetailNotification,receivername);
				backToFSARead(receivername,loggedInfsa);
				break;
			case 2:
				backToFSANotification(loggedInfsa);
				break;
				
		}
		scanner3.close();
		scanner4.close();
	}
	
	public static void readCustomerNotificationDetail(String receivername,Customer loggedInCustomer) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		
		int selectDetailNotification;
		int selectpage;
		Scanner scanner3 = new Scanner(System.in);
		Scanner scanner4 = new Scanner(System.in);
		System.out.println("\n1: Select Notificaion \n2: Back To Notification");
		System.out.println("Select Number: ");
		selectpage = scanner3.nextInt();
		
		switch(selectpage) {
			case 1:
				System.out.println("Select Notification: ");
				selectDetailNotification = scanner4.nextInt();
				Read.showDetailNotification(selectDetailNotification,receivername);
				backToCustomerRead(receivername,loggedInCustomer);
				break;
			case 2:
				backToCustomerNotification(loggedInCustomer);
				break;
				
		}
		scanner3.close();
		scanner4.close();
	}

	public static void backToFSANotification(FSA loggedInfsa) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		
		notificationPageFSA(loggedInfsa);
	
	}
	
	public static void backToCustomerNotification(Customer loggedInCustomer) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		
		notificationPageCustomer(loggedInCustomer); 
	
	}
	
	public static void backToFSARead(String username,FSA loggedInfsa) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		String reply;
		Scanner scanner6 = new Scanner(System.in);
		while(true) {
			System.out.println("\nBack To Read Page[Y/N]?");
			reply = scanner6.nextLine();
		
			if(reply.equalsIgnoreCase("Y")) {
				Read.showAllNotification(username);
				readFSANotificationDetail(username,loggedInfsa);
				break;
			}
		}
		scanner6.close();
	}
	
	public static void backToCustomerRead(String username, Customer loggedInCustomer) throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		String reply;
		Scanner scanner6 = new Scanner(System.in);
		while(true) {

			System.out.println("\nBack To Read Page[Y/N]?");
			reply = scanner6.nextLine();
		
			if(reply.equalsIgnoreCase("Y")) {
				Read.showAllNotification(username);
				readCustomerNotificationDetail(username,loggedInCustomer);
				break;
			}
		}
		scanner6.close();
	}
	public static void backToFSAMenu() throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		MenuFsa.Overview();
	}
	public static void backToCustomerMenu() throws SQLException, NoSuchAlgorithmException, ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		MenuCustomer.Overview();
	}

}
