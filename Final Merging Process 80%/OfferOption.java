package com.srh_heidelberg.mealsanddeals;

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
	private static FSA loggedInfsa = new FSA();
	
	public static void PrintOfferList(Date date) throws ParseException {
		System.out.print("\033[H\033[2J");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String[]> detail = new ArrayList<String[]>();
		
		offerlist = new ArrayList<Offer>();
		detail.add(new String[] {"fsausername", loggedInfsa.getFsausername()});
		detail.add(new String[] {"date", dateFormat.format(date)});
		db.selectOffer("offer", detail, offerlist);
		
		System.out.println("---OFFER LIST---");
		System.out.println("[id]foodamount  discount");
		
		for(int i=0; i<offerlist.size(); i++) {
			System.out.println("["+ offerlist.get(i).getOfferid() +"]" + offerlist.get(i).getFoodamount() +"  "+ offerlist.get(i).getDiscount());
		}
	}
	public static void AddOffer(Date date, int foodid) throws NoSuchAlgorithmException, ParseException, SQLException {
		ArrayList<String> offercolumn = new ArrayList<String>();
		ArrayList<String> detail = new ArrayList<String>();
		db.getColumnName("offer", offercolumn);
		
		Scanner sc = new Scanner(System.in);
		
		int offerid = 0;
		if(FoodOption.checkOffer(date, foodid)==true) {
			System.out.println("WARNING : There is offer with the food you want to delete! \n1: back to food option");
			int n = sc.nextInt();
			switch(n) {
			case 1: FoodOption.ShowFoodOption(date, loggedInfsa);
			}
		} else {
			for(int i=0; i<offercolumn.size(); i++) {
				if(offercolumn.get(i).equals("date")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					detail.add(dateFormat.format(date));
				} else if(offercolumn.get(i).equals("foodid")) {
					detail.add(String.valueOf(foodid));
				} else if(offercolumn.get(i).equals("offerid")) {
					detail.add("0");
				} else if(offercolumn.get(i).equals("fsausername")) {
					detail.add(loggedInfsa.getFsausername()); // should be fixed
				}
				else {
					String info = "";
					System.out.print("Enter "+ offercolumn.get(i) +">> ");
					info = sc.nextLine();
					detail.add(info);
				}	
			}
				
			db.insertToTable("offer", detail);
		}
	}
	public static void EditOffer(Offer newOffer) throws SQLException {
		ArrayList<String> offercolumn = new ArrayList<String>();
		ArrayList<String[]> offerinfo = new ArrayList<String[]>();
		
		db.getColumnName("offer", offercolumn);
		
		offercolumn.remove("offerid");
		offercolumn.remove("foodid");
		offercolumn.remove("fsausername");
		
		newOffer.setOffer(offercolumn, offerinfo);
		ArrayList<String[]> detail = new ArrayList<String[]>();
		ArrayList<String[]> condition = new ArrayList<String[]>();
		
		for(int i=0; i<offerinfo.size(); i++) 
			System.out.print("[" + (i+1) +"]" + offerinfo.get(i)[0]+ ": "+ offerinfo.get(i)[1] +"\t");
		
		System.out.println("");
		
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
				detail.add(new String[] {offercolumn.get(num-1), editinfo});
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
	public static void SelectOffer(Date date) throws ParseException, NoSuchAlgorithmException, SQLException {
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
		System.out.print("1: edit offer\n2: delete offer\n3: back to offer option\n>>");
		int n = sc.nextInt();
		
		switch(n) {
		case 1: EditOffer(newOffer);
				ShowOfferOption(date,loggedInfsa);
		case 2: DeleteOffer(id);
				ShowOfferOption(date,loggedInfsa);
		case 3: ShowOfferOption(date,loggedInfsa);
		}
	}
	public static void ShowOfferOption(Date date , FSA loggedInFsa) throws ParseException, NoSuchAlgorithmException, SQLException {
		loggedInfsa = loggedInFsa;
		FsaDayPage daypage = new FsaDayPage();
		PrintOfferList(date);
		System.out.print("1: select existing offer\n2: back to day\nn>>");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		switch(n) {
		case 1: SelectOffer(date);
		case 2: daypage.BacktoDay(date);
		}
	}
}
