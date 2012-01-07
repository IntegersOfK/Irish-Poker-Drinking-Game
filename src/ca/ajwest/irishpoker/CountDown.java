package ca.ajwest.irishpoker;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

public class CountDown extends Activity{

	private TextView mTextField;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.countdown);
		Log.i("IrishPokerCountDownActivity", "Running Countdown Activity");

		initSounds();
		mTextField = (TextView) findViewById(R.id.textView1);

		new CountDownTimer(10000, 1000) {

			public void onTick(long millisUntilFinished) {
				mTextField.setText("Drink time remaining: " + millisUntilFinished / 1000);
			}

			public void onFinish() {
				mTextField.setText("done!");
				playSound(1);
			}
		}.start();

	}
	

    public static final int CHIME = 1;
    private SoundPool soundPool;
	private HashMap<Integer, Integer> soundPoolMap;
	
	private void initSounds() {
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		soundPoolMap.put(CHIME, soundPool.load(getBaseContext(), R.raw.chime, 1));
	}
	
	public void playSound(int sound) {
		AudioManager mgr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
		int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, 0, 1f);
	}

	
}
