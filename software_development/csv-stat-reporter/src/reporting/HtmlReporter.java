package reporting;

import datamodel.IResult;

public class HtmlReporter extends Reporter implements IResultReporter {

	private String text;
	@Override
	public int reportResultInFile(IResult result, String filename) {
		text = "<!doctype html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta http-equiv=\"Content-Type\" content\"text/html; charset=windows-1253\">\n" + 
				"<title>"+result.getDescription()+"</title>\n" + 
						"</head>" + 
						"<body>\n<h1>"+result.getDescription()+"</h1>\n"+
		
		"<p>"+result.getAggregateFunction() + "consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C</p>\n";
		
		text += "\n<h2> Kitchen</h2>\n<ul>\n";
		for(String line: stringify(result.getAggregateMeterKitchen())) {
			text += "<li> "+line+"\n";
		}
		text += "\n</ul>\n<h2> Laundry</h2>\n<ul>\n";
		for(String line: stringify(result.getAggregateMeterLaundry())) {
			text += "<li> "+line+"\n";
		}
		text += "\n</ul>\n<h2> AC</h2>\n<ul>\n";
		for(String line: stringify(result.getAggregateMeterAC())) {
			text += "<li> "+line+"\n";
		}
		text +="</ul>\n</body>\n</html>";
		return writeResultInFile(text, filename);	
	}

}
