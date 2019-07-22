package com.mygdx.game.space_invaders;

public interface Entity {
	public boolean hit(int x, int y);
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
}
