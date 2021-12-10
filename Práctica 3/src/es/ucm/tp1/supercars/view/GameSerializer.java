package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.logic.Collider;
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
				else{
					if(game.numberOfObjects(i, j) > 0) {
						Collider c = game.getObjectInPosition(i, j);
						if(c != null) {
							str.append(c.toString() + " (" + i + ", " + j + ")");
							if(c.status() != "") str.append(" " + c.status() + "\n");
							else str.append("\n");
						}
					}
					if(game.numberOfObjects(i, j) > 1) {
						Collider c = game.getSecondObjectInPosition(i, j);
						if(c != null) {
							str.append(c.toString() + " (" + i + ", " + j + ")");
							if(c.status() != "") str.append(" " + c.status() + "\n");
							else str.append("\n");
						}
					}
				}
			}
		}
		return str.toString();
	}
}
