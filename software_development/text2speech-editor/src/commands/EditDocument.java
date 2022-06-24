package commands;

import commands.replay.Replayable;
import view.Text2SpeechEditorView;

public class EditDocument extends Replayable {


	@Override
	public void execute() {
		
		Text2SpeechEditorView.getSingletonView()
				.getCurrentDocument();
	}
}
