package com.mygdx.game.space_invaders;

import java.util.ArrayList;


public class Bunkers{
	ArrayList<BunkerSquare> bunkers;
	
	public Bunkers() {
		bunkers = new ArrayList<BunkerSquare>();
        for(int i = 0; i < 1000; i += 200) {
            bunkers.add(new BunkerSquare(70 + i, 85));
    		bunkers.add(new BunkerSquare(70 + i, 100));
    		bunkers.add(new BunkerSquare(70 + i, 115));
    		bunkers.add(new BunkerSquare(70 + i, 130));
    		bunkers.add(new BunkerSquare(70 + i, 145));

    		bunkers.add(new BunkerSquare(85 + i, 85));
    		bunkers.add(new BunkerSquare(85 + i, 100));
    		bunkers.add(new BunkerSquare(85 + i, 115));
    		bunkers.add(new BunkerSquare(85 + i, 130));
    		bunkers.add(new BunkerSquare(85 + i, 145));
    		
    		bunkers.add(new BunkerSquare(85 + i,  160));
    		bunkers.add(new BunkerSquare(100 + i, 160));
    		bunkers.add(new BunkerSquare(115 + i, 160));
    		bunkers.add(new BunkerSquare(130 + i, 160));
    		bunkers.add(new BunkerSquare(145 + i, 160));
     
    		bunkers.add(new BunkerSquare(100 + i, 175));
    		bunkers.add(new BunkerSquare(115 + i, 175));
    		bunkers.add(new BunkerSquare(130 + i, 175));
     
    		bunkers.add(new BunkerSquare(115 + i, 190));
     
    		bunkers.add(new BunkerSquare(145 + i, 145));
    		bunkers.add(new BunkerSquare(145 + i, 130));
    		bunkers.add(new BunkerSquare(145 + i, 115));
    		bunkers.add(new BunkerSquare(145 + i, 100));
    		bunkers.add(new BunkerSquare(145 + i, 85));
     
    		bunkers.add(new BunkerSquare(160 + i, 145));
    		bunkers.add(new BunkerSquare(160 + i, 130));
    		bunkers.add(new BunkerSquare(160 + i, 115));
    		bunkers.add(new BunkerSquare(160 + i, 85));
    		bunkers.add(new BunkerSquare(160 + i, 100));
        }
		
	}
	
	public void handleCollisions(Spaceship spaceship, Aliens aliens) {
		for (int c = 0; c < bunkers.size(); c++){
			if (spaceship.shooting && bunkers.get(c).colpito(spaceship.bullet.x,spaceship.bullet.y)){
				bunkers.get(c).nextState();
				spaceship.shooting = false;
			}
			for(int i = 0; i < aliens.getBullets().size(); i++) {
				if (bunkers.get(c).colpito(aliens.getBullets().get(i).x, aliens.getBullets().get(i).y)){
					bunkers.get(c).nextState();
					aliens.getBullets().remove(aliens.getBullets().get(i));
					i++;
				}
			}
				if (bunkers.get(c).getState() >= 4){
					bunkers.remove(bunkers.get(c));
					c++;  	
					break;
				}
         		for (int i = 0; i < aliens.size(); i++){
				if (bunkers.get(c).colpito(aliens.get(i).getX(), aliens.get(i).getY())){
					bunkers.remove(bunkers.get(c));
					c++;
					break;
				}
					
			}
		}
		
	}
	
	public int size() {
		return bunkers.size();
	}
	
	public BunkerSquare get(int i) {
		return bunkers.get(i);
	}
	
	public int getState(BunkerSquare b) {
		return b.getState();
	}

	
}
