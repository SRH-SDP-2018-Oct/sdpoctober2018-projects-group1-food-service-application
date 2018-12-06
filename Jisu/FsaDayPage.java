import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class FsaDayPage {
	
	@SuppressWarnings("static-access")
	
	public static void BacktoDay(Date date) throws ParseException, NoSuchAlgorithmException, SQLException {
		ShowDay(date);
	}
	public static void BacktoChooseDate() throws ParseException, NoSuchAlgorithmException, SQLException {
		FsaHomepage home = new FsaHomepage();
		home.manageFoodlist();
	}
	public static void ShowDay(Date date) throws ParseException, NoSuchAlgorithmException, SQLException {
		System.out.print("\033[H\033[2J");
		System.out.println("DAY PAGE");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("The date you selected is "+ dateFormat.format(date));
		
		System.out.print("1: food option\n2: offer option\n3: back to choose date\n4: quit program\n>>");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		switch(n) {
		case 1: FoodOption.ShowFoodOption(date);
		case 2: OfferOption.ShowOfferOption(date);
		case 3: BacktoChooseDate();
		case 4: System.exit(0);
		}
	}
}
