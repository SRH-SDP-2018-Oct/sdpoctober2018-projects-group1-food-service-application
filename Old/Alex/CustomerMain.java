package mealsanddeals.foodservice;

import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Scanner;
public class CustomerMain {
	Order order = new Order();
	Ranking ranking = new Ranking();
	public void main() {
		customerMainPage();
	}
	
	public void customerMainPage() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Meals and Deals. For Navigation\n press [1] for Calendar\n press [2] for Report\n press [3] for Menu");
		int customerInput = reader.nextInt();
		switchMenupage(customerInput);
		reader.close();	
	}
	
public void switchMenupage(int input){
	
	 int switchMenu = input;
	 String menuString;
	 CalendarPage page = new CalendarPage();
	 ReportPage repPage = new ReportPage();
				switch(switchMenu) {
				case 0: 
					customerMainPage();
					break;
				case 1: menuString = "Welcome to Calendar Page";
					System.out.println(menuString);
					order.selectOrdersFromUser("jisu123"); //Make this general
					ranking.rankOrders(order.CustomerUnrankedOrders);
					page.Overview();
				break;
				case 2: menuString = "Welcome to Report Page";
				System.out.println(menuString);
				repPage.Overview();
				break;
				case 3: menuString = "Welcome to MenuPage";
				System.out.println(menuString);
				break;
				default: menuString = "Invalid Input";
				System.out.println(menuString);
				switchMenupage(0);
				break;
				}
}
public void navigation() {
		clearScreen();
		customerMainPage();
}
public void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  

}
