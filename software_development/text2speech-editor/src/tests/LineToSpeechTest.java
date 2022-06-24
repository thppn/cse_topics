package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.LineToSpeech;
import encodingstrategies.AtBashEncoding;
import model.Document;
import texttospeechapis.FakeTextToSpeechAPI;
import view.Text2SpeechEditorView;

public class LineToSpeechTest {

	Text2SpeechEditorView view;
	LineToSpeech linToSpeech;
	Document doc;
	
	FakeTextToSpeechAPI fakeAPI;
	AtBashEncoding atBash;
	
	String expected, reversed, encoded;
	
	@Before
	public void setUp() throws Exception {
		view = Text2SpeechEditorView.getSingletonView();
		linToSpeech = new LineToSpeech();
		doc = Document.getInstance();
		
		expected = "Hello everybody everybody come on";		
		doc.setContents(new String[] {expected});
		
		fakeAPI = new FakeTextToSpeechAPI();
		doc.tuneAudioManager(fakeAPI);
		
		atBash = new AtBashEncoding();
		doc.tuneEncodingStrategy(atBash);
		
		reversed = "on come everybody everybody Hello";
		encoded = atBash.encode(expected);
	}

	@Test
	public void test() {
		linToSpeech.execute();
		
		if(view.isEncoded())
			assertEquals("Test encoded", encoded, fakeAPI.getWord());
		else if(view.isReversed()) 
			assertEquals("Test reversed", reversed, fakeAPI.getWord());
		else
			assertEquals("Test word", expected, fakeAPI.getWord());
	}
}
