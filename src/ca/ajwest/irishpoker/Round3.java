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
		//Set the textview.
        mTextView = (TextView) findViewById(R.id.textView1);
        //set the mainCard.
        mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);
        
        //set the previouscards:
        mLeftCard = (ImageButton) findViewById(R.id.buttonLeftCard);
        mRightCard = (ImageButton) findViewById(R.id.buttonRightCard);
        
		mLeftCard.setImageResource(IrishPokerActivity.imageArr[IrishPokerActivity.card1.cardNum]);
		mRightCard.setImageResource(IrishPokerActivity.imageArr[IrishPokerActivity.card2.cardNum]);
		mLeftCard.setVisibility(View.VISIBLE);
		mRightCard.setVisibility(View.VISIBLE);

        

		//Order the cards for easiser explain text and calculation below that

		if (IrishPokerActivity.card1.returnValue() < IrishPokerActivity.card2.returnValue()){
			cardExplain1 = IrishPokerActivity.card1.returnValue();
			cardExplain2 = IrishPokerActivity.card2.returnValue();
		}else{
			cardExplain1 = IrishPokerActivity.card2.returnValue();
			cardExplain2 = IrishPokerActivity.card1.returnValue();
		}

		mTextView.setText("Do you think the next card will be on the inside, or the outside of the range of these two cards? If you think the card will be in between " + cardExplain1 + " and " + cardExplain2 + " then select INSIDE. If you think it will be less than " + cardExplain1 + " or greater than " + cardExplain2 + " then select OUTSIDE.\nNote: If your card is revealed to be the same value as one of your previous two cards, it is INSIDE their range.");

		mInsideButton = (Button) findViewById(R.id.blackButton); 
		mInsideButton.setVisibility(View.VISIBLE);
		mInsideButton.setText("INSIDE");
		mInsideButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User has selected INSIDE.");
				Card current = GenerateCard.generateCard();
				IrishPokerActivity.card3 = current;
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
				nextButton3();
			}

		});

		mOutsideButton = (Button) findViewById(R.id.redButton); 
		mOutsideButton.setVisibility(View.VISIBLE);
		mOutsideButton.setText("OUTSIDE");
		mOutsideButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User has selected OUTSIDE.");
				Card current = GenerateCard.generateCard();
				IrishPokerActivity.card3 = current;
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
				nextButton3();


			}
		});
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
