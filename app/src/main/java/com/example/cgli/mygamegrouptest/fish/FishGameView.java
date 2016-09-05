package com.example.cgli.mygamegrouptest.fish;

import java.util.ArrayList;
import java.util.Random;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;


import com.example.cgli.mygamegrouptest.BaseView;
import com.example.cgli.mygamegrouptest.PaintFactory;
import com.example.cgli.mygamegrouptest.R;

/**
 * @how to add animate effect for fish?
 * http://www.geeks.gallery/how-to-display-the-animated-gif-image-in-android/
 * http://stackoverflow.com/questions/6533942/adding-gif-image-in-an-imageview-in-android
 * https://developer.android.com/guide/topics/graphics/2d-graphics.html#frame-animation
 * https://developer.android.com/reference/android/graphics/drawable/AnimationDrawable.html
 * @how to control fish come out from both side
 * @how to control the direction of the fish
 *
 *
 * 08-30 20:20:23.480 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw the fish under my control
08-30 20:20:23.480 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw my fish when isLeft is false
08-30 20:20:23.497 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw the fish under my control
08-30 20:20:23.497 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw my fish when isLeft is false
08-30 20:20:23.530 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw the fish under my control
08-30 20:20:23.530 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw my fish when isLeft is false
08-30 20:20:23.547 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw the fish under my control
08-30 20:20:23.547 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw my fish when isLeft is false
08-30 20:20:23.564 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw the fish under my control
08-30 20:20:23.564 10218-10218/com.example.cgli.mygamegrouptest D/FishGameView: draw my fish when isLeft is false
08-30 20:20:23.565 10218-10218/com.example.cgli.mygamegrouptest D/AndroidRuntime: Shutting down VM
08-30 20:20:23.573 10218-10218/com.example.cgli.mygamegrouptest E/AndroidRuntime: FATAL EXCEPTION: main
Process: com.example.cgli.mygamegrouptest, PID: 10218
java.lang.NullPointerException: Attempt to invoke virtual method 'int android.graphics.Bitmap.getWidth()' on a null object reference
at com.example.cgli.mygamegrouptest.fish.Fish.getRect(Fish.java:116)
at com.example.cgli.mygamegrouptest.fish.FishGameView.onDraw(FishGameView.java:113)
at android.view.View.draw(View.java:16184)
at android.view.View.updateDisplayListIfDirty(View.java:15180)
at android.view.ViewGroup.recreateChildDisplayList(ViewGroup.java:3593)
at android.view.ViewGroup.dispatchGetDisplayList(ViewGroup.java:3573)
at android.view.View.updateDisplayListIfDirty(View.java:15140)
at android.view.ViewGroup.recreateChildDisplayList(ViewGroup.java:3593)
at android.view.ViewGroup.dispatchGetDisplayList(ViewGroup.java:3573)
at android.view.View.updateDisplayListIfDirty(View.java:15140)
at android.view.ViewGroup.recreateChildDisplayList(ViewGroup.java:3593)
at android.view.ViewGroup.dispatchGetDisplayList(ViewGroup.java:3573)
at android.view.View.updateDisplayListIfDirty(View.java:15140)
at android.view.ViewGroup.recreateChildDisplayList(ViewGroup.java:3593)
at android.view.ViewGroup.dispatchGetDisplayList(ViewGroup.java:3573)
at android.view.View.updateDisplayListIfDirty(View.java:15140)
at android.view.ThreadedRenderer.updateViewTreeDisplayList(ThreadedRenderer.java:281)
at android.view.ThreadedRenderer.updateRootDisplayList(ThreadedRenderer.java:287)
at android.view.ThreadedRenderer.draw(ThreadedRenderer.java:322)
at android.view.ViewRootImpl.draw(ViewRootImpl.java:2615)
at android.view.ViewRootImpl.performDraw(ViewRootImpl.java:2434)
at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2067)
at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1107)
at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6013)
at android.view.Choreographer$CallbackRecord.run(Choreographer.java:858)
at android.view.Choreographer.doCallbacks(Choreographer.java:670)
at android.view.Choreographer.doFrame(Choreographer.java:606)
at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:844)
at android.os.Handler.handleCallback(Handler.java:739)
at android.os.Handler.dispatchMessage(Handler.java:95)
at android.os.Looper.loop(Looper.java:148)
at android.app.ActivityThread.main(ActivityThread.java:5417)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)

 *
 */
public class FishGameView extends BaseView {
	private String TAG = FishGameView.class.getSimpleName();
	private Bitmap bitmap;
	private Paint rPaint;
	private int bWidth;
	private int bHeight;
	// private float x, y;
	private Fish myFish;
	private FishManager fishManager = FishManager.getInstance();
	public FishGameView(Context context) {
		super(context);
	}
	@Override
	protected void initView() {
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gold_coin);
		bWidth = bitmap.getWidth();
		bHeight = bitmap.getHeight();
		rPaint = PaintFactory.getPaint(Color.RED);
		rPaint.setTextSize(20);
		rPaint.setTextAlign(Align.CENTER);
		mPaint.setTextSize(15);
		mPaint.setTextAlign(Align.CENTER);
		myFish = new Fish((winWidth - bWidth) / 2, (winHeight - bHeight) / 2, bitmap, 10, 1);
                myFish.setAlive(true);
                myFish.setDirection(true);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (myFish.isAlive()) {
						Thread.sleep(20);
						handler.sendEmptyMessage(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1: {
					if ((++count) % 100 == 0) {
						int size = new Random().nextInt(20);
						fishManager.addFish((new Random().nextFloat()) * (winWidth - bWidth), -bHeight, size);
					}
					postInvalidate();
					break;
				}
			}
		};
	};
	int count = 0;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (myFish != null && myFish.isAlive()) {
                        Log.d(TAG, "draw the fish under my control");
			if (!myFish.isLeft()) {
				Log.d(TAG, "draw my fish when isLeft is true");
				Matrix matrix = new Matrix();
				matrix.setRotate(180, myFish.getBitmap().getWidth() / 2, myFish.getBitmap().getHeight() / 2);
				Bitmap mBitmap = Bitmap.createBitmap(myFish.getBitmap(), 0, 0, myFish.getBitmap().getWidth(), myFish.getBitmap().getHeight(), matrix, false);
				canvas.drawBitmap(mBitmap, new Rect(0, 0, bWidth, bHeight), myFish.getRect(), mPaint);
			} else {
                                Log.d(TAG, "draw my fish when isLeft is false");
				canvas.drawBitmap(myFish.getBitmap(), new Rect(0, 0, bWidth, bHeight), myFish.getRect(), mPaint);
			}
			canvas.drawText(myFish.getSize() + "", myFish.getX() + myFish.getWidth() / 2, myFish.getY() + myFish.getHeight() / 2 - (mPaint.ascent() - mPaint.descent()) / 2, rPaint);
		} else if (myFish != null && !myFish.isAlive()) {
			showToast("Be eaten");
			return;
		}
		// draw other fishes
		ArrayList<Fish> fishs = fishManager.getAllFishs();
		synchronized (fishs) {
			for (Fish fish : fishs) {
				if (fish.isAlive()) {
					canvas.drawBitmap(fish.getBitmap(), new Rect(0, 0, bWidth, bHeight), fish.getRect(), mPaint);
					canvas.drawText(fish.getSize() + "", fish.getX() + fish.getWidth() / 2, fish.getY() + fish.getHeight() / 2 - (mPaint.ascent() - mPaint.descent()) / 2, mPaint);
					if (myFish.getRect().intersect(fish.getRect())) {
						myFish.eatFish(fish);
					}
				}
			}
		}
	}
	// move the fish
	private boolean isMoving = false;
	private float dX, dY;
	private float lastY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float xx = event.getX();
		float yy = event.getY();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (myFish.getX() < xx && xx < myFish.getX() + myFish.getWidth() && myFish.getY() < yy && yy < myFish.getY() + myFish.getHeight()) {
					isMoving = true;
					dX = xx - myFish.getX();
					dY = yy - myFish.getY();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if (isMoving) {
					if (event.getY() - lastY > 0) {
						// down
						myFish.setDirection(false);
					} else if (event.getY() - lastY < 0) {
						// up
						myFish.setDirection(true);
					}
					lastY = event.getY();
					myFish.setXY(xx - dX, yy - dY);
				} else {
					return false;
				}
				break;
			case MotionEvent.ACTION_UP:
				isMoving = false;
				break;
		}
		postInvalidate();
		return true;
	}
}
