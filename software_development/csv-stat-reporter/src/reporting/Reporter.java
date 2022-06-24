package reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Reporter {
	
	public ArrayList<String> stringify(HashMap<String,Double> arr) {
		
		ArrayList<String> output = new ArrayList<String>();
		
		for(String key: arr.keySet()) {
			output.add(key + "\t" + arr.get(key));
		}
	
		return output;
	}
	
	public int writeResultInFile(String fileContent, String filename) {
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			    writer.write(fileContent);
			    writer.close();
			    
		}catch(Exception e) {
			return -1;
		}
		return 0;
	}

}
