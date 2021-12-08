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
	private int playerForwardsCells = 0;
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
		player.doCollision();
		increaseForwadsCells();
		gameObjectList.update();
		if(level.hasAdvancedObjects()) GameObjectGenerator.generateRuntimeObjects(this);
		gameObjectList.removeDeadObjects();
	}
	
	public void updatePlayer() {
		player.doCollision();
		if(playerStatus()) {
			player.goForward();
			cycles++;
		}
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
	
	public void increaseForwadsCells() {
		playerForwardsCells++;
	}
	
	public int getForwardsCells() {
		return playerForwardsCells;
	}
	
	public void increaseCycles(int c) {
		cycles += c;
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
	
	public Level getLevel() {
		return level;
	}
	
	public int getCollectedCoins() {
		return player.getCollectedCoins();
	}
	
	public void increaseCollectedCoins(int c) {
		player.collectCoins(c);
	}
	
	public void reduceCollectedCoins(int c) {
		player.reduceCoins(c);
	}
	
	public void resetCollectedCoins() {
		player.resetCoins();
	}
	
	public int getPlayerXPosition() {
		return player.getX();
	}
	
	public int getRelativeX(int x) {
		return player.getX() + x;
	}
	
	public int getPlayerYPosition() {
		return player.getY();
	}
	
	public void reset(Level level, Long seed) {
		this.level = level;
		cycles = 0;
		playerForwardsCells = 0;
		random = new Random(seed);
		gameObjectList = new GameObjectContainer();
		gameObjectGenerator = new GameObjectGenerator();
		gameObjectGenerator.reset();
		player = new Player(this, 0, level.getWidth() / 2);
		time = System.currentTimeMillis();
		init();
	}
	
	public void reset() {
		reset(this.level, SEED);
	}
	
	public void cleanLevel() {
		gameObjectList.resetLevel();
	}
	
	private Double getRandomNumber() {
		return random.nextDouble();
	}
	
	public int getRandomColumn() {
		return (int) (getRandomNumber() * getVisibility());
	}
	
	public int getRandomLane() {
		return (int) (getRandomNumber() * getRoadWidth());
	}
	
	public int getVisibility() {
		return level.getVisibility();
	}
	
	public int getLastVisibleColumn() {
		return getPlayerXPosition() + getVisibility() - 1;
	}
	
	public boolean isValidVisiblePosition(int x, int y) {
		if(x > getPlayerXPosition() - getForwardsCells() && x < getVisibility() && getObjectInPosition(x + getForwardsCells(), y) == null) return true;
		else return false;
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
	
	public boolean playerStatus() {
		return player.getIsAlive();
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
		if (exit || winStatus() || !playerStatus()) {
			return true;
		}
		else {
			return false;
		}
	}

	public int numberOfObjectsInPosition(int x, int y) {
		return gameObjectList.objectsCounterInPosition(x, y);
	}
	
	public String positionToString(int x, int y) {
		StringBuilder str = new StringBuilder();
		if(player.isInPosition(x, y)) str.append(player.toString() + " ");
		if(gameObjectList.objectInPosition(getRelativeX(x), y) != null) str.append(gameObjectList.printObjectsInPosition(getRelativeX(x), y));
		if(getRelativeX(x) == getRoadLenght()) str.append("¦");
		
		return str.toString();
	}

	public Collider getObjectInPosition(int x, int y) {
		if(gameObjectList.objectInPosition(x, y) != null) return gameObjectList.objectInPosition(x, y);
		else return null;
	}

	public void clearColumn(int column) {
		for(int i = 0; i < getRoadWidth(); i++) {
			if(gameObjectList.objectInPosition(column, i) != null) {
				gameObjectList.remove(column, i);
			}
		}
	}
	public void forceAddObject(GameObject o) {
		gameObjectList.add(o);
	}
	
	public void execute(InstantAction action) {
		action.execute(this);
	}

}
