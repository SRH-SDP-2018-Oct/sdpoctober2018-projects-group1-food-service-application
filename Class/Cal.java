import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Cal {
	private static Calendar c = Calendar.getInstance();
	private static Date date;
	private static String year, month, day;
	public static void PrintCalendar() {
		c.set(Calendar.YEAR, Integer.valueOf(year));
		c.set(Calendar.MONTH, Integer.valueOf(month)-1);
		
		int dayofweek = c.get(Calendar.DAY_OF_WEEK);
		c.set(Calendar.DAY_OF_MONTH,32);
		int lastday = 32-c.get(Calendar.DAY_OF_MONTH);
		
		System.out.println("S\tM\tT\tW\tT\tF\tS");
		for(int i=0; i<dayofweek-1; i++)
			System.out.print("\t");
		for(int i=0,j=0; j<=lastday;i++,j++) {
			if(i%7==0)
				System.out.println("");
			System.out.print(j+"\t");
		}
	}
	public static void ChooseDate() {
		System.out.print("\033[H\033[2J");
		System.out.println("CHOOSE A DATE");
		
		PrintCalendar();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n\nSelect Date>>");
		day = sc.next();
	}
	public static void ShowCalendar() throws ParseException {
		System.out.print("\033[H\033[2J");
		System.out.println("CALENDAR PAGE");
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Select Year>>");
		year = sc.next();
		System.out.print("Select Month>>");
		month = sc.next();
		
		ChooseDate();
		
		String datestring = year + "-" + month + "-" + day;
		date = new SimpleDateFormat("yyyy-MM-dd").parse(datestring);
		System.out.println(date);
	}
}
