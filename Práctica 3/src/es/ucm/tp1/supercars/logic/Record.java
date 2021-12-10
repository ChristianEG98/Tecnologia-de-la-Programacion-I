package es.ucm.tp1.supercars.logic;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.exceptions.InputOutputRecordException;

public class Record {
	
	private Level level;
	private Game game;
	private long record = 0;
	private String recordText;
	private String filename = "record.txt";
	
	public Record(Level level, Game game) throws InputOutputRecordException {
		this.level = level;
		this.game = game;
		setRecord(level, game);
	}

	public void setRecord(Level level, Game game) throws InputOutputRecordException  {
		try {
			StringBuilder str = new StringBuilder();
			String[] r;
			Boolean levelFound = false;
			Scanner in = new Scanner(new FileReader(filename));
			while(in.hasNext()) {
				r = in.next().split(":");
			    if(level.name().equalsIgnoreCase(r[0])) {
			    	record = Long.parseLong(r[1]);
			    	levelFound = true;
			    }
			    str.append(r[0] + ":" + r[1] + "\n");
			}
			if(!levelFound) {
				record = Long.MAX_VALUE;
				str.append(level + ":" + Long.MAX_VALUE + "\n");
			}
			recordText = str.toString();
			in.close();
		} catch (FileNotFoundException e) {
			game.exit();
			throw new InputOutputRecordException(e.getMessage());
		}
	}
	
	public long getRecord() {
		return record;
	}
	
	public void updateRecord() throws InputOutputRecordException {
		StringBuilder str = new StringBuilder();
		String [] levelRecord = recordText.split("\\n");
		for(int i = 0; i < levelRecord.length; i++) {
			String [] individualLevel = levelRecord[i].split(":");
			if(level.name().equalsIgnoreCase(individualLevel[0])) {
				individualLevel[1] = String.valueOf(game.getEllapedTime());
		    }
			str.append(individualLevel[0] + ":" + individualLevel[1] + "\n");
		}
		recordText = str.toString();
		saveRecords();
	}
	
	public void saveRecords() throws InputOutputRecordException {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			writer.write(recordText);
			System.out.println("Records successfully saved to file " + filename);
		}
		catch(IOException e) {
			throw new InputOutputRecordException(e.getMessage());
		}
	}

}
