package tests;

import server.Dataset;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DatasetTest {
	
	private static Dataset datasetToTest;

	@Before
	public void setUp() throws Exception 
	{
		ArrayList<String[]> arr = new ArrayList<String[]>();
		arr.add(new String[] {"test1", "test2", "test3"});
		arr.add(new String[] {"1", "2", "3"});
		datasetToTest = new Dataset("test", arr);
	}

	@Test
	public void testGetIndex() 
	{
		//Test if getIndex works.
		assertEquals(datasetToTest.getIndex("test2"),1);
		assertEquals(datasetToTest.getIndex("test4"),-1);
	}
	
	@Test
	public void testData()
	{
		//Test if getHeader and getData work.
		String[] expectedHeader = new String[] {"test1", "test2", "test3"};
		for(int i = 0; i<expectedHeader.length; i++)
		{
			assertEquals(datasetToTest.getHeader()[i], expectedHeader[i]);
		}
		ArrayList<String[]> expectedData = new ArrayList<String[]>();
		expectedData.add(new String[] {"1", "2", "3"});
		for(int i = 0; i<expectedData.size(); i++)
		{
			assertEquals(datasetToTest.getData().get(0)[i], expectedData.get(0)[i]);
		}
	}
}
