package es.ucm.tp1.objects;

import es.ucm.tp1.logic.Game;

public class CoinList {

	private final String SYM = "¢";
	private Game game;
	private Coin coinList[];
	private int coinCounter = 0;
	
	public CoinList(Game game) {
		this.game = game;
		coinList = new Coin[game.getRoadLenght()];
	}
	
	public void add(Coin coin) {
		coinList[coinCounter] = coin;
		coinCounter++;
	}
	
	public void remove(int x, int y) {
		int pos = -1;
		
		for(int i = 0; i < coinCounter; i++) {
			if(coinList[i].getX() == x && coinList[i].getY() == y) pos = i;
		}
		
		if(pos != -1) {
			for(int i = pos; i < coinCounter - 1; i++) {
				coinList[i] = coinList[i + 1];
			}
			coinCounter--;
		}
	}
	
	public boolean coinInPosition(int x, int y) {
		for(int i = 0; i < coinCounter; i++) {
			if(coinList[i].getX() == x && coinList[i].getY() == y) return true;
		}
		return false;
	}
	
	public int getCoinCounter() {
		return coinCounter;
	}
	
	public String toString() {
		return SYM;
	}

}
