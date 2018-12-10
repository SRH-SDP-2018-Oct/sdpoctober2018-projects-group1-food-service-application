package mealsanddeals.foodservice;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class OrderPage extends CustomerPageNavigation {
	private FoodOption foodOption = new FoodOption();
	private Order order = new Order();
	private Filter filter = new Filter();

	@Override
	public void Overview() {
		
		
		//reader.close();
		
	}
	
	public void Overview(Date selectedDate) throws NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, ParseException, SQLException {
		System.out.println("Welcome to ----> Order Page <----\n Press [0] to return to the Main Page\n Press [1] to continue with your order");
		try {
			foodOption.PrintFoodList(selectedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("\n\n\n-------------\nPress [0] to return to the Main Page\nPress [1] filter your food\nPress [2] to add a new Order\nPress [3] to edit a current order\nPress [4] to delete a current order");
		Scanner reader = new Scanner(System.in);
		
		int customerInput = reader.nextInt();
		switchMenupage(customerInput,selectedDate);
	}
	
	public void switchMenupage(int input, Date selectedDate) throws NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, ParseException, SQLException {
		int switchMenu = input;
		Scanner reader = new Scanner(System.in);
		switch(switchMenu) {
		case 0: CustomerMain custMain = new CustomerMain();
		custMain.main();
		break;
		case 1:
			//TODO Add Filter here
			break;
		case 2:
			
			
			System.out.println("For adding a new order please enter the food id");
			int customerInput = reader.nextInt();
			System.out.println("For adding a new order please enter the amount you want to order");
			int customerInputAmount = reader.nextInt();
			System.out.println("For adding a new order please enter the Payment type");
			int customerInputPaymentType = reader.nextInt();
				order.orderHandlerAddOrder("jisu123",foodOption.SelectFoodCustomer(selectedDate),customerInputAmount,customerInputPaymentType);
			break;
		case 3:
			order.showOpenOrders("jisu123");
			System.out.println("For adding a new order please enter the food id");
			int customerInputIDEdit = reader.nextInt();
			System.out.println("For adding a new order please enter the amount you want to order");
			int customerInputAmountEdit = reader.nextInt();
			order.editOrder("jisu123",customerInputIDEdit,customerInputAmountEdit); //EDIT
			break;
		case 4:
			order.showOpenOrders("jisu123");
			System.out.println("For adding a new order please enter the food id");
			int customerInputIDDelete = reader.nextInt();
			order.deleteOrder("jisu123",customerInputIDDelete); //DELETE
			break;
			default:
		}
	}

	@Override
	public void switchMenupage(int input) {
		
		
	}

}
