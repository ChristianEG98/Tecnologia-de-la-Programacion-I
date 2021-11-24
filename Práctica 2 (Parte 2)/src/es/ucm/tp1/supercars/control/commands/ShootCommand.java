package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.instantActions.ShootAction;

public class ShootCommand extends Command implements Buyable{

	private static final String NAME = "shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	private static final String HELP = "shoot bullet";
	
	private ShootAction shoot = new ShootAction();
	
	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		if(buy(game)) {
			shoot.execute(game);
		}
		else {
			System.out.print("Not enough coins\n");
			System.out.print("[ERROR]: Failed to shoot\n");
			return false;
		}
		game.update();
		return true;
	}

	@Override
	public int cost() {
		return 1;
	}
}
