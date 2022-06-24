package server;

import java.util.ArrayList;


public class Dataset {

	private String datasetName;//Name of the Dataset.
	private ArrayList<String[]> data;//Data of the Dataset.
	private String[] header;//Header of the Dataset.
	private Dataset origin;//Dataset of origin.
	private ArrayList<String[]> filters = new ArrayList<String[]>();//Filters added to the Dataset.

	//Constructor
	public Dataset(String datasetName, ArrayList<String[]> data) 
	{
		this.datasetName = datasetName;//Sets the name
		header = data.remove(0);//Seperates the header
		this.data = data;//from the data
	}
	
	//Method getData returns the data of the Dataset.
	public ArrayList<String[]> getData() 
	{
		return data; 
	}
	
	//Method getHeader returns the header of the Dataset
	public String[] getHeader()
	{
		return header;
	}
	
	
	public void add(String[] line)
	{
		data.add(line); 
	}

	//Method getIndex returns the index of the name of a column.
	public int getIndex(String columnName) 
	{
		for(int columnNumber = 0; columnNumber < header.length; columnNumber++) 
		{
			if(columnName.equals(header[columnNumber]))
			{
				return columnNumber;
			}
		}
		return -1;//If the index is not found returns -1.
	}
	public String toString() 
	{
		return datasetName;
	}
	
	//Method setOrigin sets the Dataset of origin and the filters applied to a filtered Dataset
	public void setOrigin(Dataset origin, String[] filter) 
	{
		this.origin = origin;
		filters.add(filter);	
	}
}