package com.mygdx.game.space_invaders;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Menu menu;
	SoundManager soundManager;
	boolean entrato;
	int choice;
	

	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		menu = new Menu();
		soundManager = new SoundManager();
		entrato = false;
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		menu.runMenu(batch, soundManager);
		if(menu.quit) {
			menu = new Menu();
		}
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		menu.dispose();
		soundManager.dispose();
	}
	
}