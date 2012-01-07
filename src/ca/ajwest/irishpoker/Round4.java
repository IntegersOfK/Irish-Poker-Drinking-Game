package ca.ajwest.irishpoker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Round4 extends Activity {

	private Player currentPlayer;
	private TextView mTextView;
	private Button mMainCard, mTopLeftCard, mTopRightCard, mTopMiddleCard;
	private int numOfPlayers;
	int currentLoop = 0;



	String LOG = "IrishPokerRound4";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round4 Activity");

		numOfPlayers = Splashscreen.numPlayers;
		generalDialog("Round 4", "Player 1's turn!", "Continue", false); //the first player needs his/her own dialog popup request because the loop initiates the others.
		theRound();
	}

	private void theRound(){
		this.currentLoop++;
		
		

		//Set the textview.
		mTextView = (TextView) findViewById(R.id.textView1);

		mTextView.setText(null);
		LinearLayout layoutPieceToHide = (LinearLayout) findViewById(R.id.linearLayout2);
//		layoutPieceToHide.setVisibility(View.GONE); //Free up space for the card display, seeing as we're not giving direction here (though we probably should).
		mMainCard = (Button) findViewById(R.id.buttonMainCard);
		mTopLeftCard = (Button) findViewById(R.id.imageButton1);
		mTopMiddleCard = (Button) findViewById(R.id.imageButton2);
		mTopRightCard = (Button) findViewById(R.id.imageButton3);
		
		mMainCard.setBackgroundResource(IrishPokerActivity.imageArr[0]); //turn the card on it's back.


		//We have to find the right player again to get it's cards from previous rounds.
        currentPlayer = new Player();
        switch(currentLoop){
        case 1:
        	currentPlayer = IrishPokerActivity.player1;
        	break;
        case 2:
        	currentPlayer = IrishPokerActivity.player2;
        	break;
        case 3:
        	currentPlayer = IrishPokerActivity.player3;
        	break;
        case 4:
        	currentPlayer = IrishPokerActivity.player4;
        	break;
        case 5:
        	currentPlayer = IrishPokerActivity.player5;
        	break;
        case 6:
        	currentPlayer = IrishPokerActivity.player6;
        	break;
        case 7:
        	currentPlayer = IrishPokerActivity.player7;
        	break;
        case 8:
        	currentPlayer = IrishPokerActivity.player8;
        	break;
        case 9:
        	currentPlayer = IrishPokerActivity.player9;
        	break;
        case 10:
        	currentPlayer = IrishPokerActivity.player10;
        	break;
        }


		mTopLeftCard.setBackgroundResource(IrishPokerActivity.imageArr[currentPlayer.card1.cardIndex]);
		mTopMiddleCard.setBackgroundResource(IrishPokerActivity.imageArr[currentPlayer.card2.cardIndex]);
		mTopRightCard.setBackgroundResource(IrishPokerActivity.imageArr[currentPlayer.card3.cardIndex]);
		mTopLeftCard.setVisibility(View.VISIBLE);
		mTopRightCard.setVisibility(View.VISIBLE);
		mTopMiddleCard.setVisibility(View.VISIBLE);

		//set up dialog
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.suitselectalert);
		//dialog.setTitle("Which suit do you think the card will be?");  //This is now in the XML.
		dialog.setCancelable(false);
		Button mButtonHeart = (Button) dialog.findViewById(R.id.buttonHeart);
		Button mButtonSpade = (Button) dialog.findViewById(R.id.buttonSpade);
		Button mButtonDiamond = (Button) dialog.findViewById(R.id.buttonDiamond);
		Button mButtonClub = (Button) dialog.findViewById(R.id.buttonClub);

		mButtonHeart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "Selected Heart");
				dialog.dismiss();
				suitValueSelect(1);
			}
		});

		mButtonSpade.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "Selected Spade");
				dialog.dismiss();
				suitValueSelect(3);
			}
		});

		mButtonDiamond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "Selected Diamond");
				dialog.dismiss();
				suitValueSelect(2);
			}
		});

		mButtonClub.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "Selected Club");
				dialog.dismiss();
				suitValueSelect(4); 
			}
		});
		
		Button mNextButton = (Button) findViewById(R.id.blackButton); 
		mNextButton.setVisibility(View.VISIBLE);
		Button mRedButtonToRemove = (Button) findViewById(R.id.redButton); //we just need to remove it from the layout, so we have to make it first.
		mRedButtonToRemove.setVisibility(View.GONE);
		mNextButton.setText("Click Here to Select Your Suit Guess...");
		mNextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Player has requested to select suit. Launching suit selction dialog.");
				dialog.show();
			}           
		});

	}
	
	

	private void generalDialog(String title, String message, String confirmText, final boolean callIsAnotherRound) {

		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_menu_more)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton(confirmText, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				//          	Do stuff here        
				if (callIsAnotherRound==true){
					isAnotherRound();
				}
			} 

		})
		//.setNegativeButton("Cancel", null) No ability to cancel.
		.show();
	}
	

	private void isAnotherRound(){
		//first we should save the currentplayer:
		saveCurrentPlayer();

		Log.i(LOG, "About to check if numOfPlayers<currentLoop " + numOfPlayers + " - " + currentLoop );
		if (numOfPlayers > currentLoop){
			String playerText = currentLoop + 1 + "";
			generalDialog("Continue","Player " + playerText + "'s turn.", "Continue", false);
			theRound();
		}else{
			Log.i(LOG, "Done looping.");
			Log.i(LOG, "Game over! YAY!");
			 
			
			//Ask the user if they want to restart
	        new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Game Over!")
	        .setMessage("Play again?")
	        .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {

	            public void onClick(DialogInterface dialog, int which) {

	            	//Start splashscreen activity.
	    			Intent splashscreenIntent = new Intent(Round4.this, Splashscreen.class);
	    			Round4.this.startActivity(splashscreenIntent);
	                //Now we have to reset the picked card list activity so that the pickedcardlist and players will all reset.
	                GenerateCard.clearPickedCardsList();
	              //Stop activity
	                Round4.this.finish();
	                
	            }

	        })
	        .setNegativeButton("Cancel", null)
	        .show();
			
			
		}
	}

	private void saveCurrentPlayer() {
		Log.i(LOG, "Saving player.");
		int tempCompare = currentPlayer.getPlayerNum();
		Log.i(LOG, "tempCompare is: " + tempCompare);
		switch (tempCompare){
		case 1:
			IrishPokerActivity.player1 = currentPlayer;
			Log.i(LOG, "Saved player1");
			break;
		case 2:
			IrishPokerActivity.player2 = currentPlayer;
			Log.i(LOG, "Saved player2");
			break;
		case 3:
			IrishPokerActivity.player3 = currentPlayer;
			Log.i(LOG, "Saved player3");
			break;
		case 4:
			IrishPokerActivity.player4 = currentPlayer;
			Log.i(LOG, "Saved player4");
			break;
		case 5:
			IrishPokerActivity.player5 = currentPlayer;
			Log.i(LOG, "Saved player5");
			break;
		case 6:
			IrishPokerActivity.player6 = currentPlayer;
			Log.i(LOG, "Saved player6");
			break;
		case 7:
			IrishPokerActivity.player7 = currentPlayer;
			Log.i(LOG, "Saved player7");
			break;
		case 8:
			IrishPokerActivity.player8 = currentPlayer;
			Log.i(LOG, "Saved player8");
			break;
		case 9:
			IrishPokerActivity.player9 = currentPlayer;
			Log.i(LOG, "Saved player9");
			break;
		case 10:
			IrishPokerActivity.player10 = currentPlayer;
			Log.i(LOG, "Saved player10");
			break;
		default:
			Log.e(LOG, "Couldn't save player.");
		}		
	}



	private void suitValueSelect(int suitValueSelected){

		Log.i(LOG, "User has selected suit value: " + suitValueSelected );
		Card current = GenerateCard.generateCard();
		currentPlayer.card4 = current;
		
		//Checking options to see if face cards are worth 10 or not 
		int currentCardValue = current.returnValue(); 
		if (Splashscreen.option1 == 2){
			if (currentCardValue > 10) {
				currentCardValue = 10;
			}
		}

		//Checking options to see if we're supposed to choose a random player to drink or not.
		String dialogText = "Make somebody else drink for " + currentCardValue + " seconds."; 
		if (Splashscreen.option2 == 2){
			int min = 1;
			int max = numOfPlayers;
			int ranValue = min + (int)(Math.random() * ((max - min) + 1));
            Log.i(LOG, "ranValue=" + ranValue);
            
			dialogText = "Make player " + ranValue + " drink for " + currentCardValue + " seconds.";
		}
		
		//flip the card over
		mMainCard.setBackgroundResource(IrishPokerActivity.imageArr[current.cardIndex]);
		//did user guess correctly?

		if (suitValueSelected == current.returnSuit()){
			Log.i(LOG, "User guessed correctly.");
			generalDialog("Correct" , "You were correct! " + dialogText, "Done Drinking", true);
		}else{
			Log.i(LOG, "User guessed incorrectly");
			generalDialog("Incorrect" , "You were incorrect! Drink for " + currentCardValue + " seconds.", "Done Drinking", true);
		}
	}


	
	   @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.options_menu, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle item selection
			switch (item.getItemId()) {
			case R.id.previous_cards:
				previousCardsSelected();
				return true;
			case R.id.restart_game:
				restartGameSelected();
				return true;
			case R.id.view_rules:
				viewRulesSelected();
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}

		private void previousCardsSelected() {

			/* Create an Intent that will start the Activity. */
			Intent previousCardsIntent = new Intent(Round4.this, PreviousCards.class);
			Round4.this.startActivity(previousCardsIntent);
			
		}

		private void restartGameSelected() {
			
			 //Ask the user if they want to quit
	        new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Restart?")
	        .setMessage("Are you sure that you want to quit this game? All players and cards will be reset.")
	        .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {

	            public void onClick(DialogInterface dialog, int which) {

	            	//Start splashscreen activity.
	    			Intent splashscreenIntent = new Intent(Round4.this, Splashscreen.class);
	    			Round4.this.startActivity(splashscreenIntent);
	                //Now we have to reset the picked card list activity so that the pickedcardlist and players will all reset.
	                GenerateCard.clearPickedCardsList();
	              //Stop activity
	                Round4.this.finish();
	                
	            }

	        })
	        .setNegativeButton("Cancel", null)
	        .show();
		}
		
		private void viewRulesSelected(){
			//Start the Rules activity.
			/* Create an Intent that will start the Activity. */
			Intent viewRulesIntent = new Intent(Round4.this, Rules.class);
			Round4.this.startActivity(viewRulesIntent);
		}


}