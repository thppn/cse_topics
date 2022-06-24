package dataload;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
	
	ArrayList<String[]> fileLines;
	
	public int load(String fileName, String delimeter, boolean hasHeaderLine) {
		if(fileName == null || fileName.equals("")) {
			return -1;
		}
		fileLines = new ArrayList<String[]>();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while((line = br.readLine()) != null) {
				String temp[] = line.split(delimeter);
				fileLines.add(temp);
			}	
		}catch(IOException e) {
			return -1;
		}
		if(hasHeaderLine) fileLines.remove(0);
		return fileLines.size();
	}
}
