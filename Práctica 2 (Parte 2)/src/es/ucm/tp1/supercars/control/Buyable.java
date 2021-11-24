package es.ucm.tp1.supercars.control;

import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {

	public int cost();
	
	public default boolean buy(Game game) {
		if(game.getCollectedCoins() >= cost()) {
			game.reduceCollectedCoins(cost());
			return true;
		}
		else return false;
	}
}
