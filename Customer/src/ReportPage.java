import java.text.ParseException;
import java.util.Scanner;

public class ReportPage extends CustomerPage {
	private CustomerMain custMain = new CustomerMain();

	@Override
	public void Overview() {
		System.out.println("\nPress [1] to Return to Homepage\nPress [2] to show the Paid orders\nPress [3] to show the Unpaid orders\nPress [4] to show the Detailed Report");
		Scanner reader = new Scanner(System.in);
		int customerInput = reader.nextInt();
		switch (customerInput) {
		case 1: 
			custMain.customerMainPage();
			break;
		case 2: 
			System.out.println("Welcome to Paid Orders");
			break;
		case 3: 
			System.out.println("Welcome to Unpaid Orders");
			break;
		case 4: 
			System.out.println("Welcome to Detailed Report");
			break;
			
		default: System.out.println("Invalid Input");
		Overview();
		break;
		}
		reader.close();
		
	}
	
	

}
