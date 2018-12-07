package com.srh_heidelberg.mealsanddeals;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class RankingPage extends CustomerPageNavigation {
	private static Customer loggedInCust = new Customer();

	private List<Ranking> rankingInput;
	private Ranking ranking = new Ranking();
	@Override
	public void Overview() throws NoSuchAlgorithmException, ParseException, SQLException {
		Scanner reader = new Scanner(System.in);
		System.out.println("---->Welcome to RANKING PAGE<----");
		System.out.println("1: Continue with the Ranking of the Food Service Agent \n2: return to Main Page");
		int customerInput = reader.nextInt();
		switchMenupage(customerInput);
		//reader.close();
	}

	@Override
	public void switchMenupage(int input) throws NoSuchAlgorithmException, ParseException, SQLException {	
		int switchMenu = input;
		switch(switchMenu) {
		case 2:
			CustomerMain custMain = new CustomerMain();
			custMain.customerLogin(loggedInCust);
			break;
		case 1:
			ranking.setupRanking(loggedInCust.getCustomerusername());
			rankingValues(ranking.CustomerUnrankedOrders);
			
			while(ranking.rankingHandler(rankingInput,loggedInCust.getCustomerusername())) {
				
			}
			break;
			default: Overview();
			break;
		}
	}

	private boolean rankingValues(ArrayList<Order> unrankedOrders) {
		
		rankingInput = new ArrayList();
		Scanner reader = new Scanner(System.in);
		
		for(int i=0;i<unrankedOrders.size();i++) {
			
			System.out.println("Please type in the Rating for Quality");
			float customerInputQuality = reader.nextFloat();
			ranking.setQuality(customerInputQuality);
			
			System.out.println("Please type in the Rating for Price");
			float customerInputPrice = reader.nextFloat();
			ranking.setPrice(customerInputPrice);
			
			System.out.println("Please type in the Rating for Time");
			float customerInputTime = reader.nextFloat();
			ranking.setTime(customerInputTime);
			
			System.out.println("Please type in the Rating for Service");	
			float customerInputService = reader.nextFloat();
			ranking.setService(customerInputService);
			
			System.out.println("Please type in the Rating for Behaviour");		
			float customerInputBehaviour = reader.nextFloat();
			ranking.setBehaviour(customerInputBehaviour);
			
			System.out.println("Please type in the Rating for Availability");	
			float customerInputAvailability = reader.nextFloat();
			ranking.setAvailablity(customerInputAvailability);
			
			
			System.out.println("Please type in the Rating for Responsibility");
			float customerInputResponsibility = reader.nextFloat();
			ranking.setResponsibility(customerInputResponsibility);
			
			rankingInput.add(new Ranking(ranking.getQuality(),ranking.getPrice(),ranking.getTime(),ranking.getService()
					,ranking.getBehaviour(),ranking.getAvailablity(),ranking.getResponsibility(),unrankedOrders.get(i).getFsausername()));

		}
		
		//reader.close();
		//rankingInput = new ArrayList();
		
		
		
		
		
		return true;
		
	}
}
