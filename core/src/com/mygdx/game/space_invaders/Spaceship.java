package com.mygdx.game.space_invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Spaceship implements Entity{
	int x;
	int y;
	boolean shooting;
	SpaceshipBullet bullet;

	public Spaceship() {
		x = 490;
		y = 0;
		shooting = false;
	}
	
	 
	public boolean move(int p) {
		if (p==0) {
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if(this.x > 1015)
				return false;
			this.x += 3;
			return true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if(this.x < 0)
				return false;
			this.x -= 3;	
			return true;
		}
		}
		else if (p==1)
		{
			if(Gdx.input.isKeyPressed(Input.Keys.D)) {
				if(this.x > 1015)
					return false;
				this.x += 3;
				return true;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.A)) {
				if(this.x < 0)
					return false;
				this.x -= 3;	
				return true;
			}
		}
		return false;
	}
	
	
	public void fire() {
		if(!this.shooting) {
			bullet = new SpaceshipBullet(x, y);
		}
		bullet.fire(x, y);
		this.shooting = bullet.shooting;
	}
	
	
	
	
	public boolean isShooting() {
		return shooting;
	}

	public SpaceshipBullet getBullet() {
		return bullet;
	}

	public void setBullet(SpaceshipBullet bullet) {
		this.bullet = bullet;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
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

	public boolean hit(int bulletX, int bulletY) {
		return (bulletX >= x-10 && bulletX <= x + 45) && (bulletY >= y && bulletY <= y + 30);
		
	}

	


}