package ca.ajwest.irishpoker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class IrishPokerActivity extends Activity {

	public String LOG = "IrishPoker";
	public static int imageArr[] = new int[53]; //52 cards plus the back of a card
	public static TextView mTextView;
	public static Button mNextButton;
	public static int NUMBEROFPLAYERS;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setWindowAnimations(android.R.style.Animation_Toast);
        //setContentView(R.layout.main); //we don't really need to set this view at all.
        
        NUMBEROFPLAYERS = Splashscreen.numPlayers;
        Log.i(LOG, "There are " + NUMBEROFPLAYERS + " players.");
        makePlayers(NUMBEROFPLAYERS);
        
        //Get card resources (their names basically)
        imageArr[0] = R.drawable.back;
        imageArr[1] = R.drawable.hearta;
        imageArr[2] = R.drawable.heart2;
        imageArr[3] = R.drawable.heart3;
        imageArr[4] = R.drawable.heart4;
        imageArr[5] = R.drawable.heart5;
        imageArr[6] = R.drawable.heart6;
        imageArr[7] = R.drawable.heart7;
        imageArr[8] = R.drawable.heart8;
        imageArr[9] = R.drawable.heart9;
        imageArr[10] = R.drawable.heart10;
        imageArr[11] = R.drawable.heartj;
        imageArr[12] = R.drawable.heartq;
        imageArr[13] = R.drawable.heartk;
        imageArr[14] = R.drawable.diamonda;
        imageArr[15] = R.drawable.diamond2;
        imageArr[16] = R.drawable.diamond3;
        imageArr[17] = R.drawable.diamond4;
        imageArr[18] = R.drawable.diamond5;
        imageArr[19] = R.drawable.diamond6;
        imageArr[20] = R.drawable.diamond7;
        imageArr[21] = R.drawable.diamond8;
        imageArr[22] = R.drawable.diamond9;
        imageArr[23] = R.drawable.diamond10;
        imageArr[24] = R.drawable.diamondj;
        imageArr[25] = R.drawable.diamondq;
        imageArr[26] = R.drawable.diamondk;
        imageArr[27] = R.drawable.spadea;
        imageArr[28] = R.drawable.spade2;
        imageArr[29] = R.drawable.spade3;
        imageArr[30] = R.drawable.spade4;
        imageArr[31] = R.drawable.spade5;
        imageArr[32] = R.drawable.spade6;
        imageArr[33] = R.drawable.spade7;
        imageArr[34] = R.drawable.spade8;
        imageArr[35] = R.drawable.spade9;
        imageArr[36] = R.drawable.spade10;
        imageArr[37] = R.drawable.spadej;
        imageArr[38] = R.drawable.spadeq;
        imageArr[39] = R.drawable.spadek;
        imageArr[40] = R.drawable.cluba;
        imageArr[41] = R.drawable.club2;
        imageArr[42] = R.drawable.club3;
        imageArr[43] = R.drawable.club4;
        imageArr[44] = R.drawable.club5;
        imageArr[45] = R.drawable.club6;
        imageArr[46] = R.drawable.club7;
        imageArr[47] = R.drawable.club8;
        imageArr[48] = R.drawable.club9;
        imageArr[49] = R.drawable.club10;
        imageArr[50] = R.drawable.clubj;
        imageArr[51] = R.drawable.clubq;
        imageArr[52] = R.drawable.clubk;
        //later, use: image.setImage(imageArr[n]);
        
        
        runGame();
        

    }
    
    private void runGame() {
    	
    		//Run the first round activity:
            Intent round1Intent = new Intent(IrishPokerActivity.this, Round1.class);
            Log.i(LOG, "Requesting to start Round1 Activity");
            IrishPokerActivity.this.startActivity(round1Intent);
    //      round1Intent.putExtra("KeyName", PlayerNumber);
            //startActivityForResult(round1Intent, SECONDARY_ACTIVITY_REQUEST_CODE);
           IrishPokerActivity.this.finish();
    		
    	}
        
	
 

	public static Player player1, player2, player3, player4, player5, player6, player7, player8, player9, player10;
    //Makes the players. (Max 10 players!)
    private void makePlayers(int NUMBEROFPLAYERS) {
    	for (int i=0; i==NUMBEROFPLAYERS; i++){
    		switch (i){
    		case 1:
    			this.player1 = new Player();
    			this.player1.playerNumSet(1);
    			break;
    		case 2:
    			this.player2 = new Player();
    			this.player2.playerNumSet(2);
    			break;
    		case 3:
    			this.player3 = new Player();
    			this.player3.playerNumSet(3);
    			break;
    		case 4:
    			this.player4 = new Player();
    			this.player4.playerNumSet(4);
    			break;
    		case 5:
    			this.player5 = new Player();
    			this.player5.playerNumSet(5);
    			break;
    		case 6:
    			this.player6 = new Player();
    			this.player6.playerNumSet(6);
    			break;
    		case 7:
    			this.player7 = new Player();
    			this.player7.playerNumSet(7);
    			break;
    		case 8:
    			this.player8 = new Player();
    			this.player8.playerNumSet(8);
    			break;
    		case 9:
    			this.player9 = new Player();
    			this.player9.playerNumSet(9);
    			break;
    		case 10:
    			this.player10 = new Player();
    			this.player10.playerNumSet(10);
    			break;
    		default:
    			Log.e(LOG, "For some reason, a whole number between 1-10 wasn't selected for players.");
    			this.player1 = new Player();
    			this.player1.playerNumSet(1);
    		}
    	}
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
			default:
				return super.onOptionsItemSelected(item);
			}
		}


		private void previousCardsSelected() {

			/* Create an Intent that will start the Activity. */
			Intent previousCardsIntent = new Intent(IrishPokerActivity.this, PreviousCards.class);
			IrishPokerActivity.this.startActivity(previousCardsIntent);
		}
		
    
}