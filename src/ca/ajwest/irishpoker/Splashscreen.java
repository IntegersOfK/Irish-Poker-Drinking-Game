package ca.ajwest.irishpoker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Splashscreen extends Activity {

	String LOG = "IrishPokerSplash";
	Button mRulesButton, mUpButton, mDownButton, mOptionsButton, mStartButton, tempButton;
	private TextView mPlayerNum;
	public static int numPlayers = 1;
	public static int option1, option2, option3; //The three options to be set.

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splashscreen);
		Log.i(LOG, "Running Splashscreen");

		//Set the textview.
		mPlayerNum = (TextView) findViewById(R.id.textView2);

		mRulesButton = (Button) findViewById(R.id.button1);
		mUpButton = (Button) findViewById(R.id.button2);
		mDownButton = (Button) findViewById(R.id.button3);
		mOptionsButton = (Button) findViewById(R.id.button4);
		mStartButton = (Button) findViewById(R.id.button5);
		tempButton = (Button) findViewById(R.id.tempButton);

		updatePlayerNumText(numPlayers);
		
		
		mRulesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Rules Selected");
				
				/* Create an Intent that will start the Activity. */
				Intent rulesIntent = new Intent(Splashscreen.this, Rules.class);
				Splashscreen.this.startActivity(rulesIntent);
				
			}	
		});


		mUpButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Up Selected");
				if (numPlayers!=10){ //max players is 10
					numPlayers++;
					updatePlayerNumText(numPlayers);
				}
			}           
		});

		mDownButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Down Selected");
				if (numPlayers!=1){ //min players is 1
					numPlayers--;
					updatePlayerNumText(numPlayers);
				}
			}           
		});

		mOptionsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Options Selected");
				
				/* Create an Intent that will start the Activity. */
				Intent optionsIntent = new Intent(Splashscreen.this, Options.class);
				Splashscreen.this.startActivity(optionsIntent);
				
			}           
		});

		mStartButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Start Selected");

				/* Create an Intent that will start the Activity. */
				Intent mainIntent = new Intent(Splashscreen.this, IrishPokerActivity.class);
				Splashscreen.this.startActivity(mainIntent);
				Splashscreen.this.finish();

			}           
		});
		
		tempButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.i(LOG, "Temp Intent Selected");

				/* Create an Intent that will start the Activity. */
				Intent tempIntent = new Intent(Splashscreen.this, CountDown.class);
				Splashscreen.this.startActivity(tempIntent);

			}           
		});
		
		
	}
	
	private void updatePlayerNumText(int num){
		mPlayerNum.setText("Players: " + num);
	}
	
	
	   @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.options_menu, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle item selection
			switch (item.getItemId()) {
			case R.id.previous_cards:
				previousCardsSelected();
				return true;
			case R.id.view_rules:
				viewRulesSelected();
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}
		private void previousCardsSelected() {

			/* Create an Intent that will start the Activity. */
			Intent previousCardsIntent = new Intent(Splashscreen.this, PreviousCards.class);
			Splashscreen.this.startActivity(previousCardsIntent);
		}
		
		private void viewRulesSelected(){
			//Start the Rules activity.
			// Create an Intent that will start the Activity.
			
			Intent viewRulesIntent = new Intent(Splashscreen.this, Rules.class);
			Splashscreen.this.startActivity(viewRulesIntent);
			
		}
	
}
