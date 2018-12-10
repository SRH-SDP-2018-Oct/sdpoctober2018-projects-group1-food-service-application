package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class FsaHomepage {
	public static Scanner sc = new Scanner(System.in);
	public static Cal c;
	public static FsaDayPage day;
	private static FSA loggedInfsa = new FSA();

	public static void manageFoodlist() throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Cal.ShowCalendar();
		FsaDayPage.ShowDay(Cal.getDate(),loggedInfsa);
	}
	public static void fsaMain(FSA loggedInFsa) throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		loggedInfsa = loggedInFsa;
		System.out.println("HOME PAGE");
		System.out.print("1: Show Calendar\n2: Menu\n");
		int n = sc.nextInt();
		switch(n) {
		case 1: manageFoodlist();
		case 2: MenuPageFsa.decisioner(loggedInfsa);
		}

	}
}
