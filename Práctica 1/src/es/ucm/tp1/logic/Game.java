package es.ucm.tp1.logic;

import java.util.Random;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.objects.*;

public class Game {

	private Level level;
	private long seed;
	private Random random;
	private CoinList coinList;
	private ObstacleList obstacleList;
	private Player player;
	private boolean test;
	private int cycles = 0;
	private long time;
	
	public Game(long seed, Level level) {
		this.level = level;
		this.seed = seed;
		time = System.currentTimeMillis();
		random = new Random(seed);
		coinList = new CoinList(this);
		obstacleList = new ObstacleList(this);
		player = new Player(this, 0, level.getWidth() / 2);
		test = false;

		init();
	}
	
	private void init() {
		for(int x = getVisibility() / 2; x < getRoadLenght(); x++) {
			tryToAddObstacle(new Obstacle(this, x, getRandomLane()), level.getObstacleFrequency());
			tryToAddCoin(new Coin(this, x, getRandomLane()), level.getCoinFrequency());
		}
	
	}
	
	public void update() {
		cycles++;
		player.goForward();
		checkCollition();
	}
	
	private void checkCollition() {
		if(coinList.coinInPosition(player.getX(), player.getY())) {
			player.collectCoin();
			coinList.remove(player.getX(), player.getY());
		}
		
		if(obstacleList.obstacleInPosition(player.getX(), player.getY())){
			player.setCollision(true);
		}
	}
	
	private void tryToAddObstacle(Obstacle obstacle, double frequency) {
        double random = getRandomNumber();

        if(random <= frequency && freeSpace(obstacle.getX(), obstacle.getY())) {
            obstacleList.add(obstacle);
        }
    }

    private void tryToAddCoin(Coin coin, double frequency) {
        double random = getRandomNumber();

        if(random <= frequency && freeSpace(coin.getX(), coin.getY())) {
            coinList.add(coin);
        }
    }

    private boolean freeSpace(int x, int y) {
        if(obstacleList.obstacleInPosition(x, y)) return false;
        if(coinList.coinInPosition(x, y)) return false;

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
	
	public int getObstacleNumber() {
		return obstacleList.getObstacleCounter();
	}
	
	public int getCoinNumber() {
		return coinList.getCoinCounter();
	}
	
	public long getFirstTime() {
		return time;
	}
	
	public int getCollectedCoins() {
		return player.getCollectedCoins();
	}
	
	public void reset() {
		cycles = 0;
		test = false;
		random = new Random(seed);
		coinList = new CoinList(this);
		obstacleList = new ObstacleList(this);
		player = new Player(this, 0, level.getWidth() / 2);	
		time = System.currentTimeMillis();
		init();
	}
	
	private Double getRandomNumber() {
		return random.nextDouble();
	}
	
	private int getRandomLane() {
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
	
	public String getGameStatus() {
		if(player.getCollision()) return "Collision";
		else if(getRoadLenght() - getCycles() < 0) return "Win";
		else return "";
	}

	public String positionToString(int j, int i) {
		if(player.getX() - player.getX() == j && player.getY() == i) return player.toString();
		else if(coinList.coinInPosition(j + player.getX(), i)) return coinList.toString();
		else if(obstacleList.obstacleInPosition(j + player.getX(), i)) return obstacleList.toString();
		else if (j + player.getX() == getRoadLenght()) return "¦";
		else return "";
	}
}
