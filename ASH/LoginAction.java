package com.srh_heidelberg.mealsanddeals;

import java.util.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.math.*;
import java.security.*;
import java.sql.SQLException;


public class LoginAction {
	public static void main( String args[] ) throws NoSuchAlgorithmException, SQLException {

		Scanner input = new Scanner(System.in);
		
		ArrayList<String> loginInfo = new ArrayList<String>();
		
		String tablename = null;
		
		String columnItem;
    	
		System.out.println( "What is Your Type?" );

		System.out.println( "1 - Food Service Agent             [1]");

		System.out.println( "2 - Customer                       [2]");

		System.out.println( "3 - Admin                          [3]");
		String option = input.nextLine();
		
		switch (option) {
    	case "1":{
    		tablename = "fsa";
    		break;
    	}
    	case "2":{
    		tablename = "customer";
    		break;
    	}
    	case "3":{
    		tablename = "admin";
    		break;
    	}
		}
		System.out.print( "Username : " );
    	
		columnItem = input.nextLine();
    	
		loginInfo.add(columnItem);
    	
		// Getting Password hidden
    	final JPasswordField jpf = new JPasswordField();
    	JOptionPane jop = new JOptionPane(jpf, JOptionPane.QUESTION_MESSAGE,
    	        JOptionPane.OK_CANCEL_OPTION);
    	JDialog dialog = jop.createDialog("Please enter your Password:");
    	dialog.addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        SwingUtilities.invokeLater(new Runnable() {
    	            @Override
    	            public void run() {
    	                jpf.requestFocusInWindow();
    	            }
    	        });
    	    }
    	});
    	dialog.setVisible(true);
    	int result = (Integer) jop.getValue();
    	dialog.dispose();
    	char[] password = null;
    	if (result == JOptionPane.OK_OPTION) {
    	    password = jpf.getPassword();
    	}
    	if (password != null) { 
    		columnItem = new String(password);
    	            }
    	// encrypting Password for Security reasons
		MessageDigest md = MessageDigest.getInstance("MD5");
        
        byte[] messageDigest = md.digest(columnItem.getBytes());
        
        BigInteger number = new BigInteger(1, messageDigest);
        
        String hashtext = number.toString(16);
    	
        loginInfo.add(hashtext);
        
        // Calling Login method for checking Login information
        MysqlCon.login(tablename,loginInfo);
    	
        input.close();
	}
}
