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
    public int cardIndex;
//	public int cardNum;
	
	public Card(int cIndex) { //constructor!
        this.cardIndex = cIndex;
        setProperties(cIndex);
//		this.cardNum = getCardAsNum();
	}
    
    private void setProperties(int cIndex){
        switch(cIndex){
        case 1: theSuit = 1; theValue = 1; break;
        case 2: theSuit = 1; theValue = 2; break;
        case 3: theSuit = 1; theValue = 3; break;
        case 4 : theSuit = 1; theValue = 4; break;
        case 5 : theSuit = 1; theValue = 5; break;
        case 6 : theSuit = 1; theValue = 6; break;
        case 7 : theSuit = 1; theValue = 7; break;
        case 8 : theSuit = 1; theValue = 8; break;
        case 9 : theSuit = 1; theValue = 9; break;
        case 10 : theSuit = 1; theValue = 10; break;
        case 11 : theSuit = 1; theValue = 11; break;
        case 12 : theSuit = 1; theValue = 12; break;
        case 13 : theSuit =  1; theValue = 13; break;
        case 14 : theSuit =  2; theValue = 1; break;
        case 15 : theSuit =  2; theValue = 2; break;
        case 16 : theSuit =  2; theValue = 3; break;
        case 17 : theSuit =  2; theValue = 4; break;
        case 18 : theSuit =  2; theValue = 5; break;
        case 19 : theSuit =  2; theValue = 6; break;
        case 20 : theSuit =  2; theValue = 7; break;
        case 21 : theSuit =  2; theValue = 8; break;
        case 22 : theSuit =  2; theValue = 9; break;
        case 23 : theSuit =  2; theValue = 10; break;
        case 24 : theSuit =  2; theValue = 11; break;
        case 25 : theSuit =  2; theValue = 12; break;
        case 26 : theSuit =  2; theValue = 13; break;
        case 27 : theSuit =  3; theValue = 1; break;
        case 28 : theSuit =  3; theValue = 2; break;
        case 29 : theSuit =  3; theValue = 3; break;
        case 30 : theSuit =  3; theValue = 4; break;
        case 31 : theSuit =  3; theValue = 5; break;
        case 32 : theSuit =  3; theValue = 6; break;
        case 33 : theSuit =  3; theValue = 7; break;
        case 34 : theSuit =  3; theValue = 8; break;
        case 35 : theSuit =  3; theValue = 9; break;
        case 36 : theSuit =  3; theValue = 10; break;
        case 37 : theSuit =  3; theValue = 11; break;
        case 38 : theSuit =  3; theValue = 12; break;
        case 39 : theSuit =  3; theValue = 13; break;
        case 40 : theSuit =  4; theValue = 1; break;
        case 41 : theSuit =  4; theValue = 2; break;
        case 42 : theSuit =  4; theValue = 3; break;
        case 43 : theSuit =  4; theValue = 4; break;
        case 44 : theSuit =  4; theValue = 5; break;
        case 45 : theSuit =  4; theValue = 6; break;
        case 46 : theSuit =  4; theValue = 7; break;
        case 47 : theSuit =  4; theValue = 8; break;
        case 48 : theSuit =  4; theValue = 9; break;
        case 49 : theSuit =  4; theValue = 10; break;
        case 50 : theSuit =  4; theValue = 11; break;
        case 51 : theSuit =  4; theValue = 12; break;
        case 52 : theSuit =  4; theValue = 13; break;
        default : Log.e("CardClass","There was no cardindex given");break;
        }
    
    }
	
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
        return cardIndex;
	}
}
	



