package ca.ajwest.irishpoker;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.TextView;


public class Round2 extends Activity{

	private Player currentPlayer;
	String LOG = "IrishPokerRound2";
	private TextView mTextView;
	private Button mMainCard, mLeftCard;
	private int numOfPlayers;
	int currentLoop = 0;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round2 Activity");

		numOfPlayers = Splashscreen.numPlayers;
		generalDialog("Round 2", "Player 1's turn!", "Continue", false); //the first player needs his/her own dialog popup request because the loop initiates the others.
		theRound();	

	}

	private void theRound (){
		this.currentLoop++;

		

		mMainCard = (Button) findViewById(R.id.buttonMainCard);
		mLeftCard = (Button) findViewById(R.id.buttonLeftCard);

		//Set the textview.
		mTextView = (TextView) findViewById(R.id.textView1);
		mTextView.setText("Click the card that you think will be higher.");
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


		mLeftCard.setBackgroundResource(IrishPokerActivity.imageArr[currentPlayer.card1.cardIndex]);
		mLeftCard.setVisibility(View.VISIBLE);


		mMainCard.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User thinks invis card is higher.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(2, current);
				
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
				if (current.returnValue()>currentPlayer.card1.returnValue()){ //card is higher. User was correct.
					//		if ((currentPlayer.card2.returnValue())==(currentPlayer.card1.returnValue())){ //TODO don't think this works for some reason. Watch out for cards that are the same.
					//			//if card is the same value (ie. get 2 kings. Make user drink and give drinks)
					//			mTextView.setText("They're the same! You AND somebody else must drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
					//		}else{
					//mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
					generalDialog("Correct", "You were correct! " + dialogText, "Done Drinking", true);
					//		}
				}else{ //the other was higher, user was incorrect
					generalDialog("Incorrect", "You were incorrect. Drink for " + currentCardValue + " seconds.", "Done Drinking", true);				}
				//Make the cards non-clickable again.
				mMainCard.setOnClickListener(null);
				mLeftCard.setOnClickListener(null);
			}
		});

		mLeftCard.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User thinks vis card is higher.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(2, current);
				
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
				if (current.returnValue()<currentPlayer.card1.returnValue()){ //card is higher. User was correct.
					//TODO if card is the same value (ie. get 2 kings. Make user drink and give drinks)
					//mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
					generalDialog("Correct", "You were correct! Make somebody else drink for " + currentCardValue + " seconds.", "Done Drinking", true);
				}else{ //the other was higher, user was incorrect
					generalDialog("Incorrect", "You were incorrect. " + dialogText, "Done Drinking", true);
				}
				//Make the cards non-clickable again.
				mMainCard.setOnClickListener(null);
				mLeftCard.setOnClickListener(null);
			}
		});
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
			Log.i(LOG, "Done looping. Enabling Next Button");
			nextButton2();
		}
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
			break;
		case 5:
			IrishPokerActivity.player5 = currentPlayer;
			break;
		case 6:
			IrishPokerActivity.player6 = currentPlayer;
			break;
		case 7:
			IrishPokerActivity.player7 = currentPlayer;
			break;
		case 8:
			IrishPokerActivity.player8 = currentPlayer;
			break;
		case 9:
			IrishPokerActivity.player9 = currentPlayer;
			break;
		case 10:
			IrishPokerActivity.player10 = currentPlayer;
			break;
		default:
			Log.e(LOG, "Couldn't save player.");
		}		
	}



	public Button mNextButton, mHideButton;
	private void nextButton2(){
		mNextButton = (Button) findViewById(R.id.blackButton);
		mHideButton = (Button) findViewById(R.id.redButton);
		mNextButton.setVisibility(View.VISIBLE);
		mHideButton.setVisibility(View.GONE);
		mNextButton.setText("Next");
		mNextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Round3");

				//Run the third round activity:
				Intent round3Intent = new Intent(Round2.this, Round3.class);
				Round2.this.startActivity(round3Intent);
				Round2.this.finish();

			}           
		});
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
		Intent previousCardsIntent = new Intent(Round2.this, PreviousCards.class);
		Round2.this.startActivity(previousCardsIntent);

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
				Intent splashscreenIntent = new Intent(Round2.this, Splashscreen.class);
				Round2.this.startActivity(splashscreenIntent);
				//Now we have to reset the picked card list activity so that the pickedcardlist and players will all reset.
				GenerateCard.clearPickedCardsList();
				//Stop activity
				Round2.this.finish();

			}

		})
		.setNegativeButton("Cancel", null)
		.show();
	}
	
	private void viewRulesSelected(){
		//Start the Rules activity.
		/* Create an Intent that will start the Activity. */
		Intent viewRulesIntent = new Intent(Round2.this, Rules.class);
		Round2.this.startActivity(viewRulesIntent);
	}

}

