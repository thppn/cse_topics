package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatasetManagerFactoryTest.class, DatasetManagerTest.class, DatasetTest.class ,SimpleCSVReaderTest.class})
public class AllTests {

}
