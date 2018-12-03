package com.srh_heidelberg.mealsanddeals;

import java.util.*;
import java.math.*;
import java.security.*;
import com.srh_heidelberg.mealsanddeals.MysqlCon;

public class RegisterFSA {
    public static void main( String[] args ) throws NoSuchAlgorithmException{
    
	String[] tableItem = {"Name","Sex","Birthday","Nationality","Address","Email","Phone Number","Username","Password","Tax ID","Cooking Certificate","Business Certificate","Validation"};
    ArrayList<String> fsaDetailInfo = new ArrayList<String>();
    Scanner input = new Scanner(System.in);
    String tablename = "fsa";

    
    int i = 0;
    while(i < tableItem.length) {
    	String columnItem;
    	if(tableItem[i] == "Validation") {
    		columnItem = "Inactive";
	    	fsaDetailInfo.add(columnItem);
    	}
    	else if(tableItem[i] == "Password") {
        	System.out.print( "Your "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(columnItem.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
        	fsaDetailInfo.add(hashtext);

    	}
    	else if(tableItem[i] == "Cooking Certificate") {
        	System.out.print( "Do You Have Cooking Certificate ? (Yes / No) "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
	    	fsaDetailInfo.add(columnItem);

    	}
    	else if(tableItem[i] == "Business Certificate") {
        	System.out.print( "Do You Have Business Certificate ? (Yes / No) "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
	    	fsaDetailInfo.add(columnItem);

    	}
    	else {
	    	System.out.print( "What is Your "+tableItem[i]+" : " );
	    	columnItem = input.nextLine();
	    	fsaDetailInfo.add(columnItem);

    	}
    		
    	i++;
    }
    MysqlCon.insertToTable(tablename,fsaDetailInfo);
    input.close();
    }

}