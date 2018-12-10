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
		//loggedInCust = loggedInCustomer;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String query = "select * from food where available!='0' and date='"+dateFormat.format(selectedDate)+"'";
		

		try {
			foodOption.PrintFoodListCustomer(query,selectedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println("\n\n\n-------------\n1: return to the Main Page\n2: Filter your food\n3: Add a new Order\n4: Edit a current order\n5: Delete a current order");
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
			
			System.out.println(loggedInCust.getName().toString());
			System.out.println("For adding a new order please enter the food id");
			int customerInput = reader.nextInt();
			System.out.println("For adding a new order please enter the amount you want to order");
			int customerInputAmount = reader.nextInt();
			System.out.println("For adding a new order please enter the Payment type");
			int customerInputPaymentType = reader.nextInt();
				order.orderHandlerAddOrder(loggedInCust.getCustomerusername(),foodOption.SelectFoodCustomer(selectedDate),customerInputAmount,customerInputPaymentType);
			break;
		case 4:
			System.out.println(loggedInCust.getName().toString());
			order.showOpenOrders(loggedInCust.getCustomerusername());
			System.out.println("For adding a new order please enter the food id");
			int customerInputIDEdit = reader.nextInt();
			System.out.println("For adding a new order please enter the amount you want to order");
			int customerInputAmountEdit = reader.nextInt();
			order.editOrder(loggedInCust.getCustomerusername(),customerInputIDEdit,customerInputAmountEdit); //EDIT
			break;
		case 5:
			order.showOpenOrders(loggedInCust.getCustomerusername());
			System.out.println("For adding a new order please enter the food id");
			int customerInputIDDelete = reader.nextInt();
			order.deleteOrder(loggedInCust.getCustomerusername(),customerInputIDDelete); //DELETE
			break;
			default:
		}
	}

	@Override
	public void switchMenupage(int input) {
		
		
	}

}
