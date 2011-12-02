/**
 * Spits out a Card and adds it to a list.
 */
package ca.ajwest.irishpoker;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * @author ajwest
 *
 */
public class GenerateCard {

	private static String LOG = "GenerateCardActivity";
	public static List<Integer> pickedCardList = new ArrayList<Integer>();
    public static List<Integer> unPickedCardList = new ArrayList<Integer>();
	public GenerateCard() {

	}


	//spits out a card and adds it to the pile of discards.
	public static Card generateCard() {
		Log.i(LOG, "Generating card.");

		int i = 0;

		if (pickedCardList.size()>=52){
			Log.e(LOG, "All 52 cards have already been distributed!");
		}else{
			do{
//				int min = 1;
//				int max = 4;
//				int ranSuit = min + (int)(Math.random() * ((max - min) + 1));
				int min = 1;
				int max = 52;
				int ranValue = min + (int)(Math.random() * ((max - min) + 1));
//				Log.i(LOG, "ranSuit= " + ranSuit + " and ranValue= " + ranValue);
                Log.i(LOG, "ranValue=" + ranValue);
                
                Card c = new Card(ranValue);
    
  //              if (pickedCardList.contains(c.cardIndex)){
                
  //              }


				if (pickedCardList.contains(c.cardIndex)){
					Log.i(LOG, "Card was already picked! Have to repick. Repicking now.");
				}else{
					Log.i(LOG, "Card pick sucessful. (Card wasn't already picked.) Add new card to list.");
					pickedCardList.add(c.cardIndex);
					Log.i(LOG, "New Card Index is: " + c.cardIndex);
					i = 1;
					return c;
				}
			}while(i==0);
		}
		return null;
	}

}