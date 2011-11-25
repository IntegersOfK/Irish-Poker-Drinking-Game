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

public class Round3 extends Activity {  //is the next card going to be inside or outside of the range of the previous two cards?

	private Player currentPlayer;
	private int cardExplain1, cardExplain2;
	String LOG = "IrishPokerRound3";
	private Button mInsideButton, mOutsideButton;
	private TextView mTextView;
	private ImageButton mMainCard, mLeftCard, mRightCard;
	private int numOfPlayers;
	int currentLoop = 0;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round3 Activity");

		numOfPlayers = IrishPokerActivity.NUMBEROFPLAYERS;
		theRound();
	}

	private void theRound(){
		this.currentLoop++;


		//Set the textview.
        mTextView = (TextView) findViewById(R.id.textView1);
        //set the mainCard.
        mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);
        
        //set the previouscards:
        mLeftCard = (ImageButton) findViewById(R.id.buttonLeftCard);
        mRightCard = (ImageButton) findViewById(R.id.buttonRightCard);
        
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
		mRightCard.setImageResource(IrishPokerActivity.imageArr[currentPlayer.card2.cardNum]);
		mLeftCard.setVisibility(View.VISIBLE);
		mRightCard.setVisibility(View.VISIBLE);

        

		//Order the cards for easiser explain text and calculation below that

		if (currentPlayer.card1.returnValue() < currentPlayer.card2.returnValue()){
			cardExplain1 = currentPlayer.card1.returnValue();
			cardExplain2 = currentPlayer.card2.returnValue();
		}else{
			cardExplain1 = currentPlayer.card2.returnValue();
			cardExplain2 = currentPlayer.card1.returnValue();
		}

		mTextView.setText("Do you think the next card will be on the inside, or the outside of the range of these two cards? If you think the card will be in between " + cardExplain1 + " and " + cardExplain2 + " then select INSIDE. If you think it will be less than " + cardExplain1 + " or greater than " + cardExplain2 + " then select OUTSIDE.\nNote: If your card is revealed to be the same value as one of your previous two cards, it is INSIDE their range.");

		mInsideButton = (Button) findViewById(R.id.blackButton); 
		mInsideButton.setVisibility(View.VISIBLE);
		mInsideButton.setText("INSIDE");
		mInsideButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User has selected INSIDE.");
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(3, current);
				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				if ((current.returnValue()<cardExplain1)|(current.returnValue()>cardExplain2)){
					Log.i(LOG, "User is incorrect. " + current.returnValue() + " is less than " + cardExplain1 + " or greater than " + cardExplain2 + ".");
					mTextView.setText("You were incorrect. You must drink for " + current.returnValue() + " seconds.");
				}else{
					Log.i(LOG, "User is correct. " + current.returnValue() + " is more than " + cardExplain1 + " and less than " + cardExplain2 + ".");
					mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds.");
				}
				//hide the buttons
				mOutsideButton.setVisibility(View.GONE);
				mInsideButton.setVisibility(View.GONE);
				isAnotherRound();
			}

		});

		mOutsideButton = (Button) findViewById(R.id.redButton); 
		mOutsideButton.setVisibility(View.VISIBLE);
		mOutsideButton.setText("OUTSIDE");
		mOutsideButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User has selected OUTSIDE.");
				Card current = GenerateCard.generateCard();
				currentPlayer.setCard(3, current);
				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				if ((current.returnValue()<cardExplain1)|(current.returnValue()>cardExplain2)){
					Log.i(LOG, "User is correct. " + current.returnValue() + " is more than " + cardExplain1 + " or less than " + cardExplain2 + ".");
					mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds.");
				}else{
					Log.i(LOG, "User is incorrect. " + current.returnValue() + " is greater than " + cardExplain1 + " and less than " + cardExplain2 + ".");
					mTextView.setText("You were incorrect. You must drink for " + current.returnValue() + " seconds.");
				}

				//hide the buttons
				mOutsideButton.setVisibility(View.GONE);
				mInsideButton.setVisibility(View.GONE);
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
			nextButton3();
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



	public Button mNextButton;
	private void nextButton3(){
		mNextButton = (Button) findViewById(R.id.blackButton); 
		mNextButton.setVisibility(View.VISIBLE);
		mNextButton.setText("Next");
		mNextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Round4");

				 //Run the fourth round activity:
		        Intent round4Intent = new Intent(Round3.this, Round4.class);
				Round3.this.startActivity(round4Intent);
				Round3.this.finish();

			}           
		});
	}


}
