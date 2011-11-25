package ca.ajwest.irishpoker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class Round2 extends Activity{

	private Player currentPlayer;
	String LOG = "IrishPokerRound2";
	private TextView mTextView;
	private ImageButton mMainCard, mLeftCard;
	private int numOfPlayers;
	int currentLoop = 0;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round2 Activity");

		numOfPlayers = IrishPokerActivity.NUMBEROFPLAYERS;
		theRound();	
		
	}

	private void theRound (){
		this.currentLoop++;
		
		mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);
		mLeftCard = (ImageButton) findViewById(R.id.buttonLeftCard);
		
		//Set the textview.
        mTextView = (TextView) findViewById(R.id.textView1);
        mTextView.setText("Click the card that you think will be higher.");
        
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
        
		
        
		mLeftCard.setImageResource(IrishPokerActivity.imageArr[currentPlayer.card1.cardNum]);
        mLeftCard.setVisibility(View.VISIBLE);
        
        
		mMainCard.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User thinks invis card is higher.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(2, current);

				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				//did user guess correctly?
				if (current.returnValue()>currentPlayer.card1.returnValue()){ //card is higher. User was correct.
					if ((currentPlayer.card2.returnValue())==(currentPlayer.card1.returnValue())){ //TODO don't think this works for some reason. Watch out for cards that are the same.
						//if card is the same value (ie. get 2 kings. Make user drink and give drinks)
						mTextView.setText("They're the same! You AND somebody else must drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
					}else{
						mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
					}
				}else{ //the other was higher, user was incorrect
					mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
				}
				//Make the cards non-clickable again.
				mMainCard.setOnClickListener(null);
				mLeftCard.setOnClickListener(null);
				isAnotherRound();
			}
		});

		mLeftCard.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User thinks vis card is higher.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(2, current);

				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				//did user guess correctly?
				if (current.returnValue()<currentPlayer.card1.returnValue()){ //card is higher. User was correct.
					//TODO if card is the same value (ie. get 2 kings. Make user drink and give drinks)
					mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
				}else{ //the other was higher, user was incorrect
					mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
				}
				//Make the cards non-clickable again.
				mMainCard.setOnClickListener(null);
				mLeftCard.setOnClickListener(null);
				isAnotherRound();
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
			nextButton2();
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


}

