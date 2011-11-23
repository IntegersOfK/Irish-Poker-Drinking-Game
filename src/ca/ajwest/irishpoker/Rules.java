package ca.ajwest.irishpoker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Rules extends Activity{


	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.rules);
		
        Button mOKButton = (Button) findViewById(R.id.OKButton);


        mOKButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		Rules.this.finish(); //end the rules activity, returning user back to splashscreen (or wherever they were).
        	}           
        });

	}

}
