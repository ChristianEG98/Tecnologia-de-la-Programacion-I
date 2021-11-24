package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "[ERROR]: Unknown command"+"\n";
	
	private static final String LAST_COMMAND = "[DEBUG] Executing: ";

	/* @formatter:off */
	private static final String[] HELP = new String[] {
		"Available commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobject info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode",	
	};
	/* @formatter:off */

	private static final String[] INFO = new String[] {
		"Available objects:",
		"[Car] the racing car",
		"[Coin] gives 1 coin to the player",
		"[Obstacle] hits car",
	};
	
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
		boolean continuePlaying = true;
		boolean possibleCommand = true;
		
		String command;

		printGame();
		
		while(continuePlaying) {
			
			do {
				possibleCommand = true;
				System.out.println(PROMPT);
				command = scanner.nextLine();
				System.out.println(LAST_COMMAND + command);
				
				if(command.toLowerCase().equals("h") || command.toLowerCase().equals("help")) {
					for(int i = 0; i < HELP.length; i++) {
						System.out.println(HELP[i]);
					}
				}
				else if(command.toLowerCase().equals("i") || command.toLowerCase().equals("info")) {
					for(int i = 0; i < INFO.length; i++) {
						System.out.println(INFO[i]);
					}
				}
				else if(command.toLowerCase().equals("n") || command.toLowerCase().equals("none") || command.equals("")) {
					game.update();
					printGame();
				}
				else if(command.toLowerCase().equals("q")) {
					game.moveUp();
					game.update();
					printGame();
				}
				else if(command.toLowerCase().equals("a")) {
					game.moveDown();
					game.update();
					printGame();
				}
				else if(command.toLowerCase().equals("e") || command.toLowerCase().equals("exit")) {
					continuePlaying = false;
					System.out.print(printer.endMessage() + printer.exitMessage());
				}
				else if(command.toLowerCase().equals("r") || command.toLowerCase().equals("reset")) {
					game.reset();
					printGame();
				}
				else if(command.toLowerCase().equals("t") || command.toLowerCase().equals("test")) {
					game.toggleTest();
					printGame();
				}
				else {
					possibleCommand = false;
					System.out.println(UNKNOWN_COMMAND_MSG);
				}
			} while(!possibleCommand);
			
			String state = game.getGameStatus();
			if(state.equals("Win")) {
				System.out.println(printer.endMessage()+printer.winMessage());
				continuePlaying = false;
			}
			else if(state.equals("Collision")) {
				System.out.println(printer.endMessage() + printer.crashMessage());
				continuePlaying = false;
			}
		}		
	}

}
