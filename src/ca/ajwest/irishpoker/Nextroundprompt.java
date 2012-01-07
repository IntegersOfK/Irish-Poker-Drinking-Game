package ca.ajwest.irishpoker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Nextroundprompt extends Activity{

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.nextroundprompt);

		Bundle extras = getIntent().getExtras();
		//check to see if "myKey" is in the bundle, if so then assign it's value
		// to mIntentString  if not, assign "nothing passed in" to mIntentString...
		int mIntentInt = extras != null ? extras.getInt("playerNumKey") : -1; //so now mIntentInt is the player number.

	//	String mIntentString = extras != null ? extras.getString("displayTextKey") : null; //Text to be displayed has been set as mIntentString.


		//Make a string to display.
		String displayText = "Player " + mIntentInt + "'s turn.";

		TextView mDisplayText = (TextView) findViewById(R.id.nextRoundTextView1);
		Button mContinueButton = (Button) findViewById(R.id.continueButton1);

//		if (mIntentString.equals(null)){
			//don't change the string.

//		}else{
			
//			displayText = mIntentString;
			
//		}
		
		mDisplayText.setText(displayText);
		
			mContinueButton.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Nextroundprompt.this.finish(); //end the rules activity, returning user back to splashscreen (or wherever they were).
				}           
			});
			
			


	}

}
