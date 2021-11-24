package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;

public class GameObjectGenerator {

	public void generateGameObjects(Game game, Level level) {
		for (int x = game.getVisibility() / 2; x < game.getRoadLenght(); x++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.getCoinFrequency());
		}
	}

	public void reset() {
		Coin.reset();
		Obstacle.reset();
	}

	public int getCoinCounter() {
		return Coin.getCounter();
	}
	
	public int getObstacleCounter() {
		return Obstacle.getCounter();
	}
	
	public static void generateRuntimeObjects(Game game) {
		// TODO add your code
	}
	
}
