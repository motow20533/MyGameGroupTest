package com.example.cgli.mygamegrouptest.bird;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;


public class BirdActivity extends Activity {

	private static final String TAG = "BirdActivity";
	private BirdGameView mbirdGameView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Display mDisplay = this.getWindowManager().getDefaultDisplay();
		final int width  = mDisplay.getWidth();
		final int height = mDisplay.getHeight();

		mbirdGameView = new BirdGameView(this);
		mbirdGameView.setDisplayParam(width, height);
		setContentView(mbirdGameView);
		mbirdGameView.setMode(BirdGameView.RUNNING);
		//Log.d(TAG, "after set view, width = " + mbirdGameView.getWidth());

	}

	@Override
	protected void onPause() {
		super.onPause();
		//if we did not setMode to PAUSE here, it will report system error
		//that is because the thread in BirdGameView is still running when Bird activity exit.
		//sfh is null in drawView
		mbirdGameView.setMode(BirdGameView.PAUSE);
	}
}
