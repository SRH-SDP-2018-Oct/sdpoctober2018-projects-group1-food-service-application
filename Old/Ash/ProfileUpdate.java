package com.srh_heidelberg.mealsanddeals;

import java.util.*;
import java.math.*;
import java.security.*;
import java.sql.SQLException;

import com.srh_heidelberg.mealsanddeals.MysqlCon;

public class ProfileUpdate {
    public static void fsaprofileUpdate( String username ) throws NoSuchAlgorithmException, SQLException{
    
	String[] tableItem = {"Name","Sex","Birthday","Nationality","Address","Phone Number","Password","Tax ID","Cooking Certificate","Business Certificate"};
    ArrayList<String[]> detail = new ArrayList<String[]>();
    ArrayList<String[]> condition = new ArrayList<String[]>();
    Scanner input = new Scanner(System.in);
    String tablename = "fsa";
    String decisionMaker = "";
    String enteredValue = "";
    String oldValue = "";
    String checkingColumnLabel = null;
    String columnLabel = "fsausername";
    condition.add(new String[] {columnLabel,username});
    int i = 0;
    while(i < tableItem.length) {
    	switch (tableItem[i]) {
    	case "Name":{
    		checkingColumnLabel = "name";
    		break;
    	}
    	case "Sex":{
    		checkingColumnLabel = "sex";
    		break;
    	}
    	case "Birthday":{
    		checkingColumnLabel = "birthday";
    		break;
    	}
    	case "Nationality":{
    		checkingColumnLabel = "nationality";
    		break;
    	}
    	case "Address":{
    		checkingColumnLabel = "address";
    		break;
    	}
    	case "Phone Number":{
    		checkingColumnLabel = "phonenumber";
    		break;
    	}
    	case "Password":{
    		checkingColumnLabel = "password";
    		break;
    	}
    	case "Tax ID":{
    		checkingColumnLabel = "taxid";
    		break;
    	}
    	case "Cooking Certificate":{
    		checkingColumnLabel = "cookingcertificate";
    		break;
    	}
    	case "Business Certificate":{
    		checkingColumnLabel = "businesscertificate";
    		break;
    	}
    	}
    	String columnItem;
    	if(tableItem[i] == "Password") {
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
        	detail.add(new String[] {checkingColumnLabel,hashtext});
        	}
    	}
    	else if(tableItem[i] == "Tax ID") {
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username , checkingColumnLabel, columnLabel);
    		System.out.print( "Your "+tableItem[i]+" : ( Your previous "+tableItem[i]+" is : "+oldValue+" )" );
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
        		enteredValue = columnItem;
        		MysqlCon.checkingDups(tablename , enteredValue , checkingColumnLabel);
        		if(MysqlCon.checkingDups(tablename , enteredValue , checkingColumnLabel).equals(enteredValue)) {
                	detail.add(new String[] {checkingColumnLabel,columnItem});
        		}else {
            		System.out.println("\nYour Tax ID exists. make sure that you enter right Tax ID ... \n");
            		i--;
        		}
        	}
    	}
    	else if(tableItem[i] == "Name") {
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username, checkingColumnLabel, columnLabel);
    		System.out.print( "Your "+tableItem[i]+" :  ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
            	detail.add(new String[] {checkingColumnLabel,columnItem});
        	}
    	}
    	else if(tableItem[i] == "Address") {
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username, checkingColumnLabel, columnLabel);
    		System.out.print( "Your "+tableItem[i]+" : ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
            	detail.add(new String[] {checkingColumnLabel,columnItem});
        	}
    	}
    	else if(tableItem[i] == "Phone Number") {
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username, checkingColumnLabel, columnLabel);
    		System.out.print( "Your "+tableItem[i]+" : ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
            	detail.add(new String[] {checkingColumnLabel,columnItem});
        	}
    	}
    	else if(tableItem[i] == "Nationality") {
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username, checkingColumnLabel, columnLabel);
    		System.out.print( "Your "+tableItem[i]+" : ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
            	detail.add(new String[] {checkingColumnLabel,columnItem});
        	}
    	}
    	else if(tableItem[i] == "Cooking Certificate") {
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username , checkingColumnLabel, columnLabel);
        	System.out.print( "Do You Have Cooking Certificate ? (Yes / No)   : ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
        		if(columnItem.equals("Yes")) {
                	detail.add(new String[] {checkingColumnLabel,columnItem});
        		}
        		else if (columnItem.equals("No")) {
                	detail.add(new String[] {checkingColumnLabel,columnItem});
        		}
        		else {
               		System.out.println("\nYou entered Wrong value for Cooking Certificate use Yes or No ...\n");
            		i--;
        		}
        	}
    	}
    	else if(tableItem[i] == "Business Certificate") {
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username , checkingColumnLabel, columnLabel);
        	System.out.print( "Do You Have Business Certificate ? (Yes / No)   : ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
        	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
        		if(columnItem.equals("Yes")) {
                	detail.add(new String[] {checkingColumnLabel,columnItem});
        		}
        		else if (columnItem.equals("No")) {
                	detail.add(new String[] {checkingColumnLabel,columnItem});
        		}
        		else {
               		System.out.println("\nYou entered Wrong value for Business Certificate use Yes or No ...\n");
            		i--;
        		}
        	}
    	}
    	else if(tableItem[i] == "Sex"){
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename , username , checkingColumnLabel, columnLabel);
	    	System.out.print( "What is Your "+tableItem[i]+" ( Male / Female ) : ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
	    	columnItem = input.nextLine();
        	if(columnItem.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
        	}
        	else {
        		if(columnItem.equals("Male")) {
                	detail.add(new String[] {checkingColumnLabel,columnItem});
        		}
        		else if (columnItem.equals("Female")) {
                	detail.add(new String[] {checkingColumnLabel,columnItem});
       		}
        		else {
               		System.out.println("\nYou entered Wrong value for Sex use Male or Female ...\n");
            		i--;
        		}
        	}
        }
    	else if(tableItem[i] == "Birthday"){
    		columnItem = "";
    		oldValue = MysqlCon.oldValueShow(tablename, username, checkingColumnLabel, columnLabel);
	    	System.out.println( "What is Your "+tableItem[i]+" : ( Your previous "+tableItem[i]+" is : "+oldValue+" )");
	    	System.out.print("Year (e.g. 1990) : ");
	    	String birthInfo = "";
	    	birthInfo = input.nextLine();
        	if(birthInfo.equals("")) {
            	detail.add(new String[] {checkingColumnLabel,oldValue});
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
                    	detail.add(new String[] {checkingColumnLabel,columnItem});
                	}
            	}
        	}
    	}
    		
    	i++;
    }
        MysqlCon.updateTable(tablename, detail, condition);
    input.close();
    }

}