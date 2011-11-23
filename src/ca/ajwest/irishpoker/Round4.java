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
	
	private TextView mTextView;
	private ImageButton mMainCard, mTopLeftCard, mTopRightCard, mTopMiddleCard;
	
	
	String LOG = "IrishPokerRound3";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		Log.i(LOG, "Running Round3 Activity");

		//Set the textview.
        mTextView = (TextView) findViewById(R.id.textView1);

		mTextView.setText(null);

		mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);
        mTopLeftCard = (ImageButton) findViewById(R.id.imageButton1);
        mTopMiddleCard = (ImageButton) findViewById(R.id.imageButton2);
        mTopRightCard = (ImageButton) findViewById(R.id.imageButton3);
        

        
		mTopLeftCard.setImageResource(IrishPokerActivity.imageArr[IrishPokerActivity.card1.cardNum]);
		mTopMiddleCard.setImageResource(IrishPokerActivity.imageArr[IrishPokerActivity.card2.cardNum]);
		mTopRightCard.setImageResource(IrishPokerActivity.imageArr[IrishPokerActivity.card3.cardNum]);
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
			}
		});

		mButtonSpade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(LOG, "Selected Spade");
				suitValueSelect(3);
				dialog.dismiss();
			}
		});

		mButtonDiamond.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(LOG, "Selected Diamond");
				suitValueSelect(2);
				dialog.dismiss();
			}
		});

		mButtonClub.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(LOG, "Selected Club");
				suitValueSelect(4); 
				dialog.dismiss();
			}
		});
		dialog.show();

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
