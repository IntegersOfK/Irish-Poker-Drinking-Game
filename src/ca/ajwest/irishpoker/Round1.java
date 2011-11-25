package ca.ajwest.irishpoker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;



public class Round1 extends Activity {

	private Player currentPlayer;
	String LOG = "IrishPokerRound1";
	Button mBlackButton, mRedButton, mReadyButton;
	ImageButton mMainCard;
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

		numOfPlayers = IrishPokerActivity.NUMBEROFPLAYERS;
		//nextPlayerPrompt();
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
		mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);

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

				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				//did user guess correctly?
				if ((current.returnSuit()==1)||(current.returnSuit()==2)){ //suit is red, user was incorrect
					mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 2!");
				}else{ //suit is black, user was correct
					mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 2!");
				}

				//hide the buttons
				mBlackButton.setVisibility(View.GONE);
				mRedButton.setVisibility(View.GONE);
				isAnotherRound();//enables the next button
			}           
		});

		//User thinks it's Red.
		mRedButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//Generate a card
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(1, current);

				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				//did user guess correctly?
				if ((current.returnSuit()==1)||(current.returnSuit()==2)){ //suit is red, user was correct
					mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 2!");
				}else{ //suit is black, user was incorrect
					mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 2!");
				}

				//hide the buttons
				mBlackButton.setVisibility(View.GONE);
				mRedButton.setVisibility(View.GONE);
				isAnotherRound(); //enables the first next button

			}           
		});
	}

	private void isAnotherRound(){
		//first we should save the currentplayer:
		saveCurrentPlayer();


		Log.i(LOG, "About to check if numOfPlayers<currentLoop " + numOfPlayers + " - " + currentLoop );
		if (numOfPlayers > currentLoop){
			theRound();
		}else{
			Log.i(LOG, "Done looping. Enabling Next Button");
			nextButton1();
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
				Round1.this.finish();



				//create a new intent...
				//  Intent intent = new Intent();
				//add "returnKey" as a key and assign it the card value
				//I don't really need to send back the playernumber, just say that the result was ok.
				//intent.putExtra("returnKey", playerNum);
				//get ready to send the result back to the caller (MainActivity)
				//and put our intent into it (RESULT_OK will tell the caller that 
				//we have successfully accomplished our task..
				//setResult(RESULT_OK);
				//close this Activity...
				Log.i(LOG, "Ending Round1 activity");
				Round1.this.finish();

			}           
		});
	}
}
