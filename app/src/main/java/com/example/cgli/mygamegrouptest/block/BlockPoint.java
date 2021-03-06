package com.example.cgli.mygamegrouptest.block;
public class BlockPoint {
	private int x;
	private int y;
	public BlockPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void moveLeft() {
		x--;
	}

	public void moveRight() {
		x++;
	}

	public void moveDown() {
		y++;
	}
}
