package com.mygdx.game.space_invaders;

import java.util.ArrayList;
import java.util.Random;


public class Aliens {
	ArrayList<Alien> aliens;
	static boolean direction; //true va a DX, false fa a SX
	Random random;
	ArrayList<AlienBullet> bullets;
	
	public Aliens() {
		aliens = new ArrayList<Alien>();
		direction = true; 
		random = new Random();
		bullets = new ArrayList<AlienBullet>();
		for(int i=630;i>400;i-=60)
			for(int j = 10; j < 800; j += 90) {
				switch (i){
				case 630:
					aliens.add(new Alien(j, i, 1));
					break;
				case 570:
					aliens.add(new Alien(j, i, 2));
					break;
				default:
					aliens.add(new Alien(j, i, 3));
					break;
				}
			}
		
	}
	
	public ArrayList<AlienBullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<AlienBullet> bullets) {
		this.bullets = bullets;
	}

	public void add(Alien alien) {
		aliens.add(alien);
	}
	
	public int size() {
		return aliens.size();
	}
	
	public Alien get(int i) {
		return aliens.get(i);
	}
	
	public void remove(int i) {
		aliens.remove(i);
	}
	
	public void move(int level) {
		for(int i = 0; i < aliens.size(); i++) {
			if(!aliens.get(i).move(level))
				for(int j = 0 ; j < aliens.size(); j++)
					aliens.get(j).moveD();
		}
		moveBullets();
	}
	
	private void moveBullets(){
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).y < 10) {
				bullets.remove(bullets.get(i));
				i++;
				if(i >= bullets.size())
					return;
			}
		bullets.get(i).fire((int) bullets.get(i).getX(), bullets.get(i).getY());
		}
	}
	

	public boolean empty() {
		return aliens.isEmpty();
	}
	
	public void fire(int level) {
		for(Alien a: aliens) {
			if(random.nextInt(1050 - (level*50)) == 5) {
				bullets.add(new AlienBullet((int)a.x, (int)a.y));
			}
		}
	}
	
	
	
	
}
