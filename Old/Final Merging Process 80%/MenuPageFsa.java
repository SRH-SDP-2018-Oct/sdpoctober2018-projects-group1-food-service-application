package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class MenuPageFsa {
	private static FSA loggedInfsa = new FSA();
	public static void decisioner(FSA loggedInFsa) throws NoSuchAlgorithmException, ParseException, SQLException {
		loggedInfsa = loggedInFsa;
		Overview();
	}
	

	public static void Overview() throws NoSuchAlgorithmException, ParseException, SQLException{
		System.out.println("\n1: to Return to Homepage\n2: Profile\n3: Messages\n4: Logout");
		Scanner reader = new Scanner(System.in);
		int customerInput = reader.nextInt();
		switch (customerInput) {
		case 1: FsaHomepage.main(loggedInfsa);
		break;
		case 2: 
				ProfileFSA.decisioner(loggedInfsa);
		break;
		case 3: 
				NotificationPage.notificationPageFSA(loggedInfsa);
		break;
		case 4: try {
				MealsandDeals.main(null);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		break;
		default: System.out.println("Invalid Input");
		decisioner(loggedInfsa);
		break;
		}
		reader.close();
		

	
}
}
