package com.mygdx.game.space_invaders;

public class ExplosionCounter {
	int X;
	int Y;
	int cont;
	int type;
	public ExplosionCounter(int a, int b, int c){
		X = a;
		Y = b;
		type = c;
		cont = 13;
	}
	public int getX() {return X;}
	public int getY() {return Y;}
	public int getCont() {return cont;}
	public boolean next() {
		if (cont == 0)
			return false;
		else {
			cont--;
			return true;
		}
	}
	public int getType() {return type;}
}
