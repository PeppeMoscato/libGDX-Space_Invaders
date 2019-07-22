package com.mygdx.game.space_invaders;

import java.util.Random;

public class Alien implements Entity{
	float x;
	float y;
	float vel;
	boolean right;
	boolean left;
	Random r;
	int type;

	
	public Alien(int a, int b, int c) {
		this.x = a;
		this.y = b;
		right = true;
		left = false;
		vel = (float) 0.2;
		type = c;
	}
	
	public boolean hit(int bx, int by) {
		return (by >= this.y && by <= this.y + 54) && (bx >= this.x -10 && bx <= this.x + 39);
	}
	

	public boolean move(int level) {
		if(Aliens.direction) {
			if(this.x > 1000) {
				Aliens.direction = false;
				return false;
			} else {
				this.x += vel + ((float)level)/10;
				}
		} 
		if(!Aliens.direction){
			if(this.x < 10) {
				Aliens.direction = true;
				return false;
			} else {
				this.x -= vel + ((float)level)/10;
			}			
		}
		return true;
	}
	
	public void moveD() {
		this.y -= 15; 
		vel += 0.2;
	}
	int getType() {
		return type;
	}
	
	@Override
	public int getX() {
		return (int)this.x;
	}

	@Override
	public int getY() {
		return (int)this.y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
}
