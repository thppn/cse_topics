package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.EditDocument;
import model.Document;
import view.Text2SpeechEditorView;

public class EditDocumentTest {
	
	Text2SpeechEditorView view;
	EditDocument edit;
	Document doc;
	String expected;

	@Before
	public void setUp() throws Exception {
		view = Text2SpeechEditorView.getSingletonView();
		doc = Document.getInstance();
		edit = new EditDocument();
		
		expected = "Hello everybody everybody come on";	
		view.setText(expected);	
	}
	
	@Test
	public void test() {
		edit.execute();
		assertEquals(expected + "\n", doc.toString());
	}
}
