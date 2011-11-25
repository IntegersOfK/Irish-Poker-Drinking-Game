package ca.ajwest.irishpoker;

import android.util.Log;

public class Player {

	public Card card1, card2, card3, card4;
	private int playerNum;
	
	public Player() {
		
	}
	
	public void playerNumSet(int playerNumber){
		this.playerNum = playerNumber;
	}
	
	public int getPlayerNum(){
		return playerNum;
	}
	
	public void setCard (int cardNum, Card card){
		switch (cardNum){
			case 1:
			this.card1 = card;
			break;
			case 2:
				this.card2 = card;
				break;
			case 3:
				this.card3 = card;
				break;
			case 4:
				this.card4 = card;
				break;
				default:
					Log.e("IrishPokerPlayerClass", "Problem setting card.");
		}
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
