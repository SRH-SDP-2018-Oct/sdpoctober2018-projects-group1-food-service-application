package com.srh_heidelberg.mealsanddeals;

import java.util.*;
import java.math.*;
import java.security.*;
import java.sql.SQLException;

import com.srh_heidelberg.mealsanddeals.MysqlCon;

public class RegisterCustomer {
    public static void main( String[] args , String emailId) throws NoSuchAlgorithmException, SQLException{
    
	String[] tableItem = {"Name","Sex","Birthday","Nationality","Address","Email","Phone Number","Username","Password"};
    ArrayList<String> customerDetailInfo = new ArrayList<String>();
    Scanner input = new Scanner(System.in);
    String tablename = "customer";
    String enteredValue = "";
    String checkingtablename = null;
    String columnLabel = null;
    
    int i = 0;
    while(i < tableItem.length) {
    	String columnItem;
    	if(tableItem[i] == "Email") {
	    	customerDetailInfo.add(emailId);
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
        	customerDetailInfo.add(hashtext);
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
        		columnLabel = "customerusername";
        		MysqlCon.checkingDups(checkingtablename , enteredValue , columnLabel);
        		if(MysqlCon.checkingDups(checkingtablename , enteredValue , columnLabel).equals(enteredValue)) {
        			customerDetailInfo.add(columnItem);
        		}else {
            		System.out.println("\nYour Username has been already taken. Please enter another Username ... \n");
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
        	    	customerDetailInfo.add(columnItem);
        		}
        		else if (columnItem.equals("Female")) {
        	    	customerDetailInfo.add(columnItem);
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
                		customerDetailInfo.add(columnItem);
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
	    	customerDetailInfo.add(columnItem);
        	}
    	}
    		
    	i++;
    }
    MysqlCon.insertToTable(tablename,customerDetailInfo);
    MealsandDeals.main(null);
    input.close();
    }

}