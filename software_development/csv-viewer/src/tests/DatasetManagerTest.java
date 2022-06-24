package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.DatasetManager;

import java.util.ArrayList;

public class DatasetManagerTest 
{
	
	DatasetManager manager;

	@Before
	public void setUp() throws Exception 
	{
		manager = new DatasetManager();
	}

	@Test
	public void registerDatasetTest() 
	{
		assertEquals(manager.registerDataset("", null), -1);//Test to register Dataset with no name.
		assertEquals(manager.registerDataset("TestDataset", "Resources\\test.csv"), 0);//Test to register a Dataset.
		assertEquals(manager.registerDataset("TestDataset", "Resources\\test.csv"), -10);//Test to register the same Dataset.
	}
	
	@Test
	public void retrieveDatasetTest()
	{
		assertNull(manager.retrieveDataset(null, new ArrayList<String[]>()));//Test to retrieve a Dataset giving no name
		manager.registerDataset("TestDataset", "Resources\\test.csv");
		String[] output = manager.retrieveDataset("TestDataset", new ArrayList<String[]>());
		String[] expectedOutput = {"am","name", "surname", "age", "gender"};
		for(int i = 0; i<expectedOutput.length; i++)
		{
			assertEquals(output[i], expectedOutput[i]);//Test if the header is correct
		}
	}
	
	@Test
	public void filterDatasetTest()
	{
		manager.registerDataset("TestDataset", "Resources\\test.csv");
		assertEquals(manager.filterDataset("TestDataset", "TestDataset2", "name", "asimakis"),0);//Test to filter an existing Dataset
		assertEquals(manager.filterDataset("NoTestDataset", "TestDataset3", "name", "xristos"),-1);//Test to filter a non existing Dataset
		assertEquals(manager.filterDataset("TestDataset", "TestDataset2", "am", "4206"),-1);//Test to filter an existing Dataset giving the new Dataset an existing name.
		assertEquals(manager.filterDataset("TestDataset", "TestDataset4", "perasmena mathimata", "thanos"),-1);//Test to filter an existing Dataset giving wrong attribute.
		assertEquals(manager.filterDataset("TestDataset", "TestDataset5", "name", "pvassil"),-1);//Test to filter an existing Dataset giving a non existing attribute value.
	}
	
	@Test
	public void getDatasetProjectionTest()
	{
		manager.registerDataset("TestDataset", "Resources\\test2.csv");
		ArrayList<String> testArr = new ArrayList<String>();
		testArr.add("number1");
		testArr.add("number2");
		ArrayList<String[]> out = manager.getDatasetProjection("TestDataset", testArr);
		assertNull(manager.getDatasetProjection("NoTestDataset", testArr));//Test to get projection from a non existent Dataset.
		assertNull(manager.getDatasetProjection("TestDataset", new ArrayList<String>()));//Test to get projection from an empty attribute ArrayList.
		assertNull(manager.getDatasetProjection("TestDataset", null));//Test to getProjection from a null ArrayList.
		for(int i = 0; i<6; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				assertEquals(out.get(i)[j], Integer.toString(i+1));//Test if all the values are correct.
			}
		}
	}

}
