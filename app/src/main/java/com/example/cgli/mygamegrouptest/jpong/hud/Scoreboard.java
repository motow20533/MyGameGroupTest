package com.example.cgli.mygamegrouptest.jpong.hud;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.example.cgli.mygamegrouptest.jpong.pieces.Entity;
import com.example.cgli.mygamegrouptest.jpong.pieces.Wall;
import com.example.cgli.mygamegrouptest.jpong.pieces.Wall.HitListener;

public class Scoreboard extends Entity {
	private int firstPlayer, secondPlayer;
	private Paint letter;

	public Scoreboard(Wall first, Wall second) {
		super(null);

		firstPlayer = secondPlayer = 0;
		letter = new Paint();
		letter.setColor(Color.GRAY);
		letter.setStyle(Style.FILL_AND_STROKE);
		letter.setTextSize(12);

		first.addHitListener(new HitListener() {
			public void notifyHited() {
				secondPlayer++;
			}
		});
		second.addHitListener(new HitListener() {
			public void notifyHited() {
				firstPlayer++;
			}
		});

	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.drawText(firstPlayer + " : " + secondPlayer, 450, 550, letter);
		canvas.restore();
	}

	@Override
	public void processAI() {
		// TODO Auto-generated method stub

	}

}
