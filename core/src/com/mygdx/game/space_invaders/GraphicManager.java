package com.mygdx.game.space_invaders;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicManager {
	Texture background;
	
	Texture alienA_a;
	Texture alienA_b;
	Texture alienB_a;
	Texture alienB_b;
	Texture alienC_a;
	Texture alienC_b;
	
	Texture explA;
	Texture explB;
	Texture explC;
	
	Texture spaceship;
	Texture spaceshipB;
	
	Texture spaceBullet;
	Texture alienBullet;
	Texture level;
	Texture[] bunkers;
	
	Texture lifespng;
	Texture lifestext;
	BitmapFont font;
	Texture score;
	int aliencount;
	
	
	GraphicManager(Aliens a){
		background = new Texture("background.jpg");
		alienA_a = new Texture("alien1_1.png");
		alienA_b= new Texture("alien1_2.png");
		alienB_a= new Texture("alien2_1.png");
		alienB_b= new Texture("alien2_2.png");
		alienC_a= new Texture("alien3_1.png");
		alienC_b= new Texture("alien3_2.png");
		explA= new Texture ("alien1destr.png");
		explB= new Texture ("alien2destr.png");
		explC= new Texture ("alien3destr.png");
		spaceship = new Texture("Ship1.png");
		spaceshipB=new Texture ("Ship2.png");
		lifespng=new Texture("lifes.png");
		lifestext=new Texture("lifestext.png");
		spaceBullet = new Texture("spaceshipshot.png");
		alienBullet = new Texture("alienshot.png");
		bunkers = new Texture [4];
		bunkers[0] = new Texture("block0.png");
		bunkers[1] = new Texture ("block1.png");
		bunkers[2] = new Texture ("block2.png");
		bunkers[3] =new Texture ("block3.png");
		score = new Texture("score.png");
		aliencount=0;
		font= new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2);
		level=new Texture("level.png");
	}
	
	public void drawBackground(SpriteBatch batch) {
		batch.draw(background, 0, 0);
	}
	
	public void drawAliens(Aliens a, SpriteBatch batch) {
		for(int i = 0; i < a.size(); i++)
			if (aliencount>=20){
				switch(a.get(i).getType()){
				case 1:
					batch.draw(alienA_a, a.get(i).getX(), a.get(i).getY());
					break;
				case 2:
					batch.draw(alienB_a, a.get(i).getX(), a.get(i).getY());
					break;
				case 3:
					batch.draw(alienC_a, a.get(i).getX(), a.get(i).getY());
					break;
				}
			}
			else{
				switch(a.get(i).getType()){
				case 1:
					batch.draw(alienA_b, a.get(i).getX(), a.get(i).getY());
					break;
				case 2:
					batch.draw(alienB_b, a.get(i).getX(), a.get(i).getY());
					break;
				case 3:
					batch.draw(alienC_b, a.get(i).getX(), a.get(i).getY());
					break;
				}
			}
		aliencount++;
		if (aliencount>=40)
			aliencount=0;
	}
	
	public boolean drawExplosion(ExplosionCounter E, SpriteBatch batch){
		if (E.next()==false){
			return false;
		}
		switch (E.getType()){
		case 1:
			batch.draw(explA, E.getX(), E.getY());
			break;
		case 2:
			batch.draw(explB, E.getX(), E.getY());
			break;
		default:
			batch.draw(explC, E.getX(), E.getY());
			break;
		}
		return true;
	}
	public void drawSpaceship(Spaceship s, SpriteBatch batch, int p) {
		if (p==0)
			batch.draw(spaceship, s.getX(), s.getY());
		else
			batch.draw(spaceshipB, s.getX(), s.getY());
	}
	
	public void drawSBullet(SpaceshipBullet b, SpriteBatch batch) {
		batch.draw(spaceBullet, b.getX(), b.getY());
	}
	
	public void drawABullets(ArrayList<AlienBullet> a, SpriteBatch batch) {
		for(int i = 0; i < a.size(); i++)
			batch.draw(alienBullet, a.get(i).getX(), a.get(i).getY());
	}
	
	public void drawBunkers(Bunkers b, SpriteBatch batch) {
		for(int i = 0; i < b.size(); i++) {
			batch.draw(bunkers[b.get(i).getState()], b.get(i).getX(), b.get(i).getY());			
		}
	}
	public void drawLifes(int lifes, SpriteBatch batch)	{
		batch.draw(lifestext,940,35);
		for (int i=0;i<lifes;i++)
			batch.draw(lifespng,930+(i*30),10);
	}
	
	public void drawScore(int s, SpriteBatch batch) {
		batch.draw(score, 0, 670);
		font.draw(batch, String.valueOf(s), 200, 695);
	}
	public void drawCurrentLevel(SpriteBatch batch,int l) {
		batch.draw(level, 890, 663);
		font.draw(batch, String.valueOf(l), 1000, 695);
	}
	
	public void dispose() {
		background.dispose();
		level.dispose();
		background.dispose();
		alienA_a.dispose();
		alienA_b.dispose();
		alienB_a.dispose();
		alienB_b.dispose();
		alienC_a.dispose();
		alienC_b.dispose();
		
		explA.dispose();
		explB.dispose();
		explC.dispose();
		
		spaceship.dispose();
		
		spaceBullet.dispose();
		alienBullet.dispose();
		for(int i = 0; i < 4; i++)
			bunkers[i].dispose();
		
		lifespng.dispose();
		lifestext.dispose();
		score.dispose();
		
	}
}
