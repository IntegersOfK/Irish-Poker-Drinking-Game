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



public class Round1 extends Activity {

	String LOG = "IrishPokerRound1";
	Button mBlackButton, mRedButton;
	ImageButton mMainCard;
	private TextView mTextView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		getWindow().setWindowAnimations(android.R.style.Animation_Toast);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round1 Activity");

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

		//User thinks it's Black.
		mBlackButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				Log.i(LOG, "User thinks card is black.");
				//Generate a card
				Card current = GenerateCard.generateCard();
				IrishPokerActivity.card1 = current;

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
				nextButton1();//enables the next button
			}           
		});

		//User thinks it's Red.
		mRedButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//Generate a card
				Card current = GenerateCard.generateCard();
				IrishPokerActivity.card1 = current;

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
				nextButton1(); //enables the first next button

			}           
		});
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
				
			}           
		});

	}
}
