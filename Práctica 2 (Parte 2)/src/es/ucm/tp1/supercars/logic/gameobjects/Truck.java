package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends Obstacle{

	private static int counter = 0;
	public static final String INFO = "[TRUCK] moves towards the player";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = "←";
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
		super.x--;
	}

}
