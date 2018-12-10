package mealsanddeals.foodservice;

import java.util.Scanner;

public class OrderPage extends CustomerPageNavigation {
	private Order order = new Order();

	@Override
	public void Overview() {
		
		System.out.println("Welcome to ----> Order Page <----\n Press [0] to return to the Main Page\n Press [1] to continue with your order");
		Scanner reader = new Scanner(System.in);
		int customerInput = reader.nextInt();
		switchMenupage(customerInput);
		reader.close();
		
	}

	@Override
	public void switchMenupage(int input) {
		int switchMenu = input;
		switch(switchMenu) {
		case 0: CustomerMain custMain = new CustomerMain();
		custMain.main();
		break;
		case 1:
			order.showOpenOrders("jisu123");
		}
		
	}

}
