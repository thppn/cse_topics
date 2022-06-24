package commands;


import commands.replay.Replayable;
import model.Document;
import view.Text2SpeechEditorView;

public class DocumentToSpeech extends Replayable {
	
	@Override
	public void execute() {
		Document currentDocument = Document.getInstance();
		
		if(Text2SpeechEditorView.getSingletonView().isEncoded())
			currentDocument.playEncodedContents();
		else if(Text2SpeechEditorView.getSingletonView().isReversed()) 
			currentDocument.playReverseContents();
		else 
			currentDocument.playContents();
	}
}
