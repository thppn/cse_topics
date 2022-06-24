package commands;

import commands.replay.ReplayManager;

public class CommandsFactory {
	
	public Command createCommand(String command) {
		
		switch(command) {
		case "new_document":
			return new NewDocument();
		case "open_document":
			return new OpenDocument();
		case "edit_document":
			return new EditDocument();
		case "save_document":
			return new SaveDocument();
		case "document_to_speech":
			return new DocumentToSpeech();
		case "line_to_speech":
			return new LineToSpeech();
		case "tune_audio":
			return new TuneAudio();
		case "tune_encoding":
			return new TuneEncoding();
		case "replay_command":
			return new ReplayCommand(new ReplayManager());
		default:
			return null;
		}
	}
}
	

