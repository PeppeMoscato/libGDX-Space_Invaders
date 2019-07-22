package com.mygdx.game.space_invaders;


// SI CANCELLA SE UCCIDI L'ALIENO


public class AlienBullet implements Bullet{
	int x;
	int y;
	

	public AlienBullet(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void fire(int x, int y) {
		if(this.y < 0)
			return;
		this.y -= 4;
		
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
