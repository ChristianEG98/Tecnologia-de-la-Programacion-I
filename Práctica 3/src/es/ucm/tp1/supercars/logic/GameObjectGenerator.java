package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;
import es.ucm.tp1.supercars.logic.instantActions.ThunderAction;

public class GameObjectGenerator {

	public static final int MIN_ID = 1;
	public static final int MAX_ID = 5;
	
	public void generateGameObjects(Game game, Level level) {
		for (int x = game.getVisibility() / 2; x < game.getRoadLenght(); x++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.getCoinFrequency());
			
			if(level.hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				if(!SuperCoin.hasSuperCoin()) {
					game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				}
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), level.getAdvancedObjectFrequency());
			}
		}
	}

	public void reset() {
		Coin.reset();
		Obstacle.reset();
		Wall.reset();
		SuperCoin.reset();
		Turbo.reset();
		Pedestrian.reset();
		Truck.reset();
		Grenade.reset();
	}

	public int getCoinCounter() {
		return Coin.getCounter();
	}
	
	public int getObstacleCounter() {
		return Obstacle.getCounter() + Wall.getCounter() + Pedestrian.getCounter() + Truck.getCounter();
	}
	
	public static void generateRuntimeObjects(Game game) {
		if(game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction());
		}
	}
	
	public static void forceAdvanceObject(Game game, int id, int x) {
		GameObject o = null;
		switch(id) {
		case 1:
			o = new Wall(game, x, game.getRandomLane());
			break;
		case 2:
			o = new Turbo(game, x, game.getRandomLane());
			break;
		case 3:
			o = new SuperCoin(game, x, game.getRandomLane());
			break;
		case 4:
			o = new Truck(game, x, game.getRandomLane());
			break;
		case 5:
			o = new Pedestrian(game, x, 0);
			break;
		}
		game.forceAddObject(o);
	}
	
	public static void generateGrenade(Game game, int x, int y) {
		game.tryToAddObject(new Grenade(game, x, y), 1);
	}
	
}
