package ca.ajwest.irishpoker;

import android.util.Log;

public class Card {
	
	/**
	 * Suits: 1=hearts, 2=diamonds, 3=spades, 4=clubs
	 * Values: A=1, J=11, Q=12, K=13
	 * 
	 * 
	 */

	private int theSuit;
	private int theValue;
	private String red, black;
	private String colour;
	public int cardNum;
	
	public Card(int suit, int value) { //constructor!
		theSuit = suit;
		theValue = value;
		cardNum = getCardAsNum();
	}

	//imgage?
	
	private String cardColour(String theSuit){
		if (theSuit.equals("heart")||theSuit.equals("diamond")){
			colour = red;
		}else{
			colour = black;
		}
		return colour;
	}
	
	public int returnSuit(){
		return theSuit;
	}
	
	public int returnValue(){
		return theValue;
	}

	private int getCardAsNum(){
		//this is used to make sure we're not using the same card twice.
		String appendedNumbers = theSuit + "" + theValue;
		cardNum = Integer.parseInt(appendedNumbers);

		switch (theSuit){
		case 1: cardNum = theValue; //For hearts, the values already match the card numbers.
		break;
		case 2: //for diamonds
			switch (theValue){
				case 1: cardNum = 14;
				break;
				case 2: cardNum = 15;
				break;
				case 3: cardNum = 16;
				break;
				case 4:cardNum = 17;
				break;
				case 5:cardNum = 18;
				break;
				case 6:cardNum = 19;
				break;
				case 7:cardNum = 20;
				break;
				case 8:cardNum = 21;
				break;
				case 9:cardNum = 22;
				break;
				case 10:cardNum = 23;
				break;
				case 11:cardNum = 24;
				break;
				case 12:cardNum = 25;
				break;
				case 13:cardNum = 26;
				break;
				default: cardNum = 0;
				break;
			}
			break;
		case 3: //for spades
			switch (theValue){
				case 1: cardNum = 27;
				break;
				case 2: cardNum = 28;
				break;
				case 3: cardNum = 29;
				break;
				case 4:cardNum = 30;
				break;
				case 5:cardNum = 31;
				break;
				case 6:cardNum = 32;
				break;
				case 7:cardNum = 33;
				break;
				case 8:cardNum = 34;
				break;
				case 9:cardNum = 35;
				break;
				case 10:cardNum = 36;
				break;
				case 11:cardNum = 37;
				break;
				case 12:cardNum = 38;
				break;
				case 13:cardNum = 39;
				break;
				default: cardNum = 0;
				break;
			}
			break;
		case 4: //for clubs
			switch (theValue){
				case 1: cardNum = 40;
				break;
				case 2: cardNum = 41;
				break;
				case 3: cardNum = 42;
				break;
				case 4:cardNum = 43;
				break;
				case 5:cardNum = 44;
				break;
				case 6:cardNum = 45;
				break;
				case 7:cardNum = 46;
				break;
				case 8:cardNum = 47;
				break;
				case 9:cardNum = 48;
				break;
				case 10:cardNum = 49;
				break;
				case 11:cardNum = 50;
				break;
				case 12:cardNum = 51;
				break;
				case 13:cardNum = 52;
				break;
				default: cardNum = 0;
				break;
			}
			break;
		default: cardNum = 0;
		Log.e("CardClass", "No suit was pushed!");
		break;
		}

		return cardNum;
	}
}



