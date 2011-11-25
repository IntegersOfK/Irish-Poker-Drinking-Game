package ca.ajwest.irishpoker;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Round4 extends Activity {

	private Player currentPlayer;
	private TextView mTextView;
	private ImageButton mMainCard, mTopLeftCard, mTopRightCard, mTopMiddleCard;
	private int numOfPlayers;
	int currentLoop = 0;



	String LOG = "IrishPokerRound3";
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

		mTextView.setText(null);

		mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);
		mTopLeftCard = (ImageButton) findViewById(R.id.imageButton1);
		mTopMiddleCard = (ImageButton) findViewById(R.id.imageButton2);
		mTopRightCard = (ImageButton) findViewById(R.id.imageButton3);


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
		

		mTopLeftCard.setImageResource(IrishPokerActivity.imageArr[currentPlayer.card1.cardNum]);
		mTopMiddleCard.setImageResource(IrishPokerActivity.imageArr[currentPlayer.card2.cardNum]);
		mTopRightCard.setImageResource(IrishPokerActivity.imageArr[currentPlayer.card3.cardNum]);
		mTopLeftCard.setVisibility(View.VISIBLE);
		mTopRightCard.setVisibility(View.VISIBLE);
		mTopMiddleCard.setVisibility(View.VISIBLE);



		//set up dialog
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.suitselectalert);
		dialog.setTitle("Which suit do you think the card will be?");
		dialog.setCancelable(false);
		Button mButtonHeart = (Button) dialog.findViewById(R.id.buttonHeart);
		Button mButtonSpade = (Button) dialog.findViewById(R.id.buttonSpade);
		Button mButtonDiamond = (Button) dialog.findViewById(R.id.buttonDiamond);
		Button mButtonClub = (Button) dialog.findViewById(R.id.buttonClub);

		mButtonHeart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(LOG, "Selected Heart");
				suitValueSelect(1);
				dialog.dismiss();
				isAnotherRound();
			}
		});

		mButtonSpade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(LOG, "Selected Spade");
				suitValueSelect(3);
				dialog.dismiss();
				isAnotherRound();
			}
		});

		mButtonDiamond.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(LOG, "Selected Diamond");
				suitValueSelect(2);
				dialog.dismiss();
				isAnotherRound();
			}
		});

		mButtonClub.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(LOG, "Selected Club");
				suitValueSelect(4); 
				dialog.dismiss();
				isAnotherRound();
			}
		});
		dialog.show();

	}
	
	
	private void isAnotherRound(){
		//first we should save the currentplayer:
		saveCurrentPlayer();
		
		Log.i(LOG, "About to check if numOfPlayers<currentLoop " + numOfPlayers + " - " + currentLoop );
		if (numOfPlayers > currentLoop){
			theRound();
		}else{
			Log.i(LOG, "Done looping. Enabling Next Button");
			Log.i(LOG, "Game over! YAY!");
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
		IrishPokerActivity.card4 = current;
		//flip the card over
		mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
		//did user guess correctly?

		if (suitValueSelected == current.returnSuit()){
			Log.i(LOG, "User guessed correctly.");
			mTextView.setText("You were correct! Make somebody else drink for " + current.returnValue() + " seconds.");
		}else{
			Log.i(LOG, "User guessed incorrectly");
			mTextView.setText("You were incorrect! Drink for " + current.returnValue() + " seconds.");
		}
	}



}
