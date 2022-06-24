package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import commands.replay.Replayable;
import encodingstrategies.StrategiesFactory;
import model.Document;
import texttospeechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

public class NewDocument extends Replayable {
	
	
	@Override
	public void execute() {
		
		Document document = Document.getInstance();
		
		document.setHeader(new String[] {
				Text2SpeechEditorView.getSingletonView()
					.getAuthor(),
				Text2SpeechEditorView.getSingletonView()
					.getTitle(),
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					"Not saved"
			});
		document.setContents(new String[] {});
		
		document.tuneAudioManager(TextToSpeechAPIFactory.create("FreeTTS"));
		document.tuneEncodingStrategy(StrategiesFactory.create("AtBash"));
		
		Text2SpeechEditorView.getSingletonView()
			.setHeader();
		Text2SpeechEditorView.getSingletonView()
			.setCurrentDocument();
	}
}
