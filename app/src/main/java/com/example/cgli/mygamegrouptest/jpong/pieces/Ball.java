package com.example.cgli.mygamegrouptest.jpong.pieces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.example.cgli.mygamegrouptest.jpong.PiecesManager;
import com.example.cgli.mygamegrouptest.jpong.math.Vector2D;

public class Ball extends Entity {
	private Paint paint;

	private Vector2D pos;
	private Vector2D dir;
	private float speed;

	private PiecesManager pieces;
	
	private Bitmap image;

	public Ball(Vector2D pos, Vector2D dir, float speed) {
		super(new Rect());

		this.pos = pos;
		this.dir = dir;
		this.speed = speed;

		paint = new Paint();
		paint.setColor(Color.RED);
	}
	
	public Ball(Vector2D pos, Vector2D dir, float speed, PiecesManager manager,
			int color,Bitmap image) {
		super(new Rect());

		this.pos = pos;
		this.dir = dir;
		this.speed = speed;

		paint = new Paint();
		paint.setColor(color);

		pieces = manager;
		this.image = image;
	}

	public void setPiecesManager(PiecesManager manager) {
		pieces = manager;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.drawBitmap(image, pos.getX(),pos.getY(), paint);
//		canvas.drawCircle(pos.getX(), pos.getY(), 10, paint);
		canvas.restore();
	}

	private void move() {
		pos.plusMe(dir.multiply(speed));
	}

	@Override
	public void processAI() {
		Rect bounds = new Rect((int) pos.getX() - 5, (int) pos.getY() - 5,
				(int) pos.getX() + 5, (int) pos.getY() + 5);

		for (Entity ent : pieces.getPieces()) {
			if (ent == this)
				continue;
			if (ent instanceof BlockableEntity)
				if (bounds.intersect(ent.getBounds())) {
					Vector2D n = ((BlockableEntity) ent).getNormal();
					// r = v-2 * v.dot( n ) * n
					dir = dir.minus(n.multiply(2).multiply(dir.dot(n)));
					
					if(ent instanceof Wall)
						((Wall)ent).notifyHit();
					break;
				}
		}
		move();
	}
}
