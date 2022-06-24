package dataload;

import java.util.ArrayList;

import datamodel.MeasurementRecord;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HepcLoader extends Loader implements ILoader<MeasurementRecord>{
	
	//public HepcLoader() {format = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:ss");}
	
	@Override
	public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields, ArrayList<MeasurementRecord> objCollection) {
		load(fileName, delimeter, hasHeaderLine);
		for(String[] line : fileLines) {
			if(validDateTime(line, numFields)) objCollection.add(new MeasurementRecord(time, kitchen, laundry, ac));
		}
		return objCollection.size();
	}
	
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:ss");
	private LocalDateTime time;
	private double kitchen, laundry, ac;
	
	private boolean validDateTime(String[] line, int numFields) {
		
		if(line.length != numFields) return false;
		
		for(String field : line) if(field.equals("")) return false;
		
		time = LocalDateTime.parse(line[0]+line[1], format);
		kitchen = Double.parseDouble(line[6]);
		laundry = Double.parseDouble(line[7]);
		ac = Double.parseDouble(line[8]);
		
		return true;
	}
}
