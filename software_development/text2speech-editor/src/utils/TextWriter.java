package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {
	
	public static void writeTo(String path, String contents) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(contents);
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
