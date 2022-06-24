package tests;

import server.DatasetManagerFactory;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatasetManagerFactoryTest {

	DatasetManagerFactory factory;
	
	@Before
	public void setUp() throws Exception {
		factory = new DatasetManagerFactory();
	}

	@Test
	public void testCreate() {
		//Test if factory works.
		assertNotNull(factory.create("DatasetManager"));
		assertNull(factory.create(""));
	}

}
