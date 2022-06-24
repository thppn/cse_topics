package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commands.TuneAudio;
import model.Document;
import texttospeechapis.FakeTextToSpeechAPI;
import view.Text2SpeechEditorView;

public class TuneAudioTest {

	Text2SpeechEditorView view;
	TuneAudio tuneAud;
	Document doc;
	FakeTextToSpeechAPI fakeAPI;
	
	int volume, rate, pitch;
	
	@Before
	public void setUp() throws Exception {
		
		view = Text2SpeechEditorView.getSingletonView();
		tuneAud = new TuneAudio();
		doc = Document.getInstance();
		
		fakeAPI = new FakeTextToSpeechAPI();
		tuneAud.setAPI(fakeAPI);
		doc.tuneAudioManager(fakeAPI);
		
		volume = view.getVolume();
		rate = view.getRate();
		pitch = view.getPitch();
	}

	@Test
	public void test() {
		
		tuneAud.execute();
		
		assertEquals("Test volume", volume, fakeAPI.getVolume());
		assertEquals("Test rate", rate, fakeAPI.getRate());
		assertEquals("Test pitch", pitch, fakeAPI.getPitch());
	}
}
