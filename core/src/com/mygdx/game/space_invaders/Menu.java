package com.mygdx.game.space_invaders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu {
	int selection;
	Texture menu;
	int players;
	Game game;
	int pause;
	int level;
	
	boolean playing;
	boolean quit;
	
	BitmapFont font;
	Texture gameOver;
	
	boolean scores;
	Texture highscores;
	boolean entered;
	ArrayList<Integer> highScores;
	PrintWriter bOut;
	BufferedReader bIn;
	ArrayList<String> input;
	public Menu() {
		input = new ArrayList<String>();
		level = 1;
		selection = 0;
		menu = new Texture("menu.png");
		game = new Game();
		pause = 0;
		playing = false;
		quit = false;
		entered= false;
		font = new BitmapFont();				
		font.setColor(Color.GREEN);
		font.getData().setScale(4);
		gameOver = new Texture("gameover.png");
		scores = false;
		highscores = new Texture("highscores.png");
		highScores = new ArrayList<Integer>();
	
		try {
			bIn = new BufferedReader(new FileReader("o.txt"));
			while(bIn.ready()) {
				String line = bIn.readLine();
				input.add(line);
			}
			bOut = new PrintWriter(new BufferedWriter(new FileWriter("o.txt")), true);
			for(int i = 0; i < input.size(); i++)
				bOut.println(input.get(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(menu, 0, -15);
	}
	
	public void runMenu(SpriteBatch batch, SoundManager soundManager) {
			if(!playing)	
				soundManager.playMenu();
			else
				soundManager.stopMenu();
			if(!playing && !scores && Gdx.input.getY() >= 312 && Gdx.input.getY() <= 339 && Gdx.input.getX() >= 29 && Gdx.input.getX() <= 255) { //play
				if(Gdx.input.isTouched()) {
					playing = true;
					players = 1;
				}
			}
			else if(!playing && !scores &&  Gdx.input.getY() >= 353 && Gdx.input.getY() <= 379 && Gdx.input.getX() >= 29 && Gdx.input.getX() <= 236) { //2P
				if(Gdx.input.isTouched()) {
					playing = true;
					players = 2;
				}
			}
			else if(Gdx.input.getY() >= 369 && Gdx.input.getY() <= 419 && Gdx.input.getX() >= 29 && Gdx.input.getX() <= 170) { //highscores
				if(Gdx.input.isTouched()) {
					scores = true;
				}
			}
			else if(!playing && !scores &&  Gdx.input.getY() >= 642 && Gdx.input.getY() <= 668 && Gdx.input.getX() >= 29 && Gdx.input.getX() <= 130) { //quit
				if(Gdx.input.isTouched()) {
					quit = true;
				}
			}
			 if(!playing && !scores &&  Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
				playing = true;
				players = 1;
			}
			 else if(!playing && !scores && Gdx.input.isKeyPressed(Input.Keys.NUM_1)) { 
				playing = true;
				players = 2;
				}
			 else if(!playing && Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
					scores = true;
			 }
			 else if(!playing && !scores &&  Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
				 quit = true;
			 }
			 else if(scores) {
				 runScores(batch, input);
			 }
			else {
				draw(batch);
			}
		
		if (pause == 0)
		{
			if(playing){
				int run=game.runGame(batch, soundManager, players, level);
				if (run==1){
						level ++;
						game.nextLevel();
						run = 0;
				}
				else if (run==2) {
					pause=1;
					bOut.println(String.valueOf(game.score));
					soundManager.stopBackground();
				}
			}
		}
		else 
		{
			boolean enterpressed=drawStatic(batch);
			if (enterpressed) {
				playing = false;
				pause = 0;
				game = new Game();
			}
				
		}
		if(quit)
			Gdx.app.exit();
	}
	
	public void dispose() {
			game.dispose();
			menu.dispose();
			bOut.close();
			font.dispose();
			gameOver.dispose();
	}

	
	public boolean drawStatic(SpriteBatch batch){
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
			return true;
		batch.draw(gameOver, 0, 0);
		font.draw(batch,String.valueOf(game.score), 390, 545);
		return false;
	}
	
	public void runScores(SpriteBatch batch, ArrayList<String> input) {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			scores = false;
		}
		if(!entered) {
			for(int i = 0; i < input.size(); i++)
				highScores.add(Integer.parseInt(input.get(i)));
			highScores.sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2.compareTo(o1);
				}
			});
			entered = true;
		}
		if(scores) {
			batch.draw(highscores, 0, 0);
			for (int i = 0; i < 5; i++){
				font.draw(batch, String.valueOf(highScores.get(i)), 500, 500-(i*100));	
			}
		}
	}
	
}