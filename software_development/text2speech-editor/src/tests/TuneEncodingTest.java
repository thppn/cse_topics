package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.TuneEncoding;
import encodingstrategies.EncodingStrategy;
import model.Document;

public class TuneEncodingTest {

	TuneEncoding tuneEnc;
	Document doc;
	EncodingStrategy strategy;
	
	@Before
	public void setUp() throws Exception {
		doc = Document.getInstance();
		tuneEnc = new TuneEncoding();
	}

	@Test
	public void test() {
		assertNull(doc.getEncodingStrategy());
		
		tuneEnc.execute();
		assertNotNull(doc.getEncodingStrategy());
		
		strategy = doc.getEncodingStrategy();
		
		tuneEnc.execute();
		assertNotSame("Test strategy object", doc.getEncodingStrategy(), strategy);
	}
}
