package ca.ajwest.irishpoker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IrishPokerActivity extends Activity {

	public String LOG = "IrishPoker";
	List<Integer> pickedCardList = new ArrayList<Integer>();
	public int imageArr[] = new int[53]; //52 cards plus the back of a card
	public TextView mTextView;
	public Button mBlackButton, mRedButton, mNextButton, mInsideButton, mOutsideButton;
	public ImageButton mFirstPickedCard, mSecondPickedCard, mThirdPickedCard, mFourthPickedCard, mHigherButton1, mHigherButton2, mMainCard, mLeftCard, mRightCard;
	public Card card1, card2, card3, card4;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Set the textview.
        mTextView = (TextView) findViewById(R.id.textView1);
        
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
        
       
        mMainCard = (ImageButton) findViewById(R.id.buttonMainCard);
        mMainCard.setImageResource(R.drawable.back);
        mFirstPickedCard = (ImageButton) findViewById(R.id.imageButton1); 
        mSecondPickedCard = (ImageButton) findViewById(R.id.imageButton2);
        mThirdPickedCard = (ImageButton) findViewById(R.id.imageButton3);
        mFourthPickedCard = (ImageButton) findViewById(R.id.imageButton4);
        mLeftCard = (ImageButton) findViewById(R.id.buttonLeftCard);
        mRightCard = (ImageButton) findViewById(R.id.buttonRightCard);
        
        
        //make the small cards hidden
        mFirstPickedCard.setVisibility(View.GONE);
        mSecondPickedCard.setVisibility(View.GONE);
        mThirdPickedCard.setVisibility(View.GONE);
        mFourthPickedCard.setVisibility(View.GONE);
        mLeftCard.setVisibility(View.GONE);
        mRightCard.setVisibility(View.GONE);
        
        //Set initial message:
    	mTextView.setText("Do you think this card will be BLACK or RED?");

        
        
        //User thinks it's Black.
        mBlackButton = (Button) findViewById(R.id.blackButton);
		mBlackButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				Log.i(LOG, "User thinks card is black.");
				//Generate a card
		        Card current = generateCard();
		        card1 = current;
				
				//flip the card over
		        mMainCard.setImageResource(imageArr[current.cardNum]);
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
		mRedButton = (Button) findViewById(R.id.redButton);
		mRedButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//Generate a card
		        Card current = generateCard();
		        card1 = current;
				
				//flip the card over
		        mMainCard.setImageResource(imageArr[current.cardNum]);
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
    
    private void nextButton1(){
    	mNextButton = (Button) findViewById(R.id.blackButton); 
    	mNextButton.setVisibility(View.VISIBLE);
    	mNextButton.setText("Next");
    	mNextButton.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			mFirstPickedCard.setImageResource(imageArr[card1.cardNum]);
    			mFirstPickedCard.setVisibility(View.VISIBLE);
    			mMainCard.setImageResource(imageArr[0]);
    			mNextButton.setVisibility(View.GONE);
    			round2();
    		}           
		});
    }
    
    private void nextButton2(){
   	mNextButton.setVisibility(View.VISIBLE);
    	mNextButton.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			mSecondPickedCard.setImageResource(imageArr[card2.cardNum]);
    			mSecondPickedCard.setVisibility(View.VISIBLE);
    			mMainCard.setImageResource(imageArr[0]);
    			mNextButton.setVisibility(View.GONE);
    			Log.i(LOG, "Round3");
    			round3();
    		}           
		});
    }
   
    
    private void round2(){ //which card is higher?
    	mTextView.setText("Click the card that you think will be higher.");
    	mHigherButton1 = (ImageButton) findViewById(R.id.buttonMainCard);
    	mHigherButton2 = (ImageButton) findViewById(R.id.imageButton1);
    	
    	
    	mHigherButton1.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			Log.i(LOG, "User thinks invis card is higher.");
				//Generate a card
		        Card current = generateCard();
		        card2 = current;
				
				//flip the card over
		        mMainCard.setImageResource(imageArr[current.cardNum]);
		        //did user guess correctly?
		        if (current.returnValue()>card1.returnValue()){ //card is higher. User was correct.
		        	if ((card2.returnValue())==(card1.returnValue())){ //TODO don't think this works for some reason. Watch out for cards that are the same.
		        		//if card is the same value (ie. get 2 kings. Make user drink and give drinks)
		            	mTextView.setText("They're the same! You AND somebody else must drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
		        	}else{
		        	mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
		        	}
		        }else{ //the other was higher, user was incorrect
		        	mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
		        }
		      //Make the cards non-clickable again.
		    	mHigherButton1.setOnClickListener(null);
		    	mHigherButton2.setOnClickListener(null);
		    	nextButton2();
    		}
    	});
    	
    	mHigherButton2.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			Log.i(LOG, "User thinks vis card is higher.");
				//Generate a card
		        Card current = generateCard();
		        card2 = current;
				
				//flip the card over
		        mMainCard.setImageResource(imageArr[current.cardNum]);
		        //did user guess correctly?
		        if (current.returnValue()<card1.returnValue()){ //card is higher. User was correct.
		        	//TODO if card is the same value (ie. get 2 kings. Make user drink and give drinks)
		        	mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
		        }else{ //the other was higher, user was incorrect
		        	mTextView.setText("You were incorrect. Drink for " + current.returnValue() + " seconds. Click next to continue to ROUND 3!");
		        }
		      //Make the cards non-clickable again.
		    	mHigherButton1.setOnClickListener(null);
		    	mHigherButton2.setOnClickListener(null);
		    	nextButton2();
    		}
    	});
    }
    
    int cardExplain1, cardExplain2;
    private void round3(){ //is the next card going to be inside or outside of the range of the previous two cards?
    	//Order the cards for easiser explain text and calculation below that
    	
    	if (card1.returnValue() < card2.returnValue()){
    		cardExplain1 = card1.returnValue();
    		cardExplain2 = card2.returnValue();
    	}else{
    		cardExplain1 = card2.returnValue();
    		cardExplain2 = card1.returnValue();
    	}
    	
    	mTextView.setText("Do you think the next card will be on the inside, or the outside of the range of these two cards? If you think the card will be in between " + cardExplain1 + " and " + cardExplain2 + " then select INSIDE. If you think it will be less than " + cardExplain1 + " or greater than " + cardExplain2 + " then select OUTSIDE.");
  
    	mInsideButton = (Button) findViewById(R.id.blackButton); 
    	mInsideButton.setVisibility(View.VISIBLE);
    	mInsideButton.setText("INSIDE");
    	mInsideButton.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			Log.i(LOG, "User has selected INSIDE.");
    			Card current = generateCard();
    			card3 = current;
    			if ((current.returnValue()<cardExplain1)|(current.returnValue()>cardExplain2)){
    				Log.i(LOG, "User is incorrect. " + current.returnValue() + " is less than " + cardExplain1 + " or greater than " + cardExplain2 + ".");
    				mTextView.setText("You were incorrect. You must drink for " + current.returnValue() + " seconds.");
    			}else{
    				Log.i(LOG, "User is correct. " + current.returnValue() + " is more than " + cardExplain1 + " and less than " + cardExplain2 + ".");
		        	mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds.");
    			}
    		}           
    	});

    	mOutsideButton = (Button) findViewById(R.id.redButton); 
    	mOutsideButton.setVisibility(View.VISIBLE);
    	mOutsideButton.setText("OUTSIDE");
    	mOutsideButton.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			Log.i(LOG, "User has selected OUTSIDE.");
    			Card current = generateCard();
    			card3 = current;
    			//flip the card over
    			mMainCard.setImageResource(imageArr[current.cardNum]);
    			if ((current.returnValue()<cardExplain1)|(current.returnValue()>cardExplain2)){
    				Log.i(LOG, "User is correct. " + current.returnValue() + " is more than " + cardExplain1 + " or less than " + cardExplain2 + ".");
    				mTextView.setText("You were correct. Make somebody else drink for " + current.returnValue() + " seconds.");
    			}else{
    				Log.i(LOG, "User is incorrect. " + current.returnValue() + " is greater than " + cardExplain1 + " and less than " + cardExplain2 + ".");
    				mTextView.setText("You were incorrect. You must drink for " + current.returnValue() + " seconds.");
    			}
    		}           
		});

    	
    	
    }
    
    //spits out a card and adds it to the pile of discards.
    private Card generateCard() {
    	Log.i(LOG, "Generating card.");

    	int i = 0;

    	if (pickedCardList.size()>=52){
    		Log.e(LOG, "All 52 cards have already been distributed!");
    	}else{
    		do{
    			int min = 1;
    			int max = 4;
    			int ranSuit = min + (int)(Math.random() * ((max - min) + 1));
    			min = 1;
    			max = 13;
    			int ranValue = min + (int)(Math.random() * ((max - min) + 1));
    			Log.i(LOG, "ranSuit= " + ranSuit + " and ranValue= " + ranValue);

    			Card c = new Card(ranSuit, ranValue);
    			if (pickedCardList.contains(c.cardNum)){
    				Log.i(LOG, "Card was already picked! Have to repick. Repicking now.");
    			}else{
    				Log.i(LOG, "Card pick sucessful. (Card wasn't already picked.) Add new card to list.");
    				pickedCardList.add(c.cardNum);
    				Log.i(LOG, "New Card ID is: " + c.cardNum);
    				i = 1;
    				return c;
    			}
    		}while(i==0);
    	}
    	return null;
    }


    
    
}