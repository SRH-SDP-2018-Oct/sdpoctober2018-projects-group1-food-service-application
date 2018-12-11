package com.srh_heidelberg.mealsanddeals;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.*;

public class FoodTest {
	
	Food foodList; 
	String testFoodType = null;
	String testFoodName = null;
	String testNameofmeal = null;
	String testHotorcold = null;
	ArrayList<Field> testFoodList = new ArrayList<Field>(); 
	private static FSA loggedInfsa = new FSA();
	@Before
    public void setup() throws ParseException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
		foodList=new Food();
		Cal.ShowCalendar();
		foodList.setFood(Cal.date, loggedInfsa);
		testFoodList = foodList.getFood();
		testFoodType = foodList.getFoodtype();
		testFoodName = foodList.getFoodname();
		testNameofmeal = foodList.getNameofmeal();
		testHotorcold = foodList.getHotorcold();	
    }
	
	
	@Test
	public void testMakeQuery() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, ParseException {		
			
		assertThat(foodList.makeQuery(), hasItems(testFoodType,testFoodName,testNameofmeal,testHotorcold));
		
		System.out.println("Entered Value: foodName="+ testFoodName+" foodType= "+testFoodType
				+" nameOfMeal= "+testNameofmeal+" hotOrCold= "+ testHotorcold);
		System.out.println("\n \n Entered Food option: "+foodList.makeQuery());		
	}
	@Test
	public void testGetFood() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		System.out.println(testFoodList.get(0).getName()+"\n"+testNameofmeal);
		//assertThat(testFoodList, anyOf(is(testFoodType)));
		
		assertThat( testFoodList, contains(
				hasProperty("nameofmeal", is(testNameofmeal)) 
		));
	}

}
