package tests;

import utils.SimpleCSVReader;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SimpleCSVReaderTest {

	SimpleCSVReader reader;
	
	@Before
	public void setUp() throws Exception {
		reader = new SimpleCSVReader();
	}

	@Test
	public void testLoad() {
		assertNotNull(reader.load("Resources\\test.csv"));//Test loading with existing file.
		assertNull(reader.load("Recources\\test3.csv"));//Test loading with non existing file.
		assertNull(reader.load(""));//Test
	}
	
	@Test
	public void testSize()
	{
		ArrayList<String[]> testData = reader.load("Resources\\test.csv");
		//Test if the Dataset has the correct size.
		assertEquals(testData.size(), 4);
		assertEquals(testData.get(0).length, 5);
	}

}
