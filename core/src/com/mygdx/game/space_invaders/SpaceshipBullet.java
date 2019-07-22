package com.mygdx.game.space_invaders;


public class SpaceshipBullet implements Bullet{
	boolean shooting;
	int vel;
	int x;
	int y;
	
	public SpaceshipBullet(int x, int y) {
		vel = 17;
		shooting = false;
		this.x = x + 16;
		this.y = y+35;
		
	}
	
	@Override
	public void fire(int x, int y) {
		if(this.y > 720) {
			shooting = false;
			return;
		}
		this.shooting = true;
		this.y += 15;
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
	
}