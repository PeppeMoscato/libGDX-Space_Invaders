package com.mygdx.game.space_invaders;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {
	ArrayList<Spaceship> spaceships;
	Texture img;
	Texture[] lives;
	Aliens aliens;
	int score;
	Bunkers bunkers;
	int outcome;
	GraphicManager graphicManager;
	ArrayList<ExplosionCounter> destructions;
	int lifes;
	
	public Game() {
		spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.add(new Spaceship());
		//la classe ha sempre 2 spaceships, poi in base alla variabile players nel runGame viene gestito solo il
		//primo o anche il secondo
		
		img = new Texture("Ship1.png");
		lives = new Texture[3];
		aliens = new Aliens();
		bunkers = new Bunkers();
		graphicManager = new GraphicManager(aliens);
		destructions= new ArrayList<ExplosionCounter> ();
		
		lifes = 3;
		score = 0;
		outcome = 0;
	}
	
	public int runGame(SpriteBatch batch, SoundManager soundManager, int players, int level) {
			soundManager.playBackground();
			graphicManager.drawBackground(batch);
			graphicManager.drawAliens(aliens, batch);
			graphicManager.drawScore(score, batch);
			graphicManager.drawABullets(aliens.getBullets(), batch);
			graphicManager.drawBunkers(bunkers, batch);
			graphicManager.drawCurrentLevel(batch, level);
			for (int p = 0; p < players; p++){
				graphicManager.drawSpaceship(spaceships.get(p), batch, p);
				spaceships.get(p).move(p);
				if(spaceships.get(p).isShooting())
					graphicManager.drawSBullet(spaceships.get(p).getBullet(), batch);
				
				if(spaceships.get(p).shooting)
					spaceships.get(p).fire();
				
				if(p == 0 && Gdx.input.isKeyPressed(Input.Keys.SPACE) && !spaceships.get(p).shooting) {
					spaceships.get(p).fire();
					soundManager.playShoot();
				}
				
				if(p == 1 && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && !spaceships.get(p).shooting) {
					spaceships.get(p).fire();
					soundManager.playShoot();
				}
			}
			
			
			for (int p = 0; p < players; p++) {
				
			for(int i = 0; i < aliens.size(); i++) {
				if(spaceships.get(p).shooting && aliens.get(i).hit(spaceships.get(p).bullet.x, spaceships.get(p).bullet.y)) {
					switch (aliens.get(i).getType()){
					case 1:
						score+=50;
						break;
					case 2:
						score+=25;
						break;
					case 3:
						score+=10;
						break;
					}
					destructions.add(new ExplosionCounter(aliens.get(i).getX(),aliens.get(i).getY(),aliens.get(i).getType()));
					spaceships.get(p).shooting = false;
					aliens.remove(i);
					i++;
					soundManager.playInvaderKilled();
					if(i >= aliens.size())
						break; 
				}
				if (aliens.get(i).y < 50){
					soundManager.playShipExplosion();
					outcome=2;
					break;
				}
			}
			for(int i = 0; i < aliens.bullets.size(); i++)
				if(spaceships.get(p).hit(aliens.bullets.get(i).getX(), aliens.bullets.get(i).getY())) {
					soundManager.playShipExplosion();
					spaceships.remove(spaceships.get(p));
					spaceships.add(new Spaceship());
					lifes--;
					aliens.bullets.remove(i);
					if (lifes==0)
						outcome=2;
					break;
				}
			}
			
			for (int i = 0; i < destructions.size(); i++)	{
				if (!graphicManager.drawExplosion(destructions.get(i), batch)){
					destructions.remove(i);
					i++;
				}
			}
			for (int p = 0; p < players; p++)
				bunkers.handleCollisions(spaceships.get(p), aliens);
			graphicManager.drawLifes(lifes, batch);
			aliens.move(level);
			aliens.fire(level);
			if(aliens.empty()) {
				outcome = 1;
			}
		return outcome;
	}
	

	
	public void dispose() {
		img.dispose();
		graphicManager.dispose();
	}
	
	public void nextLevel() {
		spaceships.clear();
		spaceships.add(new Spaceship());
		spaceships.add(new Spaceship());
		if (lifes <= 4)
			lifes++;
		aliens = new Aliens();
		outcome = 0;
	}
}