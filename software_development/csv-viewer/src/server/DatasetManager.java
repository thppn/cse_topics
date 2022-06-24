package server;

import java.util.ArrayList;

public class DatasetManager implements IDatasetManager
{
	private ArrayList<Dataset> datasets;//ArrayList of all the Datasets.
	
	//Constructor
	public DatasetManager() 
	{
		datasets = new ArrayList<Dataset>();//creates a new ArrayList of Datasets.
	}
	
	//Method find searches a Dataset by its name.
	private Dataset find(String name) 
	{
		for(Dataset dataset : datasets)//For every Dataset in the ArrayList  
		{
			if(name.equals(dataset.toString()))//If the name you are looking for matches a name in the ArrayList
			{
				return dataset;//Return it
			}
		}
		return null;//Else return null
	}
	
	//Method registerDataset adds a new Dataset in the ArrayList.
	@Override
	public int registerDataset(String datasetName, String canonicalPath) 
	{
		if(datasetName == null || datasetName.equals(""))//If the name is not given 
		{
			return -1 ;
		}
		if(find(datasetName)!= null)//If the Dataset is already registered
		{
			return -10;
		}
		datasets.add(new NativeDataset(datasetName,canonicalPath));//Else register it as a Native Dataset
		return 0;
	}
	
	
	//Method retrieveDataset finds a Dataset by its name and feeds an ArrayList with its data.
	@Override
	public String[] retrieveDataset(String datasetName, ArrayList<String[]> data) 
	{
		Dataset dataset = find(datasetName);//Find the Dataset by its name
		if(dataset == null)//If the Dataset is not found 
		{
			return null;
		}
		for(String[] line: dataset.getData())//For every line in the Dataset
		{
			data.add(line);//Feed the ArrayList
		}
		return dataset.getHeader();//Return the header
	}

	//Method filterDataset filters a Dataset by a chosen attribute and registers a new Dataset with the filtered values.
	@Override
	public int filterDataset(String originalDatasetName, String newDatasetName, String filterColumnName,String filterValue) 
	{
		Dataset originalDataset = find(originalDatasetName);//Find the Dataset by its name
		if(originalDataset == null || find(newDatasetName) != null)//If it is not found, or it already exists. 
		{
			System.out.println("Name not found or it already exists.");
			return -1;
		}
		ArrayList<String[]> newData = new ArrayList<String[]>();
		int indexOfFilterColumnName = originalDataset.getIndex(filterColumnName);//Find the index of the column to filter the Dataset.
		if(indexOfFilterColumnName == -1)//If the name of the column doesn't exist 
		{
			System.out.println("Attribute doesn't exist.");
			return -1;
		}
		for(String[] line : originalDataset.getData())//For every line in the data of the Dataset 
		{
			if(line[indexOfFilterColumnName].equals(filterValue)) 
			{
				newData.add(line);//Add to the new data the lines that match the attribute.
			}
		}
		if(newData.isEmpty())//If the data is empty then the value has not been found.
		{
			System.out.println("Could not find "+filterColumnName+" = "+filterValue+" or no data to show for "+filterColumnName);
			return -1;
		}
		newData.add(0,originalDataset.getHeader());//Add the header to the new data
		Dataset newDataset = new Dataset(newDatasetName, newData);//Create a new Dataset
		newDataset.setOrigin(originalDataset, new String[] {filterColumnName, filterValue});//Set its origin Dataset and the filter applied.
		datasets.add(newDataset);//Add it to the ArrayList.
		return 0;
	}

	//Method getDatasetProjection provides the data of two columns in order for a line chart or a scatter plot to be shown.
	@Override
	public ArrayList<String[]> getDatasetProjection(String datasetName, ArrayList<String> attributeNames) 
	{
		Dataset dataset = find(datasetName);//Find the Dataset by its name.
		if(dataset == null || attributeNames == null || attributeNames.isEmpty())//If the Dataset doesn't exist or no attribute names were given  
		{
			return null;
		}
		int xAxisColumnIndex = dataset.getIndex(attributeNames.get(0));//Get the index of the column for the xAxis data
		int yAxisColumnIndex = dataset.getIndex(attributeNames.get(1));//Get the index of the column for the yAxis data
		if(xAxisColumnIndex == -1 || yAxisColumnIndex == -1)//If either of column names don't exist 
		{ 
			return null;
		}
		ArrayList<String[]> dataToProject = new ArrayList<String[]>();//ArrayList with the lines of the data to project.
		for(String[] line : dataset.getData())//For every line in the data of the Dataset. 
		{
			dataToProject.add(new String[] {line[xAxisColumnIndex] ,line[yAxisColumnIndex]});//Add to the data to project the values of the appropriate columns
		}
		if(dataToProject.isEmpty())//If the columns had no data 
		{
			return null;
		}
		return dataToProject;
	}
}