package commands;

import commands.replay.Replayable;
import encodingstrategies.StrategiesFactory;
import model.Document;
import view.Text2SpeechEditorView;

public class TuneEncoding extends Replayable {
	
	@Override
	public void execute() {
		
		Document currentDocument = Document.getInstance();
		String strategy = Text2SpeechEditorView.getSingletonView().getEncoding();
		currentDocument.tuneEncodingStrategy(StrategiesFactory.create(strategy));
	}
}
