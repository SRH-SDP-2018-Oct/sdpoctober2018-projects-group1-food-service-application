package mealsanddeals;

import java.sql.SQLException;
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
	private static FSA loggedInfsa = new FSA();
	private static Customer loggedIncust = new Customer();
	
	public static void notificationPageFSA(FSA loggedInfsa) throws SQLException {
		int page;
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.println("Notification\n-----------------------------------------");
		System.out.println("1: Send \n2: Read \n3: Delete \n4: Back To Menu");
		System.out.println("\nselect: ");
		page = scanner.nextInt();
				
		switch(page) {
		case 1://send Notification
			Send.enterSendContent(loggedInfsa.getFsausername());
			backToFSANotification();
			break;
		case 2://read all Notification -> read detail notification
			Read.showAllNotification(loggedInfsa.getFsausername());
			readFSANotificationDetail(loggedInfsa.getFsausername());
			break;
		case 3://delete notification 
			Read.showAllNotification(loggedInfsa.getFsausername());
			System.out.println("select delete notification: ");
			int id = scanner2.nextInt();
			Read.deleteNotification(id);
			backToFSANotification();
			break;
		case 4://back to menu
			backToMenu();
			break;
			
		}
		scanner.close();
		scanner2.close();
	}
	
	public static void notificationPageCustomer(Customer loggedInCustomer) throws SQLException {
		int page;
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.println("Notification\n-----------------------------------------");
		System.out.println("1: Send \n2: Read \n3: Delete \n4: Back To Menu");
		System.out.println("\nselect: ");
		page = scanner.nextInt();
				
		switch(page) {
		case 1://send Notification
			Send.enterSendContent(loggedIncust.getCustomerusername());
			backToCustomerNotification();
			break;
		case 2://read all Notification -> read detail notification
			Read.showAllNotification(loggedIncust.getCustomerusername());
			readCustomerNotificationDetail(loggedIncust.getCustomerusername());
			break;
		case 3://delete notification 
			Read.showAllNotification(loggedIncust.getCustomerusername());
			System.out.println("select delete notification: ");
			int id = scanner2.nextInt();
			Read.deleteNotification(id);
			backToCustomerNotification();
			break;
		case 4://back to menu
			backToMenu();
			break;
			
		}
		scanner.close();
		scanner2.close();
	}
	
	public static void readFSANotificationDetail(String receivername) throws SQLException {
		
		int selectDetailNotification;
		int selectpage;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("\n1: select Notificaion \n2: Back to Notification");
		System.out.println("\nselect: ");
		selectpage = scanner.nextInt();
		
		switch(selectpage) {
			case 1:
				System.out.println("\nselect Notification: ");
				selectDetailNotification = scanner2.nextInt();
				Read.showDetailNotification(selectDetailNotification,receivername);
				backToFSARead(receivername);
				break;
			case 2:
				backToFSANotification();
				break;
				
		}
		scanner.close();
		scanner2.close();
	}
	
	public static void readCustomerNotificationDetail(String receivername) throws SQLException {
		
		int selectDetailNotification;
		int selectpage;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("\n1: select Notificaion \n2: Back to Notification");
		System.out.println("\nselect: ");
		selectpage = scanner.nextInt();
		
		switch(selectpage) {
			case 1:
				System.out.println("\nselect Notification: ");
				selectDetailNotification = scanner2.nextInt();
				Read.showDetailNotification(selectDetailNotification,receivername);
				backToCustomerRead(receivername);
				break;
			case 2:
				backToCustomerNotification();
				break;
				
		}
		scanner.close();
		scanner2.close();
	}

	public static void backToFSANotification() throws SQLException {
		String reply;
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("back To Notification[Y/N]?");
		reply = scanner3.nextLine();
		
		if(reply == "Y") {
			notificationPageFSA(loggedInfsa);
		}
		
		scanner3.close();
	}
	
	public static void backToCustomerNotification() throws SQLException {
		String reply;
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("back To Notification[Y/N]?");
		reply = scanner3.nextLine();
		
		if(reply == "Y") {
			notificationPageCustomer(loggedIncust);
		}
		
		scanner3.close();
	}
	
	public static void backToFSARead(String username) throws SQLException {
		String reply;
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("back To Read Page[Y/N]?");
		reply = scanner3.nextLine();
		
		if(reply == "Y") {
			Read.showAllNotification(username);
			readFSANotificationDetail(username);
		}
		
		scanner3.close();
	}
	
	public static void backToCustomerRead(String username) throws SQLException {
		String reply;
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("back To Read Page[Y/N]?");
		reply = scanner3.nextLine();
		
		if(reply == "Y") {
			Read.showAllNotification(username);
			readCustomerNotificationDetail(username);
		}
		
		scanner3.close();
	}
	public static void backToMenu() throws SQLException {
		
	}

}
