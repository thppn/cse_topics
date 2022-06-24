package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.OpenDocument;
import model.Document;
import utils.TextReader;

public class OpenDocumentTest {

	OpenDocument openDoc;
	Document doc;

	@Before
	public void setUp() throws Exception {
		
		openDoc = new OpenDocument();
		doc = Document.getInstance();
	}

	@Test
	public void test() {
		openDoc.execute();
		
		String path = openDoc.getPath();
		String[][] file = TextReader.readFrom(path);
		
		String fileHeader = String.join(" ", file[0]);
		String fileContents = String.join("\n", file[1]) + "\n";
		
		String docHeader = String.join(" ", doc.getHeader());
		String docContents = doc.toString();
		
		assertEquals("Test header", fileHeader, docHeader);
		assertEquals("Test contents", fileContents, docContents);
	}
}
