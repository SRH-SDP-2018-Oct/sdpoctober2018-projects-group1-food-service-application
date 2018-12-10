import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OfferOption {
	private static MysqlCon db;
	private static ArrayList<Offer> offerlist;
	private static Offer newOffer;
	
	public static void PrintOfferList(Date date) throws ParseException {
		System.out.print("\033[H\033[2J");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String[]> detail = new ArrayList<String[]>();
		
		offerlist = new ArrayList<Offer>();
		detail.add(new String[] {"fsausername", "ash2"});
		detail.add(new String[] {"date", dateFormat.format(date)});
		db.selectOffer("offer", detail, offerlist);
		
		System.out.println("---OFFER LIST---");
		System.out.println("[id]foodamount  discount");
		
		for(int i=0; i<offerlist.size(); i++) {
			System.out.println("["+ offerlist.get(i).getOfferid() +"]" + offerlist.get(i).getFoodamount() +"  "+ offerlist.get(i).getDiscount());
		}
	}
	public static void AddOffer(Date date, int foodid) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		//ArrayList<String> offercolumn = new ArrayList<String>();
		ArrayList<String> detail = new ArrayList<String>();
		//db.getColumnName("offer", offercolumn);
		
		Scanner sc = new Scanner(System.in);
		int offerid = 0;
		if(FoodOption.checkOffer(date, foodid)==true) {
			System.out.println("WARNING : There is offer with the food you want to delete! \n1: back to food option");
			int n = sc.nextInt();
			switch(n) {
			case 1: FoodOption.ShowFoodOption(date);
			}
		} else {
			Offer newOffer = new Offer();
			newOffer.setoffer(foodid, date);
			detail = newOffer.makeQuery();
				
			db.insertToTable("offer", detail);
		}
	}
	public static void EditOffer(Offer newOffer) throws SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		//offercolumn = newOffer.getFields();
		ArrayList<String[]> detail = new ArrayList<String[]>();
		ArrayList<String[]> condition = new ArrayList<String[]>();
		
		ArrayList<Field> offercolumn = newOffer.getOffer();
		
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		int num = 1;
		String editinfo;
		
		while(num!=0) {
			System.out.print("\nSelect number you want to edit(press 0 to exit)>>");
			num = sc.nextInt();
			if(num==0) break;
			else {
				System.out.print("Change information>>");
				editinfo = sc2.nextLine();
				detail.add(new String[] {offercolumn.get(num-1).getName(), editinfo});
			}
		}
		
		condition.add(new String[] {"offerid", String.valueOf(newOffer.getOfferid())}); //primary key -> need to be changed
		db.updateTable("offer", detail, condition);
	}
	public static void DeleteOffer(int offerid) {
		ArrayList<String[]> detail = new ArrayList<String[]>();
		detail.add(new String[] {"offerid", String.valueOf(offerid)});
		db.deleteFromTable("offer", detail);
	}
	public static void SelectOffer(Date date) throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		System.out.print("Enter Offerid>>");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		
		newOffer = new Offer();
		
		int i=0;
		while(i<offerlist.size()) {
			if(offerlist.get(i).getOfferid()==id) {
				newOffer = offerlist.get(i);
				break;
			}
			else {
				i++;
			}
		}
		
		System.out.print("\033[H\033[2J");
		System.out.print("1: edit offer\n2: delete offer\n3: back to offer option\n4: quit program\n>>");
		int n = sc.nextInt();
		
		switch(n) {
		case 1: EditOffer(newOffer);
				ShowOfferOption(date);
		case 2: DeleteOffer(id);
				ShowOfferOption(date);
		case 3: ShowOfferOption(date);
		case 4: System.exit(0);
		}
	}
	public static void ShowOfferOption(Date date) throws ParseException, NoSuchAlgorithmException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		FsaDayPage daypage = new FsaDayPage();
		PrintOfferList(date);
		System.out.print("1: select existing offer\n2: back to day\n3: quit program\n>>");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		switch(n) {
		case 1: SelectOffer(date);
		case 2: daypage.BacktoDay(date);
		case 3: System.exit(0);
		}
	}
}
