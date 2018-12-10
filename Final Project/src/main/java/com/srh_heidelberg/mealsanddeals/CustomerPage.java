package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

public abstract class CustomerPage {
public abstract void Overview() throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException;
public abstract void Overview(Customer loggedInCustomer) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException;
public abstract void switchMenupage(int input) throws NoSuchAlgorithmException, ParseException, SQLException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException;

}
