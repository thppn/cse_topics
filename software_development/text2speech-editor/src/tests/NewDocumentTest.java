package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import commands.NewDocument;
import model.Document;

public class NewDocumentTest {

	NewDocument newDoc;
	Document doc;
	
	@Before
	public void setUp() throws Exception {
		newDoc = new NewDocument();
		doc = Document.getInstance();
	}

	/* Text2SpeechEditorView uses the one and only
	 * instance of the singleton class Document
	 */
	
	@Test
	public void test() {
		newDoc.execute();
		assertEquals("New Document Creation", doc.toString(), "");
	}

}
