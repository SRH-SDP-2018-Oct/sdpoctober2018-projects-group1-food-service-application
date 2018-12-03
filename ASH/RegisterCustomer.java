package com.srh_heidelberg.mealsanddeals;

import java.util.*;
import java.math.*;
import java.security.*;
import com.srh_heidelberg.mealsanddeals.MysqlCon;

public class RegisterCustomer {
    public static void main( String[] args ) throws NoSuchAlgorithmException{
    
	String[] tableItem = {"Name","Sex","Birthday","Nationality","Address","Email","Phone Number","Username","Password"};
    ArrayList<String> customerDetailInfo = new ArrayList<String>();
    Scanner input = new Scanner(System.in);
    String tablename = "customer";

    
    int i = 0;
    while(i < tableItem.length) {
    	String columnItem;
    	if(tableItem[i] != "Password") {
	    	System.out.print( "What is Your "+tableItem[i]+" : " );
	    	columnItem = input.nextLine();
	    	customerDetailInfo.add(columnItem);
    	}
    	else if(tableItem[i] == "Password") {
        	System.out.print( "Your Password "+tableItem[i]+" : " );
        	columnItem = input.nextLine();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(columnItem.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
        	customerDetailInfo.add(hashtext);

    	}
    	i++;
    }
    MysqlCon.insertToTable(tablename,customerDetailInfo);
    input.close();
    }

}