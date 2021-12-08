package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends Obstacle{

	private static int counter = 0;
	public static final String INFO = "[WALL] hard obstacle";
	private static final String[] SYMBOLS = new String[] {"░", "▒", "█"};
	private int resistance = 3;
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = getSymbol();
	}
	
	protected String getSymbol() {
		return SYMBOLS[resistance - 1];
	}

	@Override
	public boolean doCollision() {
		resistance--;
		if(resistance == 0) super.alive = false;
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

	public static void reset() {
		counter = 0;
	}

	public static int getCounter() {
		return counter;		
	}
	
	@Override
	public void onDelete() {
		counter--;
		game.increaseCollectedCoins(5);
	}
	
	@Override
	public boolean receiveThunder() {
		super.alive = false;
		return true;
	}

}
