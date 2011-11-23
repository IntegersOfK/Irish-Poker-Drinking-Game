package ca.ajwest.irishpoker;

import android.util.Log;

public class Player {

	private Card card1, card2, card3, card4;
	private int playerNum;
	
	public Player(int playerNumber) {
		this.playerNum = playerNumber;
	}
	
	public Card getCard (int cardNum){
		switch (cardNum){
		case 1:
			return card1;
		case 2:
			return card2;
		case 3:
			return card3;
		case 4:
			return card4;
		default:
			Log.e("IrishPokerPlayer" + playerNum, "Card " + cardNum + " does not exist. Returning card1.");
			return card1;
		}
	}

}
