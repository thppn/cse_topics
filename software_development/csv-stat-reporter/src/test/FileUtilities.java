package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtilities {

	/**
	 * returns number of lines in a txt file
	 * 
	 * @param filename the path of the file
	 * @return an int with the number of lines in the file
	 */
	public static int countLinesOfAFile(String filename) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
		int lines = 0;
		try {
			while (reader.readLine() != null) { 
				lines++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}

		return lines;
	}//countLinesOfAFile
}
