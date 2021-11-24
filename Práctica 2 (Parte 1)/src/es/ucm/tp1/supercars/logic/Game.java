package es.ucm.tp1.supercars.logic;

import java.util.Random;
import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.Player;

public class Game {

	private Level level;
	private final long SEED;
	private Random random;
	private GameObjectContainer gameObjectList;
	private GameObjectGenerator gameObjectGenerator;
	private Player player;
	private boolean test;
	private int cycles = 0;
	private long time;
	private boolean exit;
	private String message;
	
	public Game(long seed, Level level) {
		time = System.currentTimeMillis();
		this.SEED = seed;
		random = new Random(SEED);
		this.level = level;
		player = new Player(this, 0, level.getWidth() / 2);
		test = false;
		gameObjectList = new GameObjectContainer();
		gameObjectGenerator = new GameObjectGenerator();
		init();		
	}
	
	public void init() {
		gameObjectGenerator.generateGameObjects(this, level);
	}
	
	public void update() {
		cycles++;
		player.goForward();
		player.doCollision();
		gameObjectList.removeDeadObjects();
	}

	
	protected void tryToAddObject(GameObject object, double frequency) {
		double random = getRandomNumber();
		
		if(random <= frequency && freeSpace(object.getX(), object.getY())) {
			gameObjectList.add(object);;
		}
	}

	private boolean freeSpace(int x, int y) {
		if(gameObjectList.objectInPosition(x, y) != null) return false;
		return true;
	}
	
	public void moveUp() {
		if(player.getY() > 0) player.goUp();
	}
	
	public void moveDown() {
		if(player.getY() < getRoadWidth() - 1) player.goDown();
	}
	
	public void toggleTest() {
		test = !test;
	}
	
	public boolean getTestMode() {
		return test;
	}
	
	public int getCycles() {
		return cycles;
	}
	
	public int getCoinCounter() {
		return gameObjectGenerator.getCoinCounter();
	}
	
	public int getObstacleCounter() {
		return gameObjectGenerator.getObstacleCounter();
	}
	
	public long getFirstTime() {
		return time;
	}
	
	public int getCollectedCoins() {
		return player.getCollectedCoins();
	}
	
	public void reset(Level level, Long seed) {
		this.level = level;
		cycles = 0;
		test = false;
		random = new Random(seed);
		gameObjectList = new GameObjectContainer();
		gameObjectGenerator = new GameObjectGenerator();
		gameObjectGenerator.reset();
		player = new Player(this, 0, level.getWidth() / 2);
		time = System.currentTimeMillis();
		init();
	}
	
	public void reset() {
		cycles = 0;
		test = false;
		random = new Random(SEED);
		gameObjectList = new GameObjectContainer();
		gameObjectGenerator = new GameObjectGenerator();
		gameObjectGenerator.reset();
		player = new Player(this, 0, level.getWidth() / 2);
		time = System.currentTimeMillis();
		init();
	}
	
	private Double getRandomNumber() {
		return random.nextDouble();
	}
	
	public int getRandomLane() {
		return (int) (getRandomNumber() * getRoadWidth());
	}
	
	public int getVisibility() {
		return level.getVisibility();
	}
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	
	public int getRoadLenght() {
		return level.getLenght();
	}
	
	public boolean exitStatus() {
		return exit;
	}
	
	public boolean collisionStatus() {
		return player.getCollision();
	}
	
	public boolean winStatus() {
		return ((getRoadLenght() - getCycles()) < 0);
	}

	public void exit() {
		exit = true;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isFinished() {
		if (exit) {
			message = "Player leaves the game";
			return true;
		}
		else if (winStatus()) {
			message = "Player wins!\n";
			return true;
		}
		else if (collisionStatus()) {
			message = "Player crashed!\n";
			return true;
		}
		else {
			return false;
		}
	}

	public String positionToString(int j, int i) {
		if(player.getX() - player.getX() == j && player.getY() == i) return player.toString();
		else if (j + player.getX() == getRoadLenght()) return "¦";
		else if (gameObjectList.objectInPosition(j + player.getX(), i) != null) return gameObjectList.objectInPosition(j + player.getX(), i).toString();
		else return "";
	}

	public Collider getObjectInPosition(int x, int y) {
		if(gameObjectList.objectInPosition(x, y) != null) return gameObjectList.objectInPosition(x, y);
		else return null;
	}

}
