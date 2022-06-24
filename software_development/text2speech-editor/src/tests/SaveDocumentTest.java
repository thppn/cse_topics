package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.SaveDocument;
import model.Document;
import utils.TextReader;
import view.Text2SpeechEditorView;

public class SaveDocumentTest {

	SaveDocument testSaveDoc;
	Document testDoc;
	
	@Before
	public void setUp() throws Exception {
		
		testSaveDoc = new SaveDocument();
		testDoc = Document.getInstance();
		
		testDoc.setHeader(new String[] {"Title", "Author", "Big Bang", "Not saved"});
		testDoc.setContents(new String[] {"Bla blab bla","lablabla","!",".sd"});
		
		Text2SpeechEditorView.getSingletonView().setCurrentDocument();
	}

	@Test
	public void test() {

		testSaveDoc.execute();
		
		String path = testSaveDoc.getPath();
		String[][] file = TextReader.readFrom(path);
		
		String fileHeader = String.join(" ", file[0]);
		String fileContents = String.join("\n", file[1]) + "\n";
		
		String docHeader = String.join(" ", testDoc.getHeader());
		String docContents = testDoc.toString();
		
		assertEquals("Test header", docHeader, fileHeader);
		assertEquals("Test contents", docContents, fileContents);
	}
}
