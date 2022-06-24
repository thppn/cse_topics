package reporting;

import datamodel.IResult;

public class TxtReporter extends Reporter implements IResultReporter {

	private String text;
	@Override
	public int reportResultInFile(IResult result, String filename) {
		
		text = result.getDescription() 
		+"\n=======================================\n"
		+ result.getAggregateFunction() + " consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C\n";
		
		text += "\nKitchen\n--------------\n";
		for(String line: stringify(result.getAggregateMeterKitchen())) {
			text += "* "+line+"\n";
		}
		text += "\nLaundry\n--------------\n";
		for(String line: stringify(result.getAggregateMeterLaundry())) {
			text += "* "+line+"\n";
		}
		text += "\nAC\n--------------\n";
		for(String line: stringify(result.getAggregateMeterAC())) {
			text += "* "+line+"\n";
		}
		
		return writeResultInFile(text,filename);	
	}
}
