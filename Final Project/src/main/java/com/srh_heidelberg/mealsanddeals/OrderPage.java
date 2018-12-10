package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OrderPage extends CustomerPageNavigation {
	private FoodOption foodOption = new FoodOption();
	private Order order = new Order();
	private FoodFiltering filter = new FoodFiltering();
	private static Customer loggedInCust = new Customer();

	@Override
	public void Overview() {
		
		
		//reader.close();
		
	}
	
	public void Overview(Date selectedDate, Customer loggedInCustomer) throws NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, ParseException, SQLException {
		loggedInCust = loggedInCustomer;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String query = "select * from food where available!='0' and date='"+dateFormat.format(selectedDate)+"'";
		

		try {
			foodOption.PrintFoodListCustomer(query,selectedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("\n\n\n-------------\n1: Return to the Main Page\n2: Filter your food\n3: Add a New Order\n4: Edit a Current Order\n5: Delete a Current Order");
		Scanner reader = new Scanner(System.in);
		
		int customerInput = reader.nextInt();
		switchMenupage(customerInput,selectedDate);
	}
	
	public void switchMenupage(int input, Date selectedDate) throws NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, ParseException, SQLException {
		int switchMenu = input;
		Scanner reader = new Scanner(System.in);
		switch(switchMenu) {
		case 1: CustomerMain custMain = new CustomerMain();
		custMain.customerLogin(loggedInCust);
		break;
		case 2:
			//filter.
			break;
		case 3:
			
			System.out.println("For adding a new order please enter the food id : ");
			int customerInput = reader.nextInt();
			System.out.println("For adding a new order please enter the amount you want to order : ");
			int customerInputAmount = reader.nextInt();
			System.out.println("For adding a new order please enter the Payment type : ");
			int customerInputPaymentType = reader.nextInt();
				order.orderHandlerAddOrder(loggedInCust,foodOption.SelectFoodCustomer(selectedDate),customerInputAmount,customerInputPaymentType);
			Overview(selectedDate, loggedInCust);
				break;
		case 4:
			order.showOpenOrders(loggedInCust.getCustomerusername());
			System.out.println("Enter the Order ID you want to Edit :");
			int customerInputIDEdit = reader.nextInt();
			System.out.println("Your New Amount of Food : ");
			int customerInputAmountEdit = reader.nextInt();
			order.editOrder(loggedInCust.getCustomerusername(),customerInputIDEdit,customerInputAmountEdit); //EDIT
			Overview(selectedDate, loggedInCust);
			break;
		case 5:
			order.showOpenOrders(loggedInCust.getCustomerusername());
			System.out.println("Enter Order ID that you want to Delete : ");
			int customerInputIDDelete = reader.nextInt();
			order.deleteOrder(loggedInCust.getCustomerusername(),customerInputIDDelete); //DELETE
			Overview(selectedDate, loggedInCust);
		break;
			default:
		}
	}

	@Override
	public void switchMenupage(int input) {
		
		
	}

}
