package com.mygdx.game.space_invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	Sound invaderKilled;
	Sound shipExplosion;
	Sound shoot;
	
	Music background;
	Music menu;
	
	public SoundManager() {
		invaderKilled = Gdx.audio.newSound(Gdx.files.internal("invaderkilled.ogg"));
		shipExplosion = Gdx.audio.newSound(Gdx.files.internal("shipexplosion.ogg"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("shoot.ogg"));
		background = Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic.mp3"));
		menu = Gdx.audio.newMusic(Gdx.files.internal("menumusic.mp3"));
	}
	
	public void playBackground() {
		if(!background.isPlaying())
			background.play();
	}
	
	public void stopBackground() {
		if(background.isPlaying())
			background.stop();
	}
	
	public void playMenu() {
		if(!menu.isPlaying())
			menu.play();
	}
	
	public void stopMenu() {
		if(menu.isPlaying())
			menu.stop();
	}
	
	public void playInvaderKilled() {
		invaderKilled.play(0.3f);
	}
	
	public void playShipExplosion() {
		shipExplosion.play(0.3f);
	}
	
	public void playShoot() {
		shoot.play(0.3f);
	}
	
	public void dispose() {
		invaderKilled.dispose();
		shipExplosion.dispose();
		shoot.dispose();
		background.dispose();
	}
}
