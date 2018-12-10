package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

public abstract class CustomerPageNavigation {
public abstract void Overview() throws NoSuchAlgorithmException, ParseException, SQLException;
public abstract void switchMenupage(int input) throws NoSuchAlgorithmException, ParseException, SQLException;
}
