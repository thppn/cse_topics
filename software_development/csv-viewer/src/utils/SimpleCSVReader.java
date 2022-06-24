package utils;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleCSVReader
{

	public ArrayList<String[]> load(String fileName) 
	{
		if(fileName == null || fileName.equals(""))
		{
			return null;
		}
		ArrayList<String[]> lines = new ArrayList<String []>();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) 
		{
			while ((line = br.readLine()) != null) 
			{
                String[] temp  = line.split(",");//Seperate the line with comma.
                lines.add(temp);//Add the line to the ArrayList.  
			}
		}
		catch (IOException e) 
		{
			return null;//If anything happens return null.
	    }
		return lines;
	}
}
