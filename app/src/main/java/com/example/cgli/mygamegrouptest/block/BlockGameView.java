package com.example.cgli.mygamegrouptest.block;

import java.util.ArrayList;
import java.util.Random;

import com.example.cgli.mygamegrouptest.PaintFactory;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class BlockGameView extends View {

	private String TAG = BlockGameView.class.getSimpleName();

	private static boolean reStartGame;

	Random rand = new Random();

	private static int mBlocktype = 0;

		/* Display screen's width and height*/
	private int winWidth;
	private int winHeight;

	/* block size*/
	private int blockSize;
	
	private Paint mPaint;
	private Paint bPaint;


	private static final int numOfColumn = 20;
	private static final int numOfLine = 20;
	private static int newBlockPosX = numOfColumn/2;
	
	public static int[][] map = new int[numOfColumn][numOfLine];

	private static int mScore;


	public BlockGameView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		winWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
		winHeight = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
		blockSize = winWidth / numOfColumn;
		//Log.d(TAG,"windows width = " + winWidth + " , height = " + winHeight + " , block size = " + blockSize);

		mPaint = PaintFactory.getPaint(Color.BLACK);
		bPaint = PaintFactory.getPaint(Color.BLACK);
		bPaint.setStyle(Style.FILL);

		Block.setBottomBorder(numOfLine);
		Block.setRightBorder(numOfColumn);
		reStartGame = false;
		mScore = 100;
		mBlocktype = rand.nextInt(15) + 1;
		Block.generateBlock(mBlocktype, newBlockPosX, 0);
		initMap();

		// create a thread to refresh the block movement every 0.1 second
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!reStartGame) {
					try {
						Thread.sleep(800);
						boolean res = Block.moveDown();
						if (!res) {
							Log.d(TAG, "Fail to move down, first set current block pos on map");
							setBlock();
							Log.d(TAG, "Then create new block");
							mBlocktype = rand.nextInt(15) + 1;
							Block.generateBlock(mBlocktype, newBlockPosX, 0);
						}
						postInvalidate();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void turn() {
		boolean res = Block.turn(mBlocktype);
		if (!res) {
			Log.d(TAG,"can not turn");
			setBlock();
		} else {
			mBlocktype = Block.getBlockType();
		}
	}

	public void moveLeft() {
		boolean res = Block.moveLeft();
	}

	public void moveRight() {
		boolean res = Block.moveRight();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int i;
		int j;
		// draw all lines
		//Log.d(TAG,"draw all lines");
		for (i = 0; i < (numOfLine+1); i++) {
			canvas.drawLine(0, blockSize * i, winWidth, blockSize * i, mPaint);
		}
		// draw all columns
		//Log.d(TAG,"draw all columns");
		for (i = 0; i < (numOfColumn+1); i++) {
			canvas.drawLine(blockSize * i, 0, blockSize * i, blockSize*numOfColumn, mPaint);
		}


		// draw all existed blocks
		//Log.d(TAG, "draw all existing blocks");
		for (i = 0; i < numOfColumn; i++) {
			for (j = 0; j < numOfLine; j++) {
				if (map[i][j] == 1) {
					canvas.drawRect(blockSize * i, blockSize * j, blockSize * (i + 1), blockSize * (j + 1), bPaint);
				}
			}
		}

		// draw current blocks
		//Log.d(TAG,"draw current moving blocks");
		for (BlockPoint blockPoint : Block.getBlockPoints()) {
			canvas.drawRect(blockSize * blockPoint.getX(), blockSize * blockPoint.getY(), blockSize * (blockPoint.getX() + 1), blockSize * (blockPoint.getY() + 1), bPaint);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(winWidth, winWidth);
	}

	public void setBlock() {
		int i,j,sumOfLine;
		ArrayList<BlockPoint> blockPoints = Block.getBlockPoints();
		for (BlockPoint blockPoint : blockPoints) {
			//Log.d(TAG, "set map x = " + blockPoint.getX() + " , y = " + blockPoint.getY() + " to 1");
			map[blockPoint.getX()][blockPoint.getY()] = 1;

			if (blockPoint.getY() == 0) {
				Log.d(TAG,"get to top of map, need to restart game");
				reStartGame = true;
				return;
			}
		}

				/*to remove all "1" line*/
		i = numOfLine - 1;
		while (i > 0) {
			//Log.d(TAG,"check whether all 1 in line: " + i);
			sumOfLine = 0;
			for (j = 0; j < numOfColumn; j++) {
				sumOfLine = sumOfLine + map[j][i];
			}

			//Log.d(TAG,"sumOfLine result is: " + sumOfLine);
			if (sumOfLine == numOfColumn){
				//Log.d(TAG, "line " +  i + " are all 1, so remove it");
				for (j = i; j > 1; j--) {
                    for (int k=0; k<numOfColumn;k++) {
						map[k][j] = map[k][j - 1];
					}
				}

				//Log.d(TAG, "create a new line at top (set top line to all 0) ");
				for (j = 0; j < numOfColumn; j++) {
					map[j][0] = 0;
				}

				mScore = mScore + 100;
			}
			i = i - 1;
		}
	}

	public static void initMap() {
		for (int i = 0; i < numOfColumn; i++) {
		    for (int j = 0; j < numOfLine; j++) {
				map[i][j] = 0;
			}
		}
	}

	public static void stopThread() {
		reStartGame = true;
	}

    public static int getFinalScore() {
		return mScore;
	}
}
