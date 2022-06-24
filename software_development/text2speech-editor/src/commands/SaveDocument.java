package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import commands.replay.Replayable;
import model.Document;
import utils.TextWriter;
import view.Text2SpeechEditorView;

public class SaveDocument extends Replayable {

	private String path;
	
	public String getPath() {
		return path;
	}
	
	@Override
	public void execute() {
		
		Document currentDocument = Text2SpeechEditorView.getSingletonView()
				.getCurrentDocument();
		
		path = Text2SpeechEditorView.getSingletonView()
				.getSavePath();
		
		path += path.contains(".tts")?"":".tts";
			
		currentDocument.setDate(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
			);
		
		String contents = String.join(",",currentDocument.getHeader()) + "\n" + currentDocument.toString();
		TextWriter.writeTo(path, contents);
		
		Text2SpeechEditorView.getSingletonView()
			.setHeader();
	}
}
