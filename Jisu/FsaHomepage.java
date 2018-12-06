import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

public class FsaHomepage {
	public static Scanner sc = new Scanner(System.in);
	public static Cal c;
	public static FsaDayPage day;
	public static void backtoPrevious() {

		
	}
	public static void manageFoodlist() throws ParseException, NoSuchAlgorithmException, SQLException {
		c.ShowCalendar();
		FsaDayPage.ShowDay(c.getDate());
	}
	public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, SQLException {
		// TODO Auto-generated method stub
		System.out.print("\033[H\033[2J");
		System.out.println("HOME PAGE");
		System.out.print("1: show calendar\n2: back to login page\n3: quit program\n>>");
		int n = sc.nextInt();
		switch(n) {
		case 1: manageFoodlist();
		case 2: backtoPrevious();
		case 3: System.exit(0);
		}

	}
}
