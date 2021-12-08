package es.ucm.tp1.supercars.control;

import java.util.Scanner;

import es.ucm.tp1.supercars.control.commands.Command;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "[ERROR]: Unknown command\n";
	
	private static final String LAST_COMMAND = "[DEBUG] Executing: ";
	
	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
	}

	public void printGame() {
		System.out.println(printer);
	}
	
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}

	public void run() {
		boolean refreshDisplay = true;
		
		while (!game.isFinished()){
			if (refreshDisplay) {
				printGame();
			}
			refreshDisplay = false;
			System.out.println(PROMPT);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println(LAST_COMMAND + s);
			Command command = Command.getCommand(parameters, null);
			if (command != null) {
				refreshDisplay = command.execute(game);
				} 
			else {
				System.out.println(UNKNOWN_COMMAND_MSG);
			}
		}
		if (refreshDisplay) {
			printGame();
		}
		System.out.print(printer.endMessage());
		if(game.exitStatus()) System.out.println(printer.exitMessage());	
		else if(game.winStatus()) System.out.println(printer.winMessage());	
		else System.out.println(printer.crashMessage());	
	}
}
