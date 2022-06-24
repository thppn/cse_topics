package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.DocumentToSpeech;
import encodingstrategies.AtBashEncoding;
import model.Document;
import texttospeechapis.FakeTextToSpeechAPI;
import view.Text2SpeechEditorView;

public class DocumentToSpeechTest {

	Text2SpeechEditorView view;
	DocumentToSpeech docToSpeech;
	Document doc;
	
	FakeTextToSpeechAPI fakeAPI;
	AtBashEncoding atBash;
	
	String[] contents;
	
	String expected, reversed, encoded;
	
	@Before
	public void setUp() throws Exception {
		view = Text2SpeechEditorView.getSingletonView();
		docToSpeech = new DocumentToSpeech();
		doc = Document.getInstance();
		
		contents = new String[] {"Hello everybody", "everybody come on"};
		doc.setContents(contents);
		
		fakeAPI = new FakeTextToSpeechAPI();
		doc.tuneAudioManager(fakeAPI);
		
		atBash = new AtBashEncoding();
		doc.tuneEncodingStrategy(atBash);
		
		expected = String.join(" ", contents);
		encoded = atBash.encode(expected);
		reversed = "on come everybody everybody Hello";
	}

	@Test
	public void test() {
		docToSpeech.execute();
		
		if(view.isEncoded())
			assertEquals("Test encoded", encoded, fakeAPI.getWord());	
		else if(view.isReversed())
			assertEquals("Test reversed", reversed, fakeAPI.getWord());
		else 
			assertEquals("Test word", expected, fakeAPI.getWord());
	}
}
