//package com.srh_heidelberg.mealsanddeals;

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

//import com.srh_heidelberg.mealsanddeals.MysqlCon;

public class LoginAction {
	public static void main( String args[] ) throws NoSuchAlgorithmException, SQLException {

		Scanner input = new Scanner(System.in);
		
		ArrayList<String> loginInfo = new ArrayList<String>();
		
		String tablename;
		
		String columnItem;
    	
		System.out.print( "What is Your Type? (Customer / FSA / Admin) : " );
    	
		tablename = input.nextLine().toLowerCase();
    	
		System.out.print( "Username : " );
    	
		columnItem = input.nextLine();
    	
		loginInfo.add(columnItem);
    	
//		System.out.print( "Password : " );
    	
//		columnItem = input.nextLine();
        
        
    	final JPasswordField jpf = new JPasswordField();
    	JOptionPane jop = new JOptionPane(jpf, JOptionPane.QUESTION_MESSAGE,
    	        JOptionPane.OK_CANCEL_OPTION);
    	JDialog dialog = jop.createDialog("Password:");
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

		MessageDigest md = MessageDigest.getInstance("MD5");
        
        byte[] messageDigest = md.digest(columnItem.getBytes());
        
        BigInteger number = new BigInteger(1, messageDigest);
        
        String hashtext = number.toString(16);
    	
        loginInfo.add(hashtext);

//    	System.out.println(tablename);
//    	System.out.println(loginInfo);
        MysqlCon.login(tablename,loginInfo);
    	
        input.close();
	}
}
