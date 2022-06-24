package server;

import utils.SimpleCSVReader;

public class NativeDataset extends Dataset{

	private String path;//The path of the Dataset's origin file.
	
	//Constructor
	public NativeDataset(String datasetName,String path)
	{
		super(datasetName,(new SimpleCSVReader()).load(path));
		this.path = path;
	}
}