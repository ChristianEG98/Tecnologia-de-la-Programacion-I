package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject{
	
	protected static int counter = 0;
	public static final String INFO = "[Obstacle] hits car";
	
	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = "░";
	}

	@Override
	public boolean doCollision() {
		super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.playerDead();
		return false;
	}

	@Override
	public void onEnter() {
		counter++;		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDelete() {
		counter--;
	}
	
	public static int getCounter() {
		return counter;
	}

	public static void reset() {
		counter = 0;		
	}

	@Override
	public boolean receiveShoot() {
		doCollision();
		return true;
	}

	@Override
	public boolean receiveExplosion() {
		return receiveShoot();
	}
	
	@Override
	public boolean receiveThunder() {
		return true;
	}

	@Override
	public void receiveWave() {
		x += 1;
	}

}
