package com.srh_heidelberg.mealsanddeals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class CalTest {

	/*@Test
	public void testPrintCalendar() {
		//fail("Not yet implemented");
	}*/

	/*@Test
	public void testChooseDate() {
		//fail("Not yet implemented");
	}*/

	@Test
	public void testGetDate() throws ParseException {
		java.util.Date testDate; 
		Cal.ShowCalendar();		
		testDate = Cal.date;			
		assertThat(Cal.getDate(), anyOf(greaterThan(testDate), equalTo(testDate)));
		System.out.println("Entered date is: "+testDate+"\n and test date is: "+Cal.getDate());
	}

	/*@Test
	public void testShowCalendar() {
		
	}*/

}
