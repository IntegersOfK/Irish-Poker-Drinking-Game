package ca.ajwest.irishpoker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Options extends Activity {

	private String LOG = "IrishPokerOptions";
	private RadioButton rb1, rb2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.options);
		Log.i(LOG, "Running Options");

		/**
		RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.RadioGroupFaceCardValue);
		RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.RadioGroupDrinkAllocation);
		RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.RadioGroupTimer);

		int radioButtonSelected1 = 1;
		int radioButtonSelected2 = 1;
		int radioButtonSelected3 = 1;
		 **/
		
		final RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
		final RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
		final RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
		final RadioButton radio4 = (RadioButton) findViewById(R.id.radio4);
		final RadioButton radio5 = (RadioButton) findViewById(R.id.radio5);
		final RadioButton radio6 = (RadioButton) findViewById(R.id.radio6);

		radio1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "RadioButton1 Selected");
				Splashscreen.option1 = 1;
			}
		});

		radio2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "RadioButton2 Selected");
				Splashscreen.option1 = 2;
			}
		});
		radio3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "RadioButton3 Selected");
				Splashscreen.option2 = 1;
			}
		});
		radio4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "RadioButton4 Selected");
				Splashscreen.option2 = 2;
			}
		});
		radio5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "RadioButton5 Selected");
				Splashscreen.option3 = 1;
			}
		});
		radio6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(LOG, "RadioButton6 Selected");
				Splashscreen.option3 = 2;
			}
		});
	}
}