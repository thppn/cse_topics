package tests;

import static org.junit.Assert.*;

import org.junit.Before;

import commands.replay.*;
import commands.*;
import model.Document;
import view.Text2SpeechEditorView;

import org.junit.Test;

public class ReplayCommandTest {
	
	Text2SpeechEditorView view;
	ReplayManager manager;
	ReplayCommand replay;
	
	Document doc;
	Command[] replayList;
	
	@Before
	public void setUp() throws Exception {
		view = Text2SpeechEditorView.getSingletonView();
		doc = Document.getInstance();
		
		manager = new ReplayManager();
		Replayable.setReplayManager(manager);
		replay = new ReplayCommand(manager);
		
		replayList = new Command[] {new NewDocument(), new SaveDocument(), new OpenDocument(), new EditDocument()};
	}

	@Test
	public void test() {
		replay.execute();
		for(Command command: replayList) {
			command.actionPerformed(null);
		}
		assertEquals(manager.getListSize(),replayList.length);
		replay.execute();
		assertEquals(manager.getListSize(), 0);	
	}
}
