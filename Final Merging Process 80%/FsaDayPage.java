package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class FsaDayPage {
	private static FSA loggedInfsa = new FSA();

	public static void BacktoDay(Date date) throws ParseException, NoSuchAlgorithmException, SQLException {
		ShowDay(date,loggedInfsa);
	}
	public static void BacktoChooseDate() throws ParseException, NoSuchAlgorithmException, SQLException {
		FsaHomepage.manageFoodlist();
	}
	public static void ShowDay(Date date,FSA loggedInFsa) throws ParseException, NoSuchAlgorithmException, SQLException {
		loggedInfsa = loggedInFsa;
		System.out.print("\033[H\033[2J");
		System.out.println("DAY PAGE");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("The date you selected is "+ dateFormat.format(date));
		
		System.out.print("1: food option\n2: offer option\n3: back to Home Page\n>>");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		switch(n) {
		case 1: FoodOption.ShowFoodOption(date,loggedInfsa);
		case 2: OfferOption.ShowOfferOption(date,loggedInfsa);
		case 3: FsaHomepage.main(loggedInfsa);
		}
	}
}
