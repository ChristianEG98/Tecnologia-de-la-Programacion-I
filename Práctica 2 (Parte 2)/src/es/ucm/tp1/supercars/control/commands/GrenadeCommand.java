package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.GameObjectGenerator;

public class GrenadeCommand extends Command implements Buyable{

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private int x, y;

	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public GrenadeCommand(int x, int y) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean execute(Game game) {
		if(x > game.getPlayerXPosition() - game.getCycles() && x < game.getVisibility()) {
			if(game.getObjectInPosition(x + game.getCycles(), y) == null && buy(game)) {
				GameObjectGenerator.generateGrenade(game, x + game.getCycles(), y);
			}
			else {
				System.out.print("Invalid position.\n");
				System.out.print("[ERROR]: Failed to add grenade\n");
				return false;
			}
		}
		else {
			System.out.print("Not enough coins\n");
			System.out.print("[ERROR]: Failed to add grenade\n");
			return false;
		}
		game.update();
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) {
		if (super.matchCommandName(commandWords[0]) && commandWords.length == 3) {
			return new GrenadeCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
		}
		return super.parse(commandWords);
	}

	@Override
	public int cost() {
		return 3;
	}
}
