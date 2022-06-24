package commands;

import commands.replay.Replayable;
import commands.replay.ReplayManager;
import view.Text2SpeechEditorView;

public class ReplayCommand extends Command {
	
	private ReplayManager manager;
	
	public ReplayCommand(ReplayManager manager) {
		Replayable.setReplayManager(this.manager = manager);
	}

	@Override
	public void execute() {
		
		boolean status = manager.isEnabled();
		
		if(status) {
			manager.replayAll();
		}
		
		manager.toggle();
		
		Text2SpeechEditorView.getSingletonView().setReplayStatus(status);
	}
}
