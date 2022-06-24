package commands.replay;

import java.awt.event.ActionEvent;

import commands.Command;

public abstract class Replayable extends Command {
	
	private static ReplayManager manager;
	
	public static void setReplayManager(ReplayManager newManager) {
		manager = newManager;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(manager.isEnabled()) {
			manager.addToList(this);
		}
		execute();
	}
}
