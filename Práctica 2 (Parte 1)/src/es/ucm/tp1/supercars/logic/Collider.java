package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.supercars.logic.gameobjects.Player;

public interface Collider {

	boolean doCollision();

	boolean receiveCollision(Player player);

}
