//**로그인 중인 객체 완성되면 추가 

package Notification;

import java.util.Scanner;

//Page class of Notification (main)
public class NotificationPage {
	private static ReadNotification Read = new ReadNotification();
	private static SendNotification Send = new SendNotification();
	private static NotificationPage Page = new NotificationPage();
	//login username 
	
	public static void backToMenu() {
		
	}
	public static void backToNotification() {
		String reply;
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("back To Notification[Y/N]?");
		reply = scanner3.nextLine();
		
	
		scanner3.close();
	}
	public static void backToRead() {
		String reply;
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("back To Read Page[Y/N]?");
		reply = scanner3.nextLine();
		if(reply == "Y") {
			Read.showAllNotification();
			readNotificationDetail();
		}
		else if(reply == "N") {
			
		}
		scanner3.close();
	}
	//select detail notification -> show detail 
	public static void readNotificationDetail() {
		
		int selectDetailNotification;
		int selectpage;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("\n1.select Notificaion \n2.Back to Notification");
		selectpage = scanner.nextInt();
		
		switch(selectpage) {
			case 1:
				System.out.println("\nselect Notification: ");
				selectDetailNotification = scanner2.nextInt();
				Read.showDetailNotification(selectDetailNotification);
				backToRead();
				break;
			case 2:
				backToNotification();
				break;
				
		}
			
		scanner.close();
		scanner2.close();
	}
	
	//notification -> send 
	//notification -> read -> all -> select detail
	//notification -> delete
	public static void main(String[] args) {
		
		int page;
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		System.out.println("Notification\n-----------------------------------------");
		System.out.println("1.Send \n2.Read \n3.Delete \n4.Back To Menu");
		System.out.println("\nselect: ");
		page = scanner.nextInt();
				
		switch(page) {
		case 1://send Notification-> enter the content
			Send.enterSendContent();
			backToNotification();
			break;
		case 2://read all Notification -> read detail notification
			Read.showAllNotification();
			readNotificationDetail();
			break;
		case 3://delete notification 
			Read.showAllNotification();
			System.out.println("select delete notification: ");
			int id = scanner2.nextInt();
			Read.deleteNotification(id);
			backToNotification();
			break;
		case 4://back to menu
			backToMenu();
			break;
			
		}
		scanner.close();
		scanner2.close();
	}



}
