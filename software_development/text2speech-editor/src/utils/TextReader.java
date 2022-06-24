package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TextReader {
	
	
	public static String[][] readFrom(String path) {
		ArrayList<String> lines = new ArrayList<String>();
		String header = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = header = reader.readLine();
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String[][] { 
			header.split(","),
			lines.toArray(new String[lines.size()])
		};
	}
}
