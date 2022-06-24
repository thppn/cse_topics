package commands;

import commands.replay.Replayable;
import model.Document;
import utils.TextReader;
import view.Text2SpeechEditorView;

public class OpenDocument extends Replayable {
	
	private String path;
	
	public String getPath() {
		return path;
	}
	
	@Override
	public void execute() {
		
		Document currentDocument = Document.getInstance();
		
		path = Text2SpeechEditorView.getSingletonView().getOpenPath();
		String[][] fileContents = TextReader.readFrom(path);
		currentDocument.setHeader(fileContents[0]);
		currentDocument.setContents(fileContents[1]);
		
		Text2SpeechEditorView.getSingletonView()
			.setHeader();
		Text2SpeechEditorView.getSingletonView()
			.setCurrentDocument();	
	}
	
}
