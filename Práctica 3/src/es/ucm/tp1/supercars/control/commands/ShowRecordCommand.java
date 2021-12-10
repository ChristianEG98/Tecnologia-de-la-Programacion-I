package es.ucm.tp1.supercars.control.commands;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.logic.Game;

public class ShowRecordCommand extends Command{

	private static final String NAME = "record";

	private static final String DETAILS = "rec[o]rd";

	private static final String SHORTCUT = "o";

	private static final String HELP = "show level record";
	
	public ShowRecordCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat df = new DecimalFormat("0.00", symbol);
		Double ellapsedTime = (game.getActualRecord())/1000.0;
		System.out.println(game.getLevel() + " record is " + df.format(ellapsedTime) + " s");
		return false;
	}
}
