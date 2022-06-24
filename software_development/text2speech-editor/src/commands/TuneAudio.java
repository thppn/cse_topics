package commands;

import commands.replay.Replayable;
import model.Document;
import texttospeechapis.TextToSpeechAPI;
import texttospeechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

public class TuneAudio extends Replayable {
	
	private TextToSpeechAPI api;
	
	public void setAPI(TextToSpeechAPI api) {
		this.api = api;
	}
	
	@Override
	public void execute() {
		
		Document currentDocument = Document.getInstance();
		
		if(api == null) {
			api = TextToSpeechAPIFactory.create("FreeTTS");
		}//Choose between multiple if more than this exist
		
		api.setVolume(Text2SpeechEditorView.getSingletonView().getVolume());
		api.setRate(Text2SpeechEditorView.getSingletonView().getRate());
		api.setPitch(Text2SpeechEditorView.getSingletonView().getPitch());
		
		currentDocument.tuneAudioManager(api);
	}
}
