package com.example.cgli.mygamegrouptest.bird;

import com.example.cgli.mygamegrouptest.PaintFactory;
import com.example.cgli.mygamegrouptest.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.util.Log;

public class BirdGameView extends SurfaceView implements Callback {

	private static final String TAG = "BirdGameView";

	/**
	 * Be used to set background color, size, ... property
	 */
	private Paint mPaint;

	private SurfaceHolder sfh;

	/**
	 * Draw the bird
	 */
	private Bitmap oriBirdIcon;
	private Bitmap birdIcon;

	/**
	 *	 If you have a thread with a while loop inside, you can control this thread by a boolean flag
	 *	 for the while condition. When you set the flag to false the thread just finishes its task.
	 *	 EDIT: little example,
	 *
	 *	 boolean flag = true;
	 *	 Thread secondary = new Thread(new Runnable() {
	 *
	 *	 	@Override
	 *	 	public void run() {
	 *	    	while (flag) {
	 *	 		// do something
	 *	 		}
	 *	 	}
	 *	 });
	 *
	 *	 secondary.start(); //start the thread
	 *
	 *	 flag = false; // this will force secondary to finish its execution
	 *	 	try {
	 *	 		secondary.join(); // wait for secondary to finish
	 *	 	} catch (InterruptedException e) {
	 *	 		throw new RuntimeException(e);
	 *	 	}
	 */
	private boolean isAvailable = true;
	private int threadLogCount = 0;

	private int gravity = 1;
	private int speed = 0;

	/**
	 * mScore: Used to track the number of gold coin be captured mMoveDelay
	 */
	private int mScore = 0;


	/**
	 * Current mode of application: READY to run, RUNNING, or you have already lost. static final
	 * ints are used instead of an enum for performance reasons.
	 */
	//private int mMode = PAUSE;
	public static final int PAUSE = 0;
	public static final int RUNNING = 1;

	private float birdPosX;
	private float birdPosY;
	private float scorePosX;
	private float scorePosY;

	Thread birdViewThread = null;

	// The text threshold expressed in dip
	private static final float textThresholdDip = 16.0f;

	// Convert the dips to pixels
	final float textScale = getContext().getResources().getDisplayMetrics().density;
	float mTextSize = textThresholdDip * textScale * 2 + 0.5f;

	public BirdGameView(Context context) {
		super(context);
		Log.d(TAG, "BirdGameView be created");
		mPaint = PaintFactory.getPaint(Color.WHITE);
		mPaint.setTextSize(mTextSize);
		sfh = getHolder();
		sfh.addCallback(this);
		setKeepScreenOn(true);
		mScore = 0;
		oriBirdIcon = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
		//birdIcon = getResizedBitmap(oriBirdIcon,80,80);
		birdIcon = Bitmap.createScaledBitmap(oriBirdIcon, 120, 120, false);
		/*Bitmap resizedBitmap = Bitmap.createScaledBitmap(
				originalBitmap, newWidth, newHeight, false);*/
	}


	public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(
				bm, 0, 0, width, height, matrix, false);
		bm.recycle();
		return resizedBitmap;
	}

	public void setDisplayParam(int screenWidth, int screenHeight){
		birdPosX = screenWidth/4;
		birdPosY = 0;
		scorePosX = screenWidth - screenWidth/4;
		scorePosY = screenHeight/8;
	}

	private void drawView() {
		Canvas canvas = null;
		String mScoreText = "Score:" + mScore;
		try {
			canvas = sfh.lockCanvas();
			canvas.drawColor(Color.BLACK);
			canvas.drawBitmap(birdIcon, birdPosX, birdPosY, mPaint);
			canvas.drawText(mScoreText,scorePosX,scorePosY,mPaint);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				sfh.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
			birdViewThread = new Thread(new Runnable() {
				@Override
				public void run() {
					Log.d(TAG, " thread start");
					while (isAvailable) {
						try {
							Thread.sleep(20);
							drawView();
							speed += gravity;
							birdPosY += speed;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					Log.d(TAG, " thread still running"); // This will only be printed our once, which means the
														//  thread will be stopped, because nothing to run any more.
														//  So there is no need to run birdViewThread.join() in surfaceDestroyed
				}
			});
			birdViewThread.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.d(TAG, "surfaceChanged");
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "surfaceDestroyed");
		// in theory, there is no need to do below, but want to have a try
		try {
			birdViewThread.join();
		} catch (InterruptedException e) {
			Log.e(TAG, " exception happened while try to join birdViewThread");
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		speed = -10;
		if (mScore < 20) {
			mScore = mScore + 1;
		} else {
			mScore = 0;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * Updates the current mode of the application (RUNNING or PAUSED or the like) as well as sets
	 * the visibility of textview for notification
	 *
	 * @param newMode New mode of application to be set to
	 */
	public void setMode(int newMode) {
		Log.d(TAG, "setMode to " + newMode);
		isAvailable = (newMode == RUNNING);
	}

}
