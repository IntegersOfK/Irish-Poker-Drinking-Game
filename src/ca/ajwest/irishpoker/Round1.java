package ca.ajwest.irishpoker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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



public class Round1 extends Activity {

	private Player currentPlayer;
	String LOG = "IrishPokerRound1";
	Button mBlackButton, mRedButton;
	Button mMainCard;
	private TextView mTextView;
	private int numOfPlayers;
	int currentLoop = 0;
 
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		getWindow().setWindowAnimations(android.R.style.Animation_Toast);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round1 Activity");
		
		numOfPlayers = Splashscreen.numPlayers;
		generalDialog("Round 1", "Player 1's turn!", "Continue", false); //the first player needs his/her own dialog popup request because the loop initiates the others.
		theRound();	
		
	}
	
	private void theRound(){
		this.currentLoop++;
		
		
		Log.i(LOG, "On loop: " + currentLoop);
		//Set the textview.
		mTextView = (TextView) findViewById(R.id.textView1);

		//Set initial message:
		mTextView.setText("Do you think this card will be BLACK or RED?");

		//make buttons
		mBlackButton = (Button) findViewById(R.id.blackButton);
		mRedButton = (Button) findViewById(R.id.redButton);
		mMainCard = (Button) findViewById(R.id.buttonMainCard);
		mMainCard.setBackgroundResource(IrishPokerActivity.imageArr[0]); //turn the card on it's back.

		//show the buttons:
		mBlackButton.setVisibility(View.VISIBLE);
		mRedButton.setVisibility(View.VISIBLE);

		Log.i(LOG, "Creating new currentPlayer.");
		currentPlayer = new Player();
		currentPlayer.playerNumSet(currentLoop);
		Log.i(LOG, "currentPlayerNumSet to " + currentLoop);


		//User thinks it's Black.
		mBlackButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				Log.i(LOG, "User thinks card is black.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(1, current);

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
				if ((current.returnSuit()==1)||(current.returnSuit()==2)){ //suit is red, user was incorrect
				//	mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 2!");
					generalDialog("Incorrect", "You were incorrect. Drink for " + currentCardValue + " seconds.", "Done Drinking", true);
				}else{ //suit is black, user was correct
				//	mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 2!");
					generalDialog("Correct!","You were correct. " + dialogText, "Done Drinking", true);
				}

				//hide the buttons
				mBlackButton.setVisibility(View.GONE);
				mRedButton.setVisibility(View.GONE);
				
			}           
		});

		//User thinks it's Red.
		mRedButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//Generate a card
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(1, current);
				
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
				if ((current.returnSuit()==1)||(current.returnSuit()==2)){ //suit is red, user was correct
				//	mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds.");
					generalDialog("Correct!","You were correct. " + dialogText, "Done Drinking", true);
				}else{ //suit is black, user was incorrect
				//	mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds.");
					generalDialog("Incorrect", "You were incorrect. Drink for " + currentCardValue + " seconds.", "Done Drinking", true);
				}

				//hide the buttons
				mBlackButton.setVisibility(View.GONE);
				mRedButton.setVisibility(View.GONE);

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
			nextButton1();
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

	public Button mNextButton;
	public void nextButton1(){
		mNextButton = (Button) findViewById(R.id.blackButton); 
		mNextButton.setVisibility(View.VISIBLE);
		mNextButton.setText("Next");
		
		mNextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			
				
				//Run the second round activity:
		        Intent round2Intent = new Intent(Round1.this, Round2.class);
				Round1.this.startActivity(round2Intent);				
                Log.i(LOG, "Ending Round1 activity");
                Round1.this.finish();
				
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
			Intent previousCardsIntent = new Intent(Round1.this, PreviousCards.class);
			Round1.this.startActivity(previousCardsIntent);
			
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
	    			Intent splashscreenIntent = new Intent(Round1.this, Splashscreen.class);
	    			Round1.this.startActivity(splashscreenIntent);
	                //Now we have to reset the picked card list activity so that the pickedcardlist and players will all reset.
	                GenerateCard.clearPickedCardsList();
	              //Stop Round 1 activity
	                Round1.this.finish();
	            }

	        })
	        .setNegativeButton("Cancel", null)
	        .show();
		}
		
		private void viewRulesSelected(){
			//Start the Rules activity.
			/* Create an Intent that will start the Activity. */
			Intent viewRulesIntent = new Intent(Round1.this, Rules.class);
			Round1.this.startActivity(viewRulesIntent);
		}
 
	
	
	}
