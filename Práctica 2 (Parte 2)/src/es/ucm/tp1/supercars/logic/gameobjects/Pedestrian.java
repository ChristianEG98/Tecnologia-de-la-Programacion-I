package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends Obstacle{

	private static int counter = 0;
	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	private int plus;
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = "☺";
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.resetCoins();
		player.playerDead();
		doCollision();
		return false;
	}

	@Override
	public boolean receiveShoot() {
		game.resetCollectedCoins();
		doCollision();
		return true;
	}

	@Override
	public void onEnter() {
		counter++;
	}

	@Override
	public void onDelete() {
		counter--;
	}

	public static void reset() {
		counter = 0;
	}

	public static int getCounter() {
		return counter;		
	}
	
	@Override
	public void update() {
		if(super.getY() == 0) plus = 1;
		if(super.getY() == game.getRoadWidth() - 1) plus = -1;
		super.y += plus;
	}

}
