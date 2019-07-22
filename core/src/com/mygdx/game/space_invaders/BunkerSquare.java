package com.mygdx.game.space_invaders;



public class BunkerSquare{
	int x;
	int y;
	int state;
	public BunkerSquare(int x, int y){
		this.x = x;
		this.y = y;
		state = 0;
	}

	public int getState(){
		return state;
	}
	
	public void nextState()	{
		state += 1;
		
	}
	public boolean colpito(int bx, int by) {
		if ((by >= this.y && by<=this.y+10) && (bx >= this.x-15 && bx <= this.x + 3)){
			return true;
		}
		return false;
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
