package com.srh_heidelberg.mealsanddeals;

import java.util.*;
import java.math.*;
import java.security.*;
import java.sql.SQLException;

import com.srh_heidelberg.mealsanddeals.MysqlCon;

public class RegisterFSA {
    public static void main( String[] args , String emailId) throws NoSuchAlgorithmException, SQLException{
    
	String[] tableItem = {"Name","Sex","Birthday","Nationality","Address","Email","Phone Number","Username","Password","Tax ID","Cooking Certificate","Business Certificate","Validation"};
    ArrayList<String> fsaDetailInfo = new ArrayList<String>();
    Scanner input = new Scanner(System.in);
    String tablename = "fsa";
    String enteredValue = "";
    String checkingtablename = null;
    String columnLabel = null;
    
    int i = 0;
    while(i < tableItem.length) {
    	String columnItem;
    	if(tableItem[i] == "Validation") {
    		columnItem = "Inactive";
	    	fsaDetailInfo.add(columnItem);
    	}
    	else if(tableItem[i] == "Email") {
	    	fsaDetailInfo.add(emailId);
    	}
    	else if(tableItem[i] == "Password") {
    		columnItem = "";
    		System.out.print( "Your "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
        		System.out.println("\nField "+tableItem[i]+" is Empty. You Should enter your "+tableItem[i]+"  ... \n");
        		i--;
        	}
        	else {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(columnItem.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
        	fsaDetailInfo.add(hashtext);
        	}
    	}
    	else if(tableItem[i] == "Username") {
    		columnItem = "";
    		System.out.print( "Your "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
        		System.out.println("\nField "+tableItem[i]+" is Empty. You Should enter your "+tableItem[i]+"  ... \n");
        		i--;
        	}
        	else {
        		checkingtablename = tablename;
        		enteredValue = columnItem;
        		columnLabel = "fsausername";
        		MysqlCon.checkingDups(checkingtablename , enteredValue , columnLabel);
        		if(MysqlCon.checkingDups(checkingtablename , enteredValue , columnLabel).equals(enteredValue)) {
        			fsaDetailInfo.add(columnItem);
        		}else {
            		System.out.println("\nYour Username has been already taken. Please enter another Username ... \n");
            		i--;
        		}
        	}
    	}
    	else if(tableItem[i] == "Tax ID") {
    		columnItem = "";
    		System.out.print( "Your "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
        		System.out.println("\nField "+tableItem[i]+" is Empty. You Should enter your "+tableItem[i]+"  ... \n");
        		i--;
        	}
        	else {
        		checkingtablename = tablename;
        		enteredValue = columnItem;
        		columnLabel = "taxid";
        		MysqlCon.checkingDups(checkingtablename , enteredValue , columnLabel);
        		if(MysqlCon.checkingDups(checkingtablename , enteredValue , columnLabel).equals(enteredValue)) {
        			fsaDetailInfo.add(columnItem);
        		}else {
            		System.out.println("\nYour Tax ID exists. make sure that you enter right Tax ID ... \n");
            		i--;
        		}
        	}
    	}
    	else if(tableItem[i] == "Cooking Certificate") {
    		columnItem = "";
        	System.out.print( "Do You Have Cooking Certificate ? (Yes / No) "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
        		System.out.println("\nField "+tableItem[i]+" is Empty. You Should enter your "+tableItem[i]+"  ... \n");
        		i--;
        	}
        	else {
        		if(columnItem.equals("Yes")) {
        	    	fsaDetailInfo.add(columnItem);
        		}
        		else if (columnItem.equals("No")) {
        	    	fsaDetailInfo.add(columnItem);
        		}
        		else {
               		System.out.println("\nYou entered Wrong value for Cooking Certificate use Yes or No ...\n");
            		i--;
        		}
        	}
    	}
    	else if(tableItem[i] == "Business Certificate") {
    		columnItem = "";
        	System.out.print( "Do You Have Business Certificate ? (Yes / No) "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
        		System.out.println("\nField "+tableItem[i]+" is Empty. You Should enter your "+tableItem[i]+"  ... \n");
        		i--;
        	}
        	else {
        		if(columnItem.equals("Yes")) {
        	    	fsaDetailInfo.add(columnItem);
        		}
        		else if (columnItem.equals("No")) {
        	    	fsaDetailInfo.add(columnItem);
        		}
        		else {
               		System.out.println("\nYou entered Wrong value for Business Certificate use Yes or No ...\n");
            		i--;
        		}
        	}
    	}
    	else if(tableItem[i] == "Sex"){
    		columnItem = "";
	    	System.out.print( "What is Your "+tableItem[i]+" ( Male / Female ) : " );
	    	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
        		System.out.println("\nField "+tableItem[i]+" is Empty. You Should enter your "+tableItem[i]+"  ... \n");
        		i--;
        	}
        	else {
        		if(columnItem.equals("Male")) {
        	    	fsaDetailInfo.add(columnItem);
        		}
        		else if (columnItem.equals("Female")) {
        	    	fsaDetailInfo.add(columnItem);
        		}
        		else {
               		System.out.println("\nYou entered Wrong value for Sex use Male or Female ...\n");
            		i--;
        		}
        	}
        }
    	else if(tableItem[i] == "Birthday"){
    		columnItem = "";
	    	System.out.println( "What is Your "+tableItem[i]+" : " );
	    	System.out.print("Year (e.g. 1990) : ");
	    	String birthInfo = "";
	    	birthInfo = input.nextLine();
        	if(birthInfo.equals("")) {
        		System.out.println("\nYou Have to Enter your Birthday Year \n");
        		i--;
        	}
        	else {
        		columnItem = columnItem + birthInfo+"-";
        		birthInfo = "";
        		System.out.print("Month (e.g. 01) : ");
    	    	birthInfo = input.nextLine();
           	if(birthInfo.equals("")) {
            		System.out.println("\nYou Have to Enter your Birthday Month \n");
            		i--;
            	}
            	else {
            		columnItem = columnItem + birthInfo+"-";
            		birthInfo = "";
            		System.out.print("Day (e.g. 01) : ");
        	    	birthInfo = input.nextLine();
               	if(birthInfo.equals("")) {
                		System.out.println("\nYou Have to Enter your Birthday Day \n");
                		i--;
                	}
                	else {
                		columnItem = columnItem + birthInfo;
                		fsaDetailInfo.add(columnItem);
                	}
            	}
        	}
    	}
    	else {
    		columnItem = "";
	    	System.out.print( "What is Your "+tableItem[i]+" : " );
	    	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
        		System.out.println("\nField "+tableItem[i]+" is Empty. You Should enter your "+tableItem[i]+"  ... \n");
        		i--;
        	}
        	else {
	    	fsaDetailInfo.add(columnItem);
        	}
    	}
    		
    	i++;
    }
    MysqlCon.insertToTable(tablename,fsaDetailInfo);
    MealsandDeals.main(null);
    input.close();
    }

}