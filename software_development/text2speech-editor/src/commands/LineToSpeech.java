package commands;

import commands.replay.Replayable;
import model.Document;
import view.Text2SpeechEditorView;

public class LineToSpeech extends Replayable {
	
	@Override
	public void execute() {
		
		Document currentDocument = Document.getInstance();

		int index = Text2SpeechEditorView.getSingletonView().getLine();
		
		if(Text2SpeechEditorView.getSingletonView().isEncoded()) 	
			currentDocument.playEncodedLine(index);			
		else if(Text2SpeechEditorView.getSingletonView().isReversed())	
			currentDocument.playReverseLine(index);			
		else
			currentDocument.playLine(index);	
	}
}
