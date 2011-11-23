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

	String LOG = "IrishPokerRound2";
	private TextView mTextView;
	private ImageButton mMainCard, mLeftCard;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round2 Activity");

		
		mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);
		mLeftCard = (ImageButton) findViewById(R.id.buttonLeftCard);
		
		//Set the textview.
        mTextView = (TextView) findViewById(R.id.textView1);
        mTextView.setText("Click the card that you think will be higher.");
        
        mLeftCard.setImageResource(IrishPokerActivity.imageArr[IrishPokerActivity.card1.cardNum]);
        mLeftCard.setVisibility(View.VISIBLE);
        
        
		mMainCard.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User thinks invis card is higher.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				IrishPokerActivity.card2 = current;

				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				//did user guess correctly?
				if (current.returnValue()>IrishPokerActivity.card1.returnValue()){ //card is higher. User was correct.
					if ((IrishPokerActivity.card2.returnValue())==(IrishPokerActivity.card1.returnValue())){ //TODO don't think this works for some reason. Watch out for cards that are the same.
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
				nextButton2();
			}
		});

		mLeftCard.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "User thinks vis card is higher.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				IrishPokerActivity.card2 = current;

				//flip the card over
				mMainCard.setImageResource(IrishPokerActivity.imageArr[current.cardNum]);
				//did user guess correctly?
				if (current.returnValue()<IrishPokerActivity.card1.returnValue()){ //card is higher. User was correct.
					//TODO if card is the same value (ie. get 2 kings. Make user drink and give drinks)
					mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
				}else{ //the other was higher, user was incorrect
					mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
				}
				//Make the cards non-clickable again.
				mMainCard.setOnClickListener(null);
				mLeftCard.setOnClickListener(null);
				nextButton2();
			}
		});
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
				//TODO Start round 3 activity
				
				 //Run the third round activity:
		        Intent round3Intent = new Intent(Round2.this, Round3.class);
				Round2.this.startActivity(round3Intent);
				Round2.this.finish();
				
			}           
		});
	}


}

