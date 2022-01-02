package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.logic.Game;

public class GameSerializer {

	private Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}
	
	public String serializerInfo() {
		StringBuilder str = new StringBuilder();
		str.append("---- ROAD FIGHTER SERIALIZED ----\n");
		str.append("Level: " + game.getLevel() + "\n");
		str.append("Cycles: " + game.getForwardsCells() + "\n");
		str.append("Coins: " + game.getCollectedCoins() + "\n");
		if(!game.getTestMode()) str.append("EllapsedTime: " + game.getEllapedTime() + "\n");
		str.append("Game Objects: \n");
		for(int i = 0; i < game.getRoadLenght(); i++) {
			for(int j = 0; j < game.getRoadWidth(); j++) {
				if(i == game.getPlayerXPosition() && j == game.getPlayerYPosition()) str.append(game.getPlayerSymbol() + " (" + i + ", " + j + ")\n");
				else str.append(game.getSerializeInfo(i, j));
			}
		}
		return str.toString();
	}
}
