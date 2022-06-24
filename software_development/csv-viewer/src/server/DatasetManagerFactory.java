package server;

public class DatasetManagerFactory 
{

	//Method create creates DatasetManager objects
	public IDatasetManager create(String className) 
	{
		switch(className) 
		{
			case "DatasetManager": return new DatasetManager();
			default: return null;
		}

	}
}
