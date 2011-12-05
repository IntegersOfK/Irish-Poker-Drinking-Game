package ca.ajwest.irishpoker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;


	public class PreviousCards extends Activity{

		public static int smallImageArr[] = new int[53]; //52 cards plus the back of a card
		private String LOG = "PreviousCardsActivity";
		
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.previouscards);
			Log.i(LOG, "Previous Cards Requested and running.");
			Log.i(LOG, "Generating small cards array.");
			
			
	        
	        Log.i(LOG, "Starting loop.");
	       for (int i=0; i<GenerateCard.pickedCardList.size(); i++){
	    	   Log.i(LOG, "PickedCardList.size() == " + GenerateCard.pickedCardList.size());
	    	   makeCard(GenerateCard.pickedCardList.get(i));
	       }
		}

		ImageView mImageViewPrevCards;
		private void makeCard(int cardIndex){ //I don't know how to refer to a resource ID with a variable, so I made this long switch statement.
			Log.i(LOG, "Figuring out which ID to use.");
			Log.i(LOG, "cardIndex passed was: " + cardIndex);
			
			switch (cardIndex){

		case 1:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView1);
			mImageViewPrevCards.setImageResource(R.drawable.hearta_small);
			break;
		case 2:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView2);
			mImageViewPrevCards.setImageResource(R.drawable.heart2_small);
			break;
		case 3:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView3);
			mImageViewPrevCards.setImageResource(R.drawable.heart3_small);
			break;
		case 4:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView4);
			;mImageViewPrevCards.setImageResource(R.drawable.heart4_small);
			break;
		case 5:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView5);
			mImageViewPrevCards.setImageResource(R.drawable.heart5_small);
			break;
		case 6:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView6);
			mImageViewPrevCards.setImageResource(R.drawable.heart6_small);
			break;
		case 7:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView7);
			mImageViewPrevCards.setImageResource(R.drawable.heart7_small);
			break;
		case 8:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView8);
			mImageViewPrevCards.setImageResource(R.drawable.heart8_small);
			break;
		case 9:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView9);
			mImageViewPrevCards.setImageResource(R.drawable.heart9_small);
			break;
		case 10:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView10);
			mImageViewPrevCards.setImageResource(R.drawable.heart10_small);
			break;
		case 11:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView11);
			mImageViewPrevCards.setImageResource(R.drawable.heartj_small);
			break;
		case 12:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView12);
			mImageViewPrevCards.setImageResource(R.drawable.heartq_small);
			break;
		case 13:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView13);
			mImageViewPrevCards.setImageResource(R.drawable.heartk_small);
			break;
		case 14:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView14);
			mImageViewPrevCards.setImageResource(R.drawable.diamonda_small);
			break;
		case 15:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView15);
			mImageViewPrevCards.setImageResource(R.drawable.diamond2_small);
			break;
		case 16:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView16);
			mImageViewPrevCards.setImageResource(R.drawable.diamond3_small);
			break;
		case 17:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView17);
			mImageViewPrevCards.setImageResource(R.drawable.diamond4_small);
			break;
		case 18:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView18);
			mImageViewPrevCards.setImageResource(R.drawable.diamond5_small);
			break;
		case 19:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView19);
			mImageViewPrevCards.setImageResource(R.drawable.diamond6_small);
			break;
		case 20:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView20);
			mImageViewPrevCards.setImageResource(R.drawable.diamond7_small);
			break;
		case 21:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView21);
			mImageViewPrevCards.setImageResource(R.drawable.diamond8_small);
			break;
		case 22:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView22);
			mImageViewPrevCards.setImageResource(R.drawable.diamond9_small);
			break;
		case 23:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView23);
			mImageViewPrevCards.setImageResource(R.drawable.diamond10_small);
			break;
		case 24:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView24);
			mImageViewPrevCards.setImageResource(R.drawable.diamondj_small);
			break;
		case 25:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView25);
			mImageViewPrevCards.setImageResource(R.drawable.diamondq_small);
			break;
		case 26:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView26);
			mImageViewPrevCards.setImageResource(R.drawable.diamondk_small);
			break;
		case 27:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView27);
			
			mImageViewPrevCards.setImageResource(R.drawable.spadea_small);
			break;
		case 28:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView28);
			mImageViewPrevCards.setImageResource(R.drawable.spade2_small);
			break;
		case 29:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView29);
			mImageViewPrevCards.setImageResource(R.drawable.spade3_small);
			break;
		case 30:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView30);
			mImageViewPrevCards.setImageResource(R.drawable.spade4_small);
			break;
		case 31:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView31);
			mImageViewPrevCards.setImageResource(R.drawable.spade5_small);
			break;
		case 32:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView32);
			mImageViewPrevCards.setImageResource(R.drawable.spade6_small);
			break;
		case 33:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView33);
			mImageViewPrevCards.setImageResource(R.drawable.spade7_small);
			break;
		case 34:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView34);
			mImageViewPrevCards.setImageResource(R.drawable.spade8_small);
			break;
		case 35:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView35);
			mImageViewPrevCards.setImageResource(R.drawable.spade9_small);
			break;
		case 36:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView36);
			mImageViewPrevCards.setImageResource(R.drawable.spade10_small);
			break;
		case 37:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView37);
			mImageViewPrevCards.setImageResource(R.drawable.spadej_small);
			break;
		case 38:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView38);
			mImageViewPrevCards.setImageResource(R.drawable.spadeq_small);
			break;
		case 39:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView39);
			mImageViewPrevCards.setImageResource(R.drawable.spadek_small);
			break;
		case 40:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView40);
			mImageViewPrevCards.setImageResource(R.drawable.cluba_small);
			break;
		case 41:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView41);
			mImageViewPrevCards.setImageResource(R.drawable.club2_small);
			break;
		case 42:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView42);
			mImageViewPrevCards.setImageResource(R.drawable.club3_small);
			break;
		case 43:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView43);
			mImageViewPrevCards.setImageResource(R.drawable.club4_small);
			break;
		case 44:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView44);
			mImageViewPrevCards.setImageResource(R.drawable.club5_small);
			break;
		case 45:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView45);
			mImageViewPrevCards.setImageResource(R.drawable.club6_small);
			break;
		case 46:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView46);
			mImageViewPrevCards.setImageResource(R.drawable.club7_small);
			break;
		case 47:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView47);
			mImageViewPrevCards.setImageResource(R.drawable.club8_small);
			break;
		case 48:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView48);
			mImageViewPrevCards.setImageResource(R.drawable.club9_small);
			break;
		case 49:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView49);
			mImageViewPrevCards.setImageResource(R.drawable.club10_small);
			break;
		case 50:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView50);
			
			mImageViewPrevCards.setImageResource(R.drawable.clubj_small);
			break;
		case 51:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView51);
			
			mImageViewPrevCards.setImageResource(R.drawable.clubq_small);
			break;
		case 52:
			mImageViewPrevCards = (ImageView) findViewById(R.id.imageView52);
			mImageViewPrevCards.setImageResource(R.drawable.clubk_small);	
			break;

			}
		}
		
		//TODO make menus work. Since we don't really know which round started this though, the "Reset Game" button needs some kind of workaround.
	 
		
		
	}

